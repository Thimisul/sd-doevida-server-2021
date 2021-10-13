/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaControles;

import entidades.Doacao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author lsilva
 */
public class DoacaoJpaController implements Serializable {

    public DoacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Doacao doacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idDonor = doacao.getIdDonor();
            if (idDonor != null) {
                idDonor = em.getReference(idDonor.getClass(), idDonor.getId());
                doacao.setIdDonor(idDonor);
            }
            Usuario idRecipient = doacao.getIdRecipient();
            if (idRecipient != null) {
                idRecipient = em.getReference(idRecipient.getClass(), idRecipient.getId());
                doacao.setIdRecipient(idRecipient);
            }
            em.persist(doacao);
            if (idDonor != null) {
                idDonor.getDoacaoCollection().add(doacao);
                idDonor = em.merge(idDonor);
            }
            if (idRecipient != null) {
                idRecipient.getDoacaoCollection().add(doacao);
                idRecipient = em.merge(idRecipient);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Doacao doacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Doacao persistentDoacao = em.find(Doacao.class, doacao.getId());
            Usuario idDonorOld = persistentDoacao.getIdDonor();
            Usuario idDonorNew = doacao.getIdDonor();
            Usuario idRecipientOld = persistentDoacao.getIdRecipient();
            Usuario idRecipientNew = doacao.getIdRecipient();
            if (idDonorNew != null) {
                idDonorNew = em.getReference(idDonorNew.getClass(), idDonorNew.getId());
                doacao.setIdDonor(idDonorNew);
            }
            if (idRecipientNew != null) {
                idRecipientNew = em.getReference(idRecipientNew.getClass(), idRecipientNew.getId());
                doacao.setIdRecipient(idRecipientNew);
            }
            doacao = em.merge(doacao);
            if (idDonorOld != null && !idDonorOld.equals(idDonorNew)) {
                idDonorOld.getDoacaoCollection().remove(doacao);
                idDonorOld = em.merge(idDonorOld);
            }
            if (idDonorNew != null && !idDonorNew.equals(idDonorOld)) {
                idDonorNew.getDoacaoCollection().add(doacao);
                idDonorNew = em.merge(idDonorNew);
            }
            if (idRecipientOld != null && !idRecipientOld.equals(idRecipientNew)) {
                idRecipientOld.getDoacaoCollection().remove(doacao);
                idRecipientOld = em.merge(idRecipientOld);
            }
            if (idRecipientNew != null && !idRecipientNew.equals(idRecipientOld)) {
                idRecipientNew.getDoacaoCollection().add(doacao);
                idRecipientNew = em.merge(idRecipientNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = doacao.getId();
                if (findDoacao(id) == null) {
                    throw new NonexistentEntityException("The doacao with id " + id + " no longer exists.");
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
            Doacao doacao;
            try {
                doacao = em.getReference(Doacao.class, id);
                doacao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The doacao with id " + id + " no longer exists.", enfe);
            }
            Usuario idDonor = doacao.getIdDonor();
            if (idDonor != null) {
                idDonor.getDoacaoCollection().remove(doacao);
                idDonor = em.merge(idDonor);
            }
            Usuario idRecipient = doacao.getIdRecipient();
            if (idRecipient != null) {
                idRecipient.getDoacaoCollection().remove(doacao);
                idRecipient = em.merge(idRecipient);
            }
            em.remove(doacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Doacao> findDoacaoEntities() {
        return findDoacaoEntities(true, -1, -1);
    }

    public List<Doacao> findDoacaoEntities(int maxResults, int firstResult) {
        return findDoacaoEntities(false, maxResults, firstResult);
    }

    private List<Doacao> findDoacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Doacao.class));
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

    public Doacao findDoacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Doacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getDoacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Doacao> rt = cq.from(Doacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
