/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaControles;

import entidades.Donation;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author lsilva
 */
public class DonationJpaController implements Serializable {

    public DonationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Donation donation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User idDonor = donation.getIdDonor();
            if (idDonor != null) {
                idDonor = em.getReference(idDonor.getClass(), idDonor.getId());
                donation.setIdDonor(idDonor);
            }
            em.persist(donation);
            if (idDonor != null) {
                idDonor.getDonationCollection().add(donation);
                idDonor = em.merge(idDonor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Donation donation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Donation persistentDonation = em.find(Donation.class, donation.getId());
            User idDonorOld = persistentDonation.getIdDonor();
            User idDonorNew = donation.getIdDonor();
            if (idDonorNew != null) {
                idDonorNew = em.getReference(idDonorNew.getClass(), idDonorNew.getId());
                donation.setIdDonor(idDonorNew);
            }
            donation = em.merge(donation);
            if (idDonorOld != null && !idDonorOld.equals(idDonorNew)) {
                idDonorOld.getDonationCollection().remove(donation);
                idDonorOld = em.merge(idDonorOld);
            }
            if (idDonorNew != null && !idDonorNew.equals(idDonorOld)) {
                idDonorNew.getDonationCollection().add(donation);
                idDonorNew = em.merge(idDonorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = donation.getId();
                if (findDonation(id) == null) {
                    throw new NonexistentEntityException("The donation with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Donation donation;
            try {
                donation = em.getReference(Donation.class, id);
                donation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The donation with id " + id + " no longer exists.", enfe);
            }
            User idDonor = donation.getIdDonor();
            if (idDonor != null) {
                idDonor.getDonationCollection().remove(donation);
                idDonor = em.merge(idDonor);
            }
            em.remove(donation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Donation> findDonationEntities() {
        return findDonationEntities(true, -1, -1);
    }

    public List<Donation> findDonationEntities(int maxResults, int firstResult) {
        return findDonationEntities(false, maxResults, firstResult);
    }

    private List<Donation> findDonationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Donation.class));
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

    public Donation findDonation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Donation.class, id);
        } finally {
            em.close();
        }
    }

    public int getDonationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Donation> rt = cq.from(Donation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
