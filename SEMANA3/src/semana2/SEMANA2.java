/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package semana2;

import Configuracion.ConexionLocal;
import Vistas.Personas;

/**
 *
 * @author USUARIO
 */
public class SEMANA2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("CRUD en JAVA");
        ConexionLocal nConexion = new ConexionLocal();
        nConexion.conectar();
        System.out.println("Conectado");
        nConexion.desconectar();
        System.out.println("Testear");
        nConexion.testearConexion();
        
        Personas mostrarFormPersonas = new Personas();
        mostrarFormPersonas.setLocationRelativeTo(null);
        mostrarFormPersonas.setVisible(true);
    }

}
