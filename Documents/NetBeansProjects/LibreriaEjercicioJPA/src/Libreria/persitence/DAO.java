/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.persitence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author joaqu
 */
public class DAO<T> {

    EntityManagerFactory ef = Persistence.createEntityManagerFactory("LibreriaEjercicioJPAPU");
    EntityManager em = ef.createEntityManager();

    protected void connect() {
        if (!em.isOpen()) {
            em = ef.createEntityManager();
        }
    }

    protected void disconnect() {
        if (em.isOpen()) {
            em.close();
        }
    }

    protected void save(T objeto) {
        connect();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        disconnect();
    }

    protected void edit(T objeto) {
        connect();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        disconnect();
    }

    protected void delete(T objeto) {
        connect();
        em.getTransaction().begin();
        em.remove(em.merge(objeto));
        em.getTransaction().commit();
        disconnect();
    }

}
