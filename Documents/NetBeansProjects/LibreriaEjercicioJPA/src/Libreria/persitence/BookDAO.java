/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.persitence;

import Libreria.entities.Book;
import java.util.List;

/**
 *
 * @author joaqu
 */
public class BookDAO extends DAO<Book> {

    public void saveNewBook(Book book) {

        save(book);
    }

    public void refresh(Book book) {

        edit(book);
    }

    public void deleteBook(Book book) {

        delete(book);
    }

    public List<Book> buscarLibroNombre(String nombre) {
        connect();
        List<Book> book = em.createQuery("SELECT b FROM Book b where b.titulo = :nombre").setParameter("nombre", nombre).setMaxResults(1).getResultList();
        disconnect();
        return book;

    }

    public List<Book> listarLibros() throws Exception {
        connect();
        List<Book> books = em.createQuery("SELECT b FROM Book b").getResultList();
        disconnect();
        return books;
    }

    public List<Book> listarNombreLibros() throws Exception {
        connect();
        List<Book> books = em.createQuery("SELECT b.titulo FROM Book b").getResultList();
        disconnect();
        return books;
    }

    public List<Long> listarISBN() {

        List<Long> ISBNs = em.createQuery("SELECT b.id FROM Book b").getResultList();

        return ISBNs;
    }

    public List<Book> buscarISBN(Long isbn) {

        List<Book> book = em.createQuery("SELECT b FROM Book b where b.id = :isbn").setParameter("isbn", isbn).getResultList();
        return book;
    }

    public List<Book> buscarLibroPorAutor(String nombre) {

        List<Book> books = em.createQuery("SELECT b FROM Book b where b.autor.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        return books;
    }

    public List<Book> buscarLibroPorEditorial(String nombre) {

        List<Book> books = em.createQuery("SELECT b FROM Book b where b.editorial.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        return books;
    }
}
