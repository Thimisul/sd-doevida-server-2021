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
import entidades.Doacao;
import entidades.Usuario;
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
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getDoacaoCollection() == null) {
            usuario.setDoacaoCollection(new ArrayList<Doacao>());
        }
        if (usuario.getDoacaoCollection1() == null) {
            usuario.setDoacaoCollection1(new ArrayList<Doacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Doacao> attachedDoacaoCollection = new ArrayList<Doacao>();
            for (Doacao doacaoCollectionDoacaoToAttach : usuario.getDoacaoCollection()) {
                doacaoCollectionDoacaoToAttach = em.getReference(doacaoCollectionDoacaoToAttach.getClass(), doacaoCollectionDoacaoToAttach.getId());
                attachedDoacaoCollection.add(doacaoCollectionDoacaoToAttach);
            }
            usuario.setDoacaoCollection(attachedDoacaoCollection);
            Collection<Doacao> attachedDoacaoCollection1 = new ArrayList<Doacao>();
            for (Doacao doacaoCollection1DoacaoToAttach : usuario.getDoacaoCollection1()) {
                doacaoCollection1DoacaoToAttach = em.getReference(doacaoCollection1DoacaoToAttach.getClass(), doacaoCollection1DoacaoToAttach.getId());
                attachedDoacaoCollection1.add(doacaoCollection1DoacaoToAttach);
            }
            usuario.setDoacaoCollection1(attachedDoacaoCollection1);
            em.persist(usuario);
            for (Doacao doacaoCollectionDoacao : usuario.getDoacaoCollection()) {
                Usuario oldIdDonorOfDoacaoCollectionDoacao = doacaoCollectionDoacao.getIdDonor();
                doacaoCollectionDoacao.setIdDonor(usuario);
                doacaoCollectionDoacao = em.merge(doacaoCollectionDoacao);
                if (oldIdDonorOfDoacaoCollectionDoacao != null) {
                    oldIdDonorOfDoacaoCollectionDoacao.getDoacaoCollection().remove(doacaoCollectionDoacao);
                    oldIdDonorOfDoacaoCollectionDoacao = em.merge(oldIdDonorOfDoacaoCollectionDoacao);
                }
            }
            for (Doacao doacaoCollection1Doacao : usuario.getDoacaoCollection1()) {
                Usuario oldIdRecipientOfDoacaoCollection1Doacao = doacaoCollection1Doacao.getIdRecipient();
                doacaoCollection1Doacao.setIdRecipient(usuario);
                doacaoCollection1Doacao = em.merge(doacaoCollection1Doacao);
                if (oldIdRecipientOfDoacaoCollection1Doacao != null) {
                    oldIdRecipientOfDoacaoCollection1Doacao.getDoacaoCollection1().remove(doacaoCollection1Doacao);
                    oldIdRecipientOfDoacaoCollection1Doacao = em.merge(oldIdRecipientOfDoacaoCollection1Doacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Collection<Doacao> doacaoCollectionOld = persistentUsuario.getDoacaoCollection();
            Collection<Doacao> doacaoCollectionNew = usuario.getDoacaoCollection();
            Collection<Doacao> doacaoCollection1Old = persistentUsuario.getDoacaoCollection1();
            Collection<Doacao> doacaoCollection1New = usuario.getDoacaoCollection1();
            List<String> illegalOrphanMessages = null;
            for (Doacao doacaoCollectionOldDoacao : doacaoCollectionOld) {
                if (!doacaoCollectionNew.contains(doacaoCollectionOldDoacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Doacao " + doacaoCollectionOldDoacao + " since its idDonor field is not nullable.");
                }
            }
            for (Doacao doacaoCollection1OldDoacao : doacaoCollection1Old) {
                if (!doacaoCollection1New.contains(doacaoCollection1OldDoacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Doacao " + doacaoCollection1OldDoacao + " since its idRecipient field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Doacao> attachedDoacaoCollectionNew = new ArrayList<Doacao>();
            for (Doacao doacaoCollectionNewDoacaoToAttach : doacaoCollectionNew) {
                doacaoCollectionNewDoacaoToAttach = em.getReference(doacaoCollectionNewDoacaoToAttach.getClass(), doacaoCollectionNewDoacaoToAttach.getId());
                attachedDoacaoCollectionNew.add(doacaoCollectionNewDoacaoToAttach);
            }
            doacaoCollectionNew = attachedDoacaoCollectionNew;
            usuario.setDoacaoCollection(doacaoCollectionNew);
            Collection<Doacao> attachedDoacaoCollection1New = new ArrayList<Doacao>();
            for (Doacao doacaoCollection1NewDoacaoToAttach : doacaoCollection1New) {
                doacaoCollection1NewDoacaoToAttach = em.getReference(doacaoCollection1NewDoacaoToAttach.getClass(), doacaoCollection1NewDoacaoToAttach.getId());
                attachedDoacaoCollection1New.add(doacaoCollection1NewDoacaoToAttach);
            }
            doacaoCollection1New = attachedDoacaoCollection1New;
            usuario.setDoacaoCollection1(doacaoCollection1New);
            usuario = em.merge(usuario);
            for (Doacao doacaoCollectionNewDoacao : doacaoCollectionNew) {
                if (!doacaoCollectionOld.contains(doacaoCollectionNewDoacao)) {
                    Usuario oldIdDonorOfDoacaoCollectionNewDoacao = doacaoCollectionNewDoacao.getIdDonor();
                    doacaoCollectionNewDoacao.setIdDonor(usuario);
                    doacaoCollectionNewDoacao = em.merge(doacaoCollectionNewDoacao);
                    if (oldIdDonorOfDoacaoCollectionNewDoacao != null && !oldIdDonorOfDoacaoCollectionNewDoacao.equals(usuario)) {
                        oldIdDonorOfDoacaoCollectionNewDoacao.getDoacaoCollection().remove(doacaoCollectionNewDoacao);
                        oldIdDonorOfDoacaoCollectionNewDoacao = em.merge(oldIdDonorOfDoacaoCollectionNewDoacao);
                    }
                }
            }
            for (Doacao doacaoCollection1NewDoacao : doacaoCollection1New) {
                if (!doacaoCollection1Old.contains(doacaoCollection1NewDoacao)) {
                    Usuario oldIdRecipientOfDoacaoCollection1NewDoacao = doacaoCollection1NewDoacao.getIdRecipient();
                    doacaoCollection1NewDoacao.setIdRecipient(usuario);
                    doacaoCollection1NewDoacao = em.merge(doacaoCollection1NewDoacao);
                    if (oldIdRecipientOfDoacaoCollection1NewDoacao != null && !oldIdRecipientOfDoacaoCollection1NewDoacao.equals(usuario)) {
                        oldIdRecipientOfDoacaoCollection1NewDoacao.getDoacaoCollection1().remove(doacaoCollection1NewDoacao);
                        oldIdRecipientOfDoacaoCollection1NewDoacao = em.merge(oldIdRecipientOfDoacaoCollection1NewDoacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Doacao> doacaoCollectionOrphanCheck = usuario.getDoacaoCollection();
            for (Doacao doacaoCollectionOrphanCheckDoacao : doacaoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Doacao " + doacaoCollectionOrphanCheckDoacao + " in its doacaoCollection field has a non-nullable idDonor field.");
            }
            Collection<Doacao> doacaoCollection1OrphanCheck = usuario.getDoacaoCollection1();
            for (Doacao doacaoCollection1OrphanCheckDoacao : doacaoCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Doacao " + doacaoCollection1OrphanCheckDoacao + " in its doacaoCollection1 field has a non-nullable idRecipient field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
