/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class Conexion {

    private Connection cnn;
    private String cadenaConexion, usuarioDb, claveDb;

    public Conexion(String cadenaConexion, String usuarioDb, String claveDb) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.cadenaConexion = cadenaConexion;
            this.usuarioDb = usuarioDb;
            this.claveDb = claveDb;
            cnn = DriverManager.getConnection(this.cadenaConexion, this.usuarioDb, this.claveDb);
            System.out.println("Estas conectado");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean testearConexion() {
        try {
            return !cnn.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void abrirConexion() {
        try {
            cnn = DriverManager.getConnection(cadenaConexion, "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Conectado");
        }
    }

    public void cerrarConexion() {
        try {
            cnn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Desconectado");
        }
    }

}
