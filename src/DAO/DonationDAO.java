/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Donation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.JTable;
import jpaControles.DonationJpaController;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author lsilva
 */
public class DonationDAO {

    private final DonationJpaController objetoJPA;
    private final EntityManagerFactory emf;

    public DonationDAO() {
        emf = Persistence.createEntityManagerFactory("SDServerPU");
        objetoJPA = new DonationJpaController(emf);
    }

    public void add(Donation objeto) throws Exception {
        objetoJPA.create(objeto);
    }

    public void edit(Donation objeto) throws Exception {
        objetoJPA.edit(objeto);
    }

    public void remove(int id) throws NonexistentEntityException, IllegalOrphanException {
        objetoJPA.destroy(id);
    }

    public List<Donation> geAllDonations() {
        return objetoJPA.findDonationEntities();
    }

    public void persist(Donation object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public List<Donation> getByIdDonor(int idDonor){
        return objetoJPA.findByIdDonor(idDonor);
    }

}
