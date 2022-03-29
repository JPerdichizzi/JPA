/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.persitence;

import Libreria.entities.Book;
import Libreria.entities.Publisher;
import java.util.List;

/**
 *
 * @author joaqu
 */
public class PublisherDAO extends DAO<Publisher> {

    public void saveNewPublisher(Publisher P) {

        save(P);
    }

    public void refresh(Publisher P) {

        edit(P);
    }

    public void deletePublisher(Publisher P) {

        delete(P);
    }

    public List<Publisher> buscarEditorialNombre(String nombre) {
        connect();
        List<Publisher> p = em.createQuery("SELECT p FROM Publisher p where p.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        disconnect();
        return p;

    }

    public List<Publisher> listarNombreP() throws Exception {
        connect();
        List<Publisher> Ps = em.createQuery("SELECT p FROM Publisher p").getResultList();
        disconnect();
        return Ps;
    }
}
