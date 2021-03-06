/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.JTable;
import jpaControles.UserJpaController;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author lsilva
 */
public class UserDAO {

    private final UserJpaController objetoJPA;
    private final EntityManagerFactory emf;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("SDServerPU");
        objetoJPA = new UserJpaController(emf);
    }

    public void add(User objeto) throws Exception {
        objetoJPA.create(objeto);
    }

    public void edit(User objeto) throws Exception {
        objetoJPA.edit(objeto);
    }
    
    public void editUser(User objeto) throws Exception {
        objetoJPA.editUser(objeto);
    }

    public void remove(int id) throws NonexistentEntityException, IllegalOrphanException {
        objetoJPA.destroy(id);
    }

    public List<User> geAllUsuario() {
        return objetoJPA.findUserEntities();
    }
    
    public List<User> getPendentUsers() {
        return objetoJPA.findUserEntitiesPendents();
    }
    
    public void receptorAcept(String username, int recepValideted) throws NonexistentEntityException, Exception{
        objetoJPA.receptorAcept(username, recepValideted);
    }


    public void persist(User object) {
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

    public User userLogin(String username, String password) throws NoResultException{
        User login = objetoJPA.login(username, password);
        return login;
    }

    public User getUserByUsername(String username) {
        User userFind = objetoJPA.getUserByUsername(username);
        return userFind;
    }

    public User getUserByUsernameEdit(String username) {
        User userFindEdit = objetoJPA.getUserByUsernameEdit(username);
        return userFindEdit;
    }
    
    public List<User> getReceptors(String username, String name, String city, String state){
        return objetoJPA.userFindReceptors(username, name, city, state);
    }
    
    public User getUserById(int id) {
        User userFind = objetoJPA.findUser(id);
        return userFind;
    }
    
}
