/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.services;

import Libreria.entities.Author;
import Libreria.entities.Book;
import Libreria.entities.Prestamo;
import Libreria.entities.Publisher;
import Libreria.persitence.AuthorDAO;
import Libreria.persitence.BookDAO;
import Libreria.persitence.PrestamoDAO;
import Libreria.persitence.PublisherDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author joaqu
 */
public class BookService {

    BookDAO bookDAO = new BookDAO();
    PublisherService PS = new PublisherService();
    AuthorService AS = new AuthorService();
    AuthorDAO AD = new AuthorDAO();
    PublisherDAO PD = new PublisherDAO();
    PrestamoDAO PrestamoDAO = new PrestamoDAO();

    public void createBook() throws Exception {

        try {

            Book book = new Book();

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el título del libro que desea agregar");

            String tituloUsuario = read.next();

            if (tituloUsuario.trim().isEmpty()) {
                throw new Exception("No puede ingresarse un título en blanco");
            }
            List<Book> books = bookDAO.listarLibros();

            for (Book book1 : books) {
                if (tituloUsuario.equalsIgnoreCase(book1.getTitulo())) {
                    throw new Exception("El título ya existe y no puede agregar por duplicado");

                }

            }
            book.setTitulo(tituloUsuario);

            System.out.println("Ingresar el año de publicación");
            int anio = read.nextInt();
            Date calendario = new Date();

            if (anio < 0 || anio > (calendario.getYear() + 1900)) {
                throw new Exception("Año inválido. Debe ser mayor a 0 y menor al año actual (" + (calendario.getYear() + 1900) + ")");
            }

            book.setAnio(anio);

            System.out.println("Ingresar cuántos ejemplares del libro se poseen");

            Integer cantidadLibros = read.nextInt();
            if (cantidadLibros < 0) {
                throw new Exception("Cantidad inválida, debe ser mayor a 0");
            }

            book.setEjemplares(cantidadLibros);
            book.setEjemplarePrestados(0);
            book.setEjemplaresRestantes(book.getEjemplares() - book.getEjemplarePrestados());
            book.setAlta(true);

            System.out.println("Ingresar el autor del libro"
                    + "\nSe muestra la lista de autores disponibles:");
            List<Author> autores = AD.listarNombreA();

            for (Author autore : autores) {
                System.out.println(autore.getNombre());
            }

            String autorABuscar = read.next();
            book.setAutor(AS.buscarAutorPorNombre(autorABuscar).get(0));

            System.out.println("Ingresar la editorial del libro"
                    + "\n Se muestra la lista de Editoriales disponibles:\"");
            List<Publisher> publishers = PD.listarNombreP();

            for (Publisher publisher : publishers) {
                System.out.println(publisher.getNombre());

            }
            String editorialABuscar = read.next();
            book.setEditorial(PS.buscarEditorialPorNombre(editorialABuscar).get(0));

            bookDAO.saveNewBook(book);

            System.out.println("El libro se añadió correctamente");
        } catch (Exception e) {
            throw e;
        }
    }

    public void editBook() throws Exception {

        try {

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el título del libro que desea modificar. Los títulos disponibles se listan a continuaciòn");

            List<Book> books = bookDAO.listarLibros();

            for (Book bs : books) {

                System.out.println(bs);

            }
            List<Book> bookss = bookDAO.buscarLibroNombre(read.next());

            Book book = bookss.get(0);

            System.out.println("Que desea modificar?"
                    + "\n1. Título"
                    + "\n2. Año de publicación"
                    + "\n3. Autor"
                    + "\n4. Editorial"
                    + "\n5. Deseo eliminar el libro");

            int seleccion = read.nextInt();
            switch (seleccion) {

                case 1:
                    System.out.println("Ingrese el nuevo título");
                    String nuevo = read.next();
                    if (nuevo.trim().isEmpty()) {
                        throw new Exception("No puede ingresarse un título en blanco");
                    }
                    book.setTitulo(nuevo);
                    System.out.println("La modificación se realizó correctamente");
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo año");
                    int nuevo1 = read.nextInt();
                    Date calendario = new Date();

                    if (nuevo1 < 0 || nuevo1 > (calendario.getYear() + 1900)) {
                        throw new Exception("Año inválido. Debe ser mayor a 0 y menor al año actual (" + (calendario.getYear() + 1900) + ")");
                    }
                    book.setAnio(nuevo1);
                    System.out.println("La modificación se realizó correctamente");
                    break;
                case 3:
                    System.out.println("Ingrese el nuevo autor");
                    String nuevo2 = read.next();
                    book.setAutor(AS.buscarAutorPorNombre(nuevo2).get(0));
                    System.out.println("La modificación se realizó correctamente");
                    break;
                case 4:
                    System.out.println("Ingrese la nueva editorial");
                    String nuevo3 = read.next();
                    book.setEditorial(PS.buscarEditorialPorNombre(nuevo3).get(0));
                    System.out.println("La modificación se realizó correctamente");
                    break;
                case 5:
                    List<Prestamo> prestamos = PrestamoDAO.buscarPrestamoPorLibro(book.getTitulo());
                    if (!prestamos.isEmpty()) {
                        throw new Exception("No puede dar de baja un libro con ejemplares prestados hasta que sean devueltos");
                    }
                    System.out.println("El libro será dado de baja en la próxima llamada al método Eliminar");

                    book.setAlta(false);
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;

            }

            bookDAO.refresh(book);

        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteBook() throws Exception {

        try {

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            List<Book> books = new ArrayList();

            books = bookDAO.listarLibros();

            if (books.isEmpty()) {
                System.out.println("No existen libros en condición de ser eliminados");
            } else {

                for (Book b : books) {
                    if (b.isAlta() == false) {
                        bookDAO.deleteBook(b);
                        System.out.println("Se elimimaron los libros en estado de a baja");
                    }
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void bookList() throws Exception {

        try {

            List<Book> books = new ArrayList();

            books = bookDAO.listarLibros();

            System.out.println("Se listan por título los libros disponibles y en esta de abaja (no disponibles)");

            for (Book b : books) {
                System.out.println(b.getTitulo());
            }
            System.out.println("Seleccione el título de un libro para ver su información y estado");

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            List<Book> book = bookDAO.buscarLibroNombre(read.next());

            if (book.isEmpty()) {
                System.out.println("No ingresó un libro de la lista");
            } else {
                System.out.println(book);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void ListarISBN() {
        Scanner read = new Scanner(System.in).useDelimiter("\n");

        List<Long> ISBNs = bookDAO.listarISBN();

        System.out.println("Se listan los ISBN encontrados");
        for (Long ISBN : ISBNs) {
            System.out.println(ISBN);
        }

        System.out.println("Inserte a continuaciòn el ISBN de la lista que se correspondan con el libro que desea buscar");
        buscarPorISBN(read.nextLong());
    }

    public void buscarPorISBN(Long isbn) {

        List<Book> book = bookDAO.buscarISBN(isbn);

        if (book.isEmpty()) {
            System.out.println("No se encontraron libros con el ISBN ingresado");
        } else {
            for (Book book1 : book) {
                System.out.println(book);
            }

        }
    }

    public void buscarPorNombre() {

        Scanner read = new Scanner(System.in).useDelimiter("\n");

        System.out.println("Ingrese el nombre del libro que desea buscar");

        List<Book> book = bookDAO.buscarLibroNombre(read.next());

        if (book.isEmpty()) {
            System.out.println("No se encontraron libros con el nombre ingresado");
        } else {
            for (Book book1 : book) {
                System.out.println(book);

            }
        }
    }

    public void buscarPorAutor() {

        Scanner read = new Scanner(System.in).useDelimiter("\n");

        System.out.println("Ingrese el nombre del autor para buscar sus libros");

        List<Book> books = bookDAO.buscarLibroPorAutor(read.next());

        if (books.isEmpty()) {
            System.out.println("No se encontraron libros con el autor ingresado");
        } else {
            for (Book book1 : books) {
                System.out.println(books);
            }
        }
    }

    public void buscarPorEditorial() {

        Scanner read = new Scanner(System.in).useDelimiter("\n");

        System.out.println("Ingrese el nombre de la Editorial para buscar sus libros");

        List<Book> books = bookDAO.buscarLibroPorEditorial(read.next());

        if (books.isEmpty()) {
            System.out.println("No se encontraron libros con la Editorial ingresada");
        } else {
            for (Book book1 : books) {
                System.out.println(books);
            }
        }

    }

}
