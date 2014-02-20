/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.infn.ct.example.eclipselinkexample.main;

import it.infn.ct.example.eclipselinkexample.model.Todo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author mario
 */
public class Main {
    
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        Query q = em.createQuery("select t from Todo t");
        List<Todo> todos = q.getResultList();
        
        for (Todo todo : todos) {
            System.out.println(todo);
        }
        System.out.println("Size: " + todos.size());
        
        em.getTransaction().begin();
        Todo t = new Todo();
        t.setSummary("This is a test " + (todos.size() + 1));
        t.setDescription("This is a test" + (todos.size() + 1));
        em.persist(t);
        em.getTransaction().commit();
        
        em.close();;
    }
    
}
