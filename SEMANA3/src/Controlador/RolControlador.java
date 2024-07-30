/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Configuracion.ConexionLocal;
import Models.Roles;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class RolControlador {

    private Connection cnn;
    private ConexionLocal connConsultar = new ConexionLocal();
    private Roles mostrarRoles = new Roles();

    public List<Roles> traerRoles() {
        List<Roles> roles = new ArrayList<>();
        // Consulta SQL
        String sql = "SELECT nombre,id from Roles order by id ";

        try {
            connConsultar.conectar();
            PreparedStatement stmt = connConsultar.getConexion().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Roles rol = new Roles();
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("nombre"));
                roles.add(rol);
            }
            return roles;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar: " + this.getClass().getName());
            return Collections.emptyList();
        }
    }

    public List<Roles> obtenerRoles() {
        return traerRoles();
    }
}
