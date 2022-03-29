/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.services;

import Libreria.entities.Book;
import Libreria.entities.Cliente;
import Libreria.entities.Prestamo;
import Libreria.persitence.BookDAO;
import Libreria.persitence.ClienteDAO;
import Libreria.persitence.PrestamoDAO;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static java.time.temporal.TemporalQueries.localTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author joaqu
 */
public class PrestamoService {

    PrestamoDAO PrestamoDAO = new PrestamoDAO();
    

    BookDAO BD = new BookDAO();
    ClienteDAO CD = new ClienteDAO();

    public void Prestar() throws Exception {

        try {

            Prestamo prestamo = new Prestamo();

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el DNI del cliente");

            Long respuesta = read.nextLong();

            if (respuesta == null || respuesta.toString().length() < 7 || respuesta.toString().length() > 8) {
                throw new Exception("DNI Incorrecto. Recuerde que debe tener entre 7 y 8 números y corresponder a un cliente registrado");
            }

            List<Cliente> cliente = CD.buscarClienteDNI(respuesta);

            if (cliente.isEmpty()) {
                throw new Exception("El DNI no corresponde a un cliente registrado");
            }

            

            Cliente clientePrestatario = cliente.get(0);

            if (clientePrestatario.getEstado().equalsIgnoreCase("INACTIVO - BAJA")) {
                throw new Exception("El DNI corresponde a un cliente dado de baja");
            }
            prestamo.setCliente(clientePrestatario);

            System.out.println("Ingresar el nombre del libro que se presta, Presione L para mostrar la lista de libros en Biblioteca");

            String respuesta1 = read.next();

            if (respuesta1.trim().isEmpty()) {
                throw new Exception("No puede ingresarse un título en blanco");
            }

            if (respuesta1.equalsIgnoreCase("L")) {
                List<Book> books = BD.listarLibros();

                for (Object book : books) {
                    System.out.println(book.toString());

                }

                System.out.println("Ingrese el nombre de libro que se presta");

                respuesta1 = read.next();
            }
            List<Book> book = BD.buscarLibroNombre(respuesta1);

            Book libroAPrestar = book.get(0);

            if (libroAPrestar.getEjemplaresRestantes() == 0) {

                throw new Exception("No quedan ejemplares disponibles para préstamo");

            }

            if (libroAPrestar.isAlta() == false) {
                throw new Exception("El libro está en condición de baja. No puede prestarse");
            }

            prestamo.setBook(libroAPrestar);

            Date fechaPrestamo = new Date();

            prestamo.setFechaPrestamo(fechaPrestamo);

            PrestamoDAO.nuevoPrestamo(prestamo);

            System.out.println("\nEl préstamo se cargo correctamente correctamente");

            List<Prestamo> cantidadDelCliente = (List<Prestamo>) PrestamoDAO.buscarPrestamoPorCliente(prestamo.getCliente().getDocumento());

            int cantidadPrestamos = 0;

            for (Prestamo prestamo1 : cantidadDelCliente) {

                cantidadPrestamos++;
            }
            System.out.println("\nEl cliente tiene " + cantidadPrestamos + " préstamos activos");

            libroAPrestar.setEjemplarePrestados(libroAPrestar.getEjemplarePrestados() + 1);
            libroAPrestar.setEjemplaresRestantes(libroAPrestar.getEjemplaresRestantes() - 1);
            BD.refresh(libroAPrestar);
        } catch (Exception e) {
            throw e;
        }
    }

    public void cancelaPrestamo() throws Exception {

        try {

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el número del préstamos que desea cancelar");

            List<Prestamo> prestamos = PrestamoDAO.listaPrestamos();

            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo.toString());
            }

            List<Prestamo> prestamoss = PrestamoDAO.buscarPrestamoPorID(read.nextLong());

            
            Prestamo prestamo = prestamoss.get(0);
            
            if(prestamo.getFechaDevolucion() != null) {
                throw new Exception("El préstamo no está activo");
            }

            Date fechaDevolucion = new Date();
            prestamo.setFechaDevolucion(fechaDevolucion);

            PrestamoDAO.refresh(prestamo);

            System.out.println("\nPréstamo cancelado");
            List<Book> book = BD.buscarLibroNombre(prestamo.getBook().getTitulo());

            Book libroDevuelto = book.get(0);
            libroDevuelto.setEjemplarePrestados(libroDevuelto.getEjemplarePrestados() - 1);
            libroDevuelto.setEjemplaresRestantes(libroDevuelto.getEjemplaresRestantes() + 1);
            BD.refresh(libroDevuelto);
        } catch (Exception e) {
            throw e;
        }
    }

    public void PrestamoList() throws Exception {

        try {

            List<Prestamo> prestamos = PrestamoDAO.listaPrestamos();
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo.toString());
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void PrestamosPorCliente() throws Exception {
        System.out.println("Ingrese el DNI de cliente");
        Scanner read = new Scanner(System.in).useDelimiter("\n");
        Long dni = read.nextLong();

        if (CD.buscarClienteDNI(dni).isEmpty()) {
            System.out.println("\nEl DNI no corresponde a un usuario registrado");
        } else {
            List<Prestamo> prestamosCliente = PrestamoDAO.buscarPrestamoPorCliente(dni);

            if (prestamosCliente.isEmpty()) {
                System.out.println("No existen préstamos para el cliente seleccionado");
            } else {
                System.out.println("\nSe imprimen los préstamos para el cliente con DNI " + dni + ":");
                for (Prestamo prestamo : prestamosCliente) {
                    
                    System.out.println(prestamo.toString());
                }
            }

        }

    }

}
