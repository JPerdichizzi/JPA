/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriaejerciciojpa;

/**
 *
 * @author joaqu
 */
public class LibreriaEjercicioJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        MainService mainService = new MainService();

        try {

            // TODO code application logic here
            mainService.menu();

        } catch (Exception e) {
            throw e;
        }
    }

}
