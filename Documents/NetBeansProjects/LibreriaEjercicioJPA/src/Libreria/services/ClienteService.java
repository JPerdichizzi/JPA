/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.services;

import Libreria.entities.Cliente;
import Libreria.entities.Prestamo;
import Libreria.persitence.ClienteDAO;
import Libreria.persitence.PrestamoDAO;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author joaqu
 */
public class ClienteService {

    ClienteDAO clienteDAO = new ClienteDAO();
    PrestamoDAO PrestamoDAO = new PrestamoDAO();

    public void crearCliente() throws Exception {

        try {

            Cliente cliente = new Cliente();

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("\nIngresar el DNI del nuevo cliente");

            Long DNICliente = read.nextLong();

            if (DNICliente == null || DNICliente.toString().length() < 7 || DNICliente.toString().length() > 8) {
                throw new Exception("DNI Incorrecto. Recuerde que debe tener entre 7 y 8 números");
            }

            List<Cliente> clientes = clienteDAO.listarClientes();

            for (Cliente cliente1 : clientes) {
                if (cliente1.getDocumento() == DNICliente) {
                    throw new Exception("Ya existe un cliente con ese DNI");
                }
            }

            cliente.setDocumento(DNICliente);

            System.out.println("Ingresar el nombre del nuevo cliente");

            String nombreCliente = read.next();

            if (nombreCliente.trim().isEmpty()) {
                throw new Exception("No puede ingresarse un nombre en blanco");
            }

            System.out.println("Ingresar el apellido del nuevo cliente");

            String apellidoCliente = read.next();

            if (apellidoCliente.trim().isEmpty()) {
                throw new Exception("No puede ingresarse un apellido en blanco");
            }

            cliente.setNombre(nombreCliente);
            cliente.setApellido(apellidoCliente);

            System.out.println("Ingresar el teléfono del cliente");
            String telCliente = read.next();

            if (telCliente.trim().isEmpty() || telCliente.length() < 6) {
                throw new Exception("No ingreso un número o un teléfono válido");
            }

            cliente.setTeléfono(telCliente);
            cliente.setEstado("ACTIVO");
            clienteDAO.nuevoCliente(cliente);

            System.out.println("\nEl cliente se añadió correctamente\n");
        } catch (Exception e) {
            throw e;
        }
    }

    public void editarCliente() throws Exception {

        try {

           Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el DNI del cliente que desea modificar. A continuación se listan los clientes y sus DNI");

            List<Cliente> clientes = clienteDAO.listarClientes();

            for (Cliente cliente : clientes) {
                System.out.println("Nombre: " + cliente.getNombre() + " Apellido: " + cliente.getApellido() + " DNI: " + cliente.getDocumento());
            }

            List<Cliente> Cc = clienteDAO.buscarClienteDNI(read.nextLong());

            
            if (Cc.isEmpty()) {
                System.out.println("No ingresó una DNI que corresponda a un cliente registrado");
            } else {
                
                Cliente C = Cc.get(0);

                if(C.getEstado().equalsIgnoreCase("INACTIVO - BAJA")) {
                    System.out.println("El cliente está dado de baja. No puede modificarse");
                } else {
                    
              
                    

                System.out.println("Que desea modificar?"
                        + "\n1. Nombre"
                        + "\n2. Apellido"
                        + "\n3. Documento"
                        + "\n4. Teléfono");

                int seleccion = read.nextInt();
                switch (seleccion) {

                    case 1:
                        System.out.println("Ingrese el nuevo nombre");
                        String nuevoNombre = read.next();
                        if (nuevoNombre.trim().isEmpty()) {
                            throw new Exception("No puede ingresarse un nombre en blanco");
                        }
                        C.setNombre(nuevoNombre);
                        System.out.println("La modificación se realizó correctamente");
                        break;
                    case 2:
                        System.out.println("Ingrese el nuevo apellido");
                        String nuevoApellido = read.next();

                        if (nuevoApellido.trim().isEmpty()) {
                            throw new Exception("No puede ingresarse un apellido en blanco");
                        }
                        C.setApellido(nuevoApellido);
                        System.out.println("La modificación se realizó correctamente");

                        break;
                    case 3:
                        System.out.println("Ingrese el nuevo DNI");
                        Long nuevoDNI = read.nextLong();

                        List<Cliente> clientess = clienteDAO.listarClientes();

                        for (Cliente clientes1 : clientess) {
                            if (nuevoDNI == clientes1.getDocumento()) {
                                throw new Exception("Ya existe un cliente con ese DNI");
                            }
                        }
                        C.setDocumento(nuevoDNI);
                        System.out.println("La modificación se realizó correctamente");
                        break;
                    case 4:
                        System.out.println("Ingrese el nuevo número de teléfono");
                        String telCliente = read.next();

                        if (telCliente.trim().isEmpty() || telCliente.length() < 6) {
                            throw new Exception("No ingreso un número");
                        }

                        C.setTeléfono(telCliente);
                        System.out.println("La modificación se realizó correctamente");
                        break;

                    default:
                        System.out.println("Opción inválida");
                        break;

                }
                clienteDAO.refresh(C);
            }
  }
        } catch (Exception e) {
            throw e;
        }
    }

    public void borrarCliente() throws Exception {

        try {

            Scanner read = new Scanner(System.in).useDelimiter("\n");

            System.out.println("Ingresar el DNI del cliente que desea eliminar. A continuación se listan los clientes y sus DNI");

            List<Cliente> clientes = clienteDAO.listarClientes();

            for (Cliente cliente : clientes) {
                System.out.println("Nombre: " + cliente.getNombre() + " Apellido: " + cliente.getApellido() + " DNI: " + cliente.getDocumento());
            }

            List<Cliente> Cc = clienteDAO.buscarClienteDNI(read.nextLong());

            

            if (Cc.isEmpty()) {
                System.out.println("No ingresó una DNI que corresponda a un cliente registrad");
            } else {
                
                Cliente C = Cc.get(0);
            

            Long DNICliente = C.getDocumento();

            List<Prestamo> prestamosCliente = PrestamoDAO.buscarPrestamoPorCliente(DNICliente);
            int num = 0;
            if (!prestamosCliente.isEmpty()) {

                for (Prestamo prestamo : prestamosCliente) {
                    if (prestamo.getFechaDevolucion() == null) {
                        num++;
                    }
                }
            }
            if (num > 0) {

                System.out.println("El cliente no puede eliminarse porque tiene préstamos activos sin cancelar");

            } else {

                C.setEstado("INACTIVO - BAJA");
                      clienteDAO.refresh(C);
                      System.out.println("Dio de baja al cliente");
            }
            
      }
        } catch (Exception e) {
            throw e;
        }
    }

    public void ListaClientes() throws Exception {

        try {

            List<Cliente> clientes = clienteDAO.listarClientes();

            System.out.println("\nSe listan los clientes registrados");

            if (clientes.isEmpty()) {
                System.out.println("\nNo hay clientes registrados");
            }
            for (Cliente cliente : clientes){
                System.out.println(cliente.toString());

            }
            System.out.println(" ");
        } catch (Exception e) {
            throw e;
        }
    }
}
