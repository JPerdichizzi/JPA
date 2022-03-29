/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.persitence;

import Libreria.entities.Author;
import Libreria.entities.Cliente;
import static Libreria.entities.Cliente_.documento;
import java.util.List;

/**
 *
 * @author joaqu
 */
public class ClienteDAO extends DAO<Cliente> {

    public void nuevoCliente(Cliente C) {

        save(C);
    }

    public void refresh(Cliente C) {

        edit(C);
    }

    public void borrarCliente(Cliente C) {

        delete(C);
    }

    public List<Cliente> listarClientes() {
        connect();
        List<Cliente> Cs = em.createQuery("SELECT c FROM Cliente c").getResultList();
         disconnect();
        
            return Cs;
        
       
        
    }

    public List<Cliente> buscarClienteDNI(Long documento) throws Exception {
        connect();
        List<Cliente> cliente = em.createQuery("SELECT c FROM Cliente c where c.documento = :documento").setParameter("documento", documento).getResultList();
        disconnect();
        return cliente;

    }
}
