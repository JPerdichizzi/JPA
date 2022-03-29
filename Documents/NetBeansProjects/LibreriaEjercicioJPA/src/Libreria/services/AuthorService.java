/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.services;

import Libreria.entities.Author;
import Libreria.entities.Book;
import Libreria.entities.Publisher;
import Libreria.persitence.AuthorDAO;
import Libreria.persitence.BookDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author joaqu
 */
public class AuthorService {

    AuthorDAO ADAO = new AuthorDAO();
    BookDAO BDAO = new BookDAO();

    public void newAuthor() throws Exception {

        try {

            Author A = new Author();

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el nombre del Autor que desea agregar");
            String nombreAutor = read.next();

            if (nombreAutor.trim().isEmpty()) {
                throw new Exception("No puede ingresra un nombre vacío");
            }

            List<Author> Autor = ADAO.listarNombreA();

            for (Author autor : Autor) {
                if (nombreAutor.equalsIgnoreCase(autor.getNombre())) {
                    throw new Exception("El autor ya existe y no puede agregar por duplicado");
                }

            }

            if (!nombreAutor.contains(" ")) {
                throw new Exception("Debe ingresar nombre y apellido");
            }

            A.setNombre(nombreAutor);
            A.setAlta(true);

            ADAO.saveNewAuthor(A);
            System.out.println("El autor se añadió correctamente");
        } catch (Exception e) {
            throw e;
        }
    }

    public void editAuthor() throws Exception {

        try {

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el nombre del Autor que desea modificar. Se listan los autores actualmente dadatos de alta:");

            List<Author> autores = ADAO.listarNombreA();

            for (Author autore : autores) {
                System.out.println(autore.getNombre());
            }

            List<Author> Aa = ADAO.buscarAutorNombre(read.next());

            if (Aa.isEmpty()) {
                throw new Exception("No ingresó un autor de la lista de autores activos");
            }
            Author A = Aa.get(0);

            System.out.println("Ingrese el nuevo nombre del Autor");
            String nombre = read.next();
            A.setNombre(nombre);

            ADAO.refresh(A);

            System.out.println("La modificación se realizó correctamente");

        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteP() throws Exception {

        try {

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el nombre del Autor que desea eliminar. Se listan los autores actualmente dadatos de alta:");

            List<Author> autores = ADAO.listarNombreA();

            for (Author autore : autores) {
                System.out.println(autore.getNombre());
            }

            List<Author> Aa = ADAO.buscarAutorNombre(read.next());

            if (Aa.isEmpty()) {
                throw new Exception("No ingresó un autor de la lista de autores activos");
            }

            Author A = Aa.get(0);

            String nombreAutor = A.getNombre();

            if (!BDAO.buscarLibroPorAutor(nombreAutor).isEmpty()) {
                System.out.println("El autor no puede eliminarse porque está asignado a un libro. Actualice o eliminar primero el o los libros correspondientes y luego intente nuevamente");
            } else {

                A.setAlta(false);
                ADAO.refresh(A);
                ADAO.deleteAuthor(A);

                System.out.println("El autor se eliminó correctamente");

            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void AuthorList() throws Exception {

        try {

            List<Author> As = new ArrayList();

            As = ADAO.listarNombreA();

            System.out.println("Se listan los Autores por nombre");

            for (Author a : As) {

                System.out.println(a.getNombre());
            }
            System.out.println("Seleccione el nombre del Autor para ver su información");

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            List<Author> a = ADAO.buscarAutorNombre(read.next());

            if (a.isEmpty()) {
                System.out.println("No ingresó un autor de la lista");
            } else {
                for (Author author : a) {
                    System.out.println(a);
                }

            }

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Author> buscarAutorPorNombre(String nombre) {

        List<Author> autor = ADAO.buscarAutorNombre(nombre);

        return autor;
    }
}
