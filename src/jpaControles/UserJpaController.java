/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaControles;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Donation;
import entidades.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author lsilva
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getDonationCollection() == null) {
            user.setDonationCollection(new ArrayList<Donation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Donation> attachedDonationCollection = new ArrayList<Donation>();
            for (Donation donationCollectionDonationToAttach : user.getDonationCollection()) {
                donationCollectionDonationToAttach = em.getReference(donationCollectionDonationToAttach.getClass(), donationCollectionDonationToAttach.getId());
                attachedDonationCollection.add(donationCollectionDonationToAttach);
            }
            user.setDonationCollection(attachedDonationCollection);
            em.persist(user);
            for (Donation donationCollectionDonation : user.getDonationCollection()) {
                User oldIdDonorOfDonationCollectionDonation = donationCollectionDonation.getIdDonor();
                donationCollectionDonation.setIdDonor(user);
                donationCollectionDonation = em.merge(donationCollectionDonation);
                if (oldIdDonorOfDonationCollectionDonation != null) {
                    oldIdDonorOfDonationCollectionDonation.getDonationCollection().remove(donationCollectionDonation);
                    oldIdDonorOfDonationCollectionDonation = em.merge(oldIdDonorOfDonationCollectionDonation);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getId());
            Collection<Donation> donationCollectionOld = persistentUser.getDonationCollection();
            Collection<Donation> donationCollectionNew = user.getDonationCollection();
            List<String> illegalOrphanMessages = null;
            for (Donation donationCollectionOldDonation : donationCollectionOld) {
                if (!donationCollectionNew.contains(donationCollectionOldDonation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Donation " + donationCollectionOldDonation + " since its idDonor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Donation> attachedDonationCollectionNew = new ArrayList<Donation>();
            for (Donation donationCollectionNewDonationToAttach : donationCollectionNew) {
                donationCollectionNewDonationToAttach = em.getReference(donationCollectionNewDonationToAttach.getClass(), donationCollectionNewDonationToAttach.getId());
                attachedDonationCollectionNew.add(donationCollectionNewDonationToAttach);
            }
            donationCollectionNew = attachedDonationCollectionNew;
            user.setDonationCollection(donationCollectionNew);
            user = em.merge(user);
            for (Donation donationCollectionNewDonation : donationCollectionNew) {
                if (!donationCollectionOld.contains(donationCollectionNewDonation)) {
                    User oldIdDonorOfDonationCollectionNewDonation = donationCollectionNewDonation.getIdDonor();
                    donationCollectionNewDonation.setIdDonor(user);
                    donationCollectionNewDonation = em.merge(donationCollectionNewDonation);
                    if (oldIdDonorOfDonationCollectionNewDonation != null && !oldIdDonorOfDonationCollectionNewDonation.equals(user)) {
                        oldIdDonorOfDonationCollectionNewDonation.getDonationCollection().remove(donationCollectionNewDonation);
                        oldIdDonorOfDonationCollectionNewDonation = em.merge(oldIdDonorOfDonationCollectionNewDonation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Donation> donationCollectionOrphanCheck = user.getDonationCollection();
            for (Donation donationCollectionOrphanCheckDonation : donationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Donation " + donationCollectionOrphanCheckDonation + " in its donationCollection field has a non-nullable idDonor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
