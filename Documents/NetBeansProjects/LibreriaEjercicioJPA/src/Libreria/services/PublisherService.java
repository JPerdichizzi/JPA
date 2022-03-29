/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.services;

import Libreria.entities.Author;
import Libreria.entities.Book;
import Libreria.entities.Publisher;
import Libreria.persitence.BookDAO;
import Libreria.persitence.PublisherDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author joaqu
 */
public class PublisherService {

    PublisherDAO PDAO = new PublisherDAO();
    BookDAO BDAO = new BookDAO();

    public void createPublisher() throws Exception {

        try {

            Publisher p = new Publisher();

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el nombre de la Editorial que desea agregar");

            String nombreEditorial = read.next();

            if (nombreEditorial.isEmpty()) {
                throw new Exception("No puede ingresra un nombre vacío");
            }

            List<Publisher> publisher = PDAO.listarNombreP();

            for (Publisher ps : publisher) {
                if (nombreEditorial.equalsIgnoreCase(ps.getNombre())) {
                    throw new Exception("La editorial ya existe y no puede agregar por duplicado");
                }

            }
            p.setNombre(nombreEditorial);
            p.setAlta(true);

            PDAO.saveNewPublisher(p);
            System.out.println("La nueva Editorial se añadió correctamente");
        } catch (Exception e) {
            throw e;
        }
    }

    public void editPublisher() throws Exception {

        try {

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el nombre de la Editorial que desea modificar. A continuación se listan las Editoriales actualmente dadas de alta");

            List<Publisher> editoriales = PDAO.listarNombreP();

            for (Publisher editoriale : editoriales) {
                System.out.println(editoriale.getNombre());
            }
            List<Publisher> Pp = PDAO.buscarEditorialNombre(read.next());

            if (Pp.isEmpty()) {
                throw new Exception("No ingresó una Editorial de la lista de Editoriales activas");
            }
            Publisher p = Pp.get(0);

            System.out.println("Ingrese el nuevo nombre de la Editorial");
            String nombre = read.next();
            p.setNombre(nombre);

            PDAO.refresh(p);

            System.out.println("La modificación se realizó correctamente");

        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteP() throws Exception {

        try {

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el nombre de la Editorial que desea eliminar. A continuación se listan las Editoriales actualmente dadas de alta");

            List<Publisher> editoriales = PDAO.listarNombreP();

            for (Publisher editoriale : editoriales) {
                System.out.println(editoriale.getNombre());
            }

            List<Publisher> Pp = PDAO.buscarEditorialNombre(read.next());

            Publisher p = Pp.get(0);

            if (Pp.isEmpty()) {
                System.out.println("No ingresó una Editorial de la lista");
            }
            String nombreEditorial = p.getNombre();

            if (!BDAO.buscarLibroPorEditorial(nombreEditorial).isEmpty()) {
                System.out.println("La Editorial no puede eliminarse porque está asignada a un libro. Actualice o eliminar primero el o los libros correspondientes y luego intente nuevamente");
            } else {

                p.setAlta(false);

                PDAO.deletePublisher(p);

                System.out.println("La editorial se eliminó correctamente");
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void PublisherList() throws Exception {

        try {

            List<Publisher> Ps = new ArrayList();

            Ps = PDAO.listarNombreP();

            System.out.println("Se listan las Editoriales por nombre");

            for (Publisher P : Ps) {

                System.out.println(P.getNombre());
            }
            System.out.println("Seleccione el nombre de la Editorial para ver su información");

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            List<Publisher> Pp = PDAO.buscarEditorialNombre(read.next());

            if (Pp.isEmpty()) {
                System.out.println("No ingresó una Editorial de la lista");
            }
            Publisher p = Pp.get(0);

            System.out.println(p);

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Publisher> buscarEditorialPorNombre(String nombre) {

        List<Publisher> P = PDAO.buscarEditorialNombre(nombre);

        return P;
    }
}
