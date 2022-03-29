/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriaejerciciojpa;

import Libreria.services.AuthorService;
import Libreria.services.BookService;
import Libreria.services.ClienteService;
import Libreria.services.PrestamoService;
import Libreria.services.PublisherService;
import java.util.Scanner;

/**
 *
 * @author joaqu
 */
public class MainService {

    public void menu() throws Exception {

        BookService book = new BookService();
        AuthorService author = new AuthorService();
        PublisherService publisher = new PublisherService();
        ClienteService cliente = new ClienteService();
        PrestamoService prestamo = new PrestamoService();

        Scanner read = new Scanner(System.in).useDelimiter("\n");

        String menuOption = "";
        String sub = "";

        System.out.println("\nBienvenido");
        do {
            try {

                System.out.println("\nPresione Enter para continuar");
                read.next();

                System.out.println("Por favor, escoja una opción"
                        + "\n1. Sección Libros - Presione L"
                        + "\n2. Sección Autores - Presione A"
                        + "\n3. Sección Editorial - Presione E"
                        + "\n4. Sección Clientes - Presione C"
                        + "\n5. Sección Préstamos - Presione P"
                        + "\n6. Salir del programa - Presione S"
                );

                menuOption = read.next();

                switch (menuOption) {

                    case "L":
                        System.out.println("Por favor, escoja una opción"
                                + "\n1. Agregar un nuevo libro - Presione 1"
                                + "\n2. Modificar un libro existente - Presione 2"
                                + "\n3. Eliminar un libro - Presione 3"
                                + "\n4. Buscar un libro por su nombre - Presione 4"
                                + "\n5. Buscar un libro por ISBN - Presione 5"
                                + "\n6. Buscar libros por autor - Presione 6"
                                + "\n7. Buscar libros por Editorial - Presione 7"
                                + "\n8. Listar todos los libros - Presione 8"
                                + "\n9. Volver al Menú anterior - Presione 9"
                        );
                        sub = read.next();

                        switch (sub) {

                            case "1":
                                book.createBook();
                                break;
                            case "2":
                                book.editBook();
                                break;
                            case "3":
                                book.deleteBook();
                                break;
                            case "4":
                                book.buscarPorNombre();
                                break;
                            case "5":
                                book.ListarISBN();
                                break;
                            case "6":
                                book.buscarPorAutor();
                                break;
                            case "7":
                                book.buscarPorEditorial();
                                break;
                            case "8":
                                book.bookList();
                                break;
                            case "9":
                                menu();
                                break;
                            default:
                                System.out.println("\nLa opción elegida no es válida");
                                break;
                        }

                        break;
                    case "A":
                        System.out.println("Por favor, escoja una opción"
                                + "\n1. Agregar un nuevo autor - Presione 1"
                                + "\n2. Modificar un autor existente - Presione 2"
                                + "\n3. Eliminar un autor - Presione 3"
                                + "\n4. Listar autores - Presione 4"
                                //                        + "\n5. Buscar un libro por ISBN - Presione 5"
                                //                        + "\n6. Buscar libros por autor - Presione 6"
                                //                        + "\n7. Buscar libros por Editorial - Presione 7"
                                //                               + "\n7. Listar todos los libros - Presione 8"
                                + "\n8. Volver al Menú anterior - Presione 9"
                        );
                        sub = read.next();

                        switch (sub) {

                            case "1":
                                author.newAuthor();
                                break;
                            case "2":
                                author.editAuthor();
                                break;
                            case "3":
                                author.deleteP();

                                break;
                            case "4":
                                author.AuthorList();
                                break;
                            case "9":
                                menu();
                                break;
                            default:
                                System.out.println("\nLa opción elegida no es válida");
                                break;
                        }

                        break;
                    case "E":
                        System.out.println("Por favor, escoja una opción"
                                + "\n1. Agregar una Editorial - Presione 1"
                                + "\n2. Modificar una Editorial existente - Presione 2"
                                + "\n3. Eliminar una Editorial - Presione 3"
                                + "\n4. Listar editoriales - Presione 4"
                                + "\n8. Volver al Menú anterior - Presione 9"
                        );
                        sub = read.next();

                        switch (sub) {

                            case "1":
                                publisher.createPublisher();
                                break;
                            case "2":
                                publisher.editPublisher();
                                break;
                            case "3":
                                publisher.deleteP();
                                break;
                            case "4":
                                publisher.PublisherList();
                                break;
                            case "9":
                                menu();
                                break;
                            default:
                                System.out.println("\nLa opción elegida no es válida");
                                break;
                        }
break;
                    case "C":
                        System.out.println("Por favor, escoja una opción"
                                + "\n1. Agregar un nuevo cliente - Presione 1"
                                + "\n2. Modificar un cliente existente - Presione 2"
                                + "\n3. Eliminar un cliente - Presione 3"
                                + "\n4. Listar todos los clientes - Presione 4"
                                + "\n9. Volver al Menú anterior - Presione 9"
                        );
                        sub = read.next();

                        switch (sub) {

                            case "1":
                                cliente.crearCliente();
                                break;
                            case "2":
                                cliente.editarCliente();
                                break;
                            case "3":
                                cliente.borrarCliente();
                                break;
                            case "4":
                                cliente.ListaClientes();
                                break;
                            case "9":
                                menu();
                                break;
                            default:
                                System.out.println("\nLa opción elegida no es válida");
                                break;
                        }
                      break;
                    case "P":
                        System.out.println("Por favor, escoja una opción"
                                + "\n1. Cargar un préstamo - Presione 1"
                                + "\n2. Devolución de libro prestado - Presione 2"
                                + "\n3. Listar préstamos - Presione 3"
                                + "\n4. Listar todos los préstamos de un clientes - Presione 4"
                                + "\n9. Volver al Menú anterior - Presione 9"
                        );
                        sub = read.next();

                        switch (sub) {

                            case "1":
                                prestamo.Prestar();
                                break;
                            case "2":
                                prestamo.cancelaPrestamo();
                                break;
                            case "3":
                                prestamo.PrestamoList();
                                break;
                            case "4":
                                prestamo.PrestamosPorCliente();
                                break;
                            case "9":
                                menu();
                                break;
                            default:
                                System.out.println("\nLa opción elegida no es válida");
                                break;
                        }
                        break;

                }
            } catch (Exception e) {

                throw e;
            }

        } while (!menuOption.equalsIgnoreCase("S"));

    }
}
