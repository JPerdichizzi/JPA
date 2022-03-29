/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.persitence;

import Libreria.entities.Author;
import Libreria.entities.Prestamo;
import java.util.List;

/**
 *
 * @author joaqu
 */
public class PrestamoDAO extends DAO<Prestamo> {

    public void nuevoPrestamo(Prestamo P) {

        save(P);
    }

    public void refresh(Prestamo P) {

        edit(P);
    }

    public void borrarPrestamo (Prestamo P) {

        delete(P);
    }

    public List<Prestamo> buscarPrestamoPorCliente(Long documento) throws Exception {
        
         connect();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p where p.cliente.documento = :documento").setParameter("documento", documento).getResultList();
         disconnect();
        return prestamos;
    }
    
    public List<Prestamo> buscarPrestamoPorLibro(String titulo) throws Exception {
        
         connect();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p where p.book.titulo = :titulo").setParameter("titulo", titulo).getResultList();
         disconnect();
        return prestamos;
    }
    
    public List<Prestamo> buscarPrestamoPorID(Long id) throws Exception {
        
         connect();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p where p.id = :id").setParameter("id", id).getResultList();
         disconnect();
        return prestamos;
    }

   public List<Prestamo> listaPrestamos() throws Exception {
        
         connect();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p").getResultList();
         disconnect();
        return prestamos;
    } 
}
