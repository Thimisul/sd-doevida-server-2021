/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaControles.UsuarioJpaController;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author lsilva
 */
public class UsuarioDAO {
        private final UsuarioJpaController objetoJPA;
    private final EntityManagerFactory emf;

    public UsuarioDAO(){
        emf=Persistence.createEntityManagerFactory("SDServerPU");
        objetoJPA = new UsuarioJpaController(emf);
    }
    
    public void add(Usuario objeto) throws Exception{
        objetoJPA.create(objeto);
    }
    
    public void edit(Usuario objeto) throws Exception{
        objetoJPA.edit(objeto);
    }
    
    public void remove(int id) throws NonexistentEntityException, IllegalOrphanException{
        objetoJPA.destroy(id);
    }
    
    public List<Usuario> geAllUsuario(){
        return objetoJPA.findUsuarioEntities();
    }
    
    
    public void persist (Usuario object){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally{
            em.close();
        }
    }
    
    public Usuario userLogin(String username, String password){
        Usuario login = objetoJPA.login(username, password);
        System.out.println( "DAO   -    " + login.getName());
        return login;
    }  
}
