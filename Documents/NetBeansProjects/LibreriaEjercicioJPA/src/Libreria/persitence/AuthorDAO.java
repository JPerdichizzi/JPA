/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.persitence;

import Libreria.entities.Author;
import Libreria.entities.Publisher;
import java.util.List;

/**
 *
 * @author joaqu
 */
public class AuthorDAO extends DAO<Author> {

    public void saveNewAuthor(Author A) {

        save(A);
    }

    public void refresh(Author A) {

        edit(A);
    }

    public void deleteAuthor(Author A) {

        delete(A);
    }

    public List<Author> buscarAutorNombre(String nombre) {
        connect();
        List<Author> A = em.createQuery("SELECT a FROM Author a where a.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        disconnect();
        return A;

    }

    public List<Author> listarNombreA() throws Exception {
        connect();
        List<Author> As = em.createQuery("SELECT a FROM Author a").getResultList();
        disconnect();
        return As;
    }

}
