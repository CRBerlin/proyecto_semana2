/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Configuracion.ConexionLocal;
import java.sql.SQLException;
import Interface.IGestorDatos;
import Models.Persona;
import com.mysql.jdbc.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class PersonaControlador implements IGestorDatos<Persona> {

    private Connection cnn;
    private final ConexionLocal connNewAdmin = new ConexionLocal();

    @Override
    public void creacion(Persona objeto) {

        try {
            connNewAdmin.conectar();
            String sql = "INSERT INTO persona (Nombre, Apellidos, Correo, Fecha_Nacimiento, Pais, Profesion, rol_id)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connNewAdmin.getConexion().prepareStatement(sql);
            st.setString(1, objeto.getNombre());
            st.setString(2, objeto.getApellidos());
            st.setString(3, objeto.getCorreo());
            java.sql.Date fechaNacimiento = new java.sql.Date(objeto.getFecha_Nacimiento().getTime());
            st.setDate(4, fechaNacimiento);
            st.setString(5, objeto.getPais());
            st.setString(6, objeto.getProfesion());
            st.setInt(7, objeto.getRol_id());

            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha realizado un nuevo registro.", "Datos Guardados", 1);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Por favor comprueba los datos.", "Error al crear datos", 0);
        } finally {
            connNewAdmin.desconectar();
        }
    }

    @Override
    public Persona lectura(int id) {
        String sql = "SELECT * FROM persona WHERE id = '" + id + "'";
        Persona personaTraida = new Persona();
        try {
            connNewAdmin.conectar();
            PreparedStatement realizaConsulta = connNewAdmin.getConexion().prepareStatement(sql);
            ResultSet resultado = realizaConsulta.executeQuery();
            if (resultado.next()) {
                personaTraida.setNombre(resultado.getString("Nombre"));
                personaTraida.setApellidos(resultado.getString("Apellidos"));
                personaTraida.setCorreo(resultado.getString("Correo"));
                personaTraida.setFecha_Nacimiento(resultado.getDate("Fecha_Nacimiento"));
                personaTraida.setPais(resultado.getString("Pais"));
                personaTraida.setProfesion(resultado.getString("Profesion"));
                personaTraida.setRol_id(resultado.getInt("rol_id"));
            } else {
                personaTraida = new Persona();
                JOptionPane.showMessageDialog(null, "No se encontraron datos.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontraron registros.", "Error al recuperar datos", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en la clase: " + this.getClass().getName());
        }
        return personaTraida;

    }

    @Override
    public void actualizar(Persona objetoActualizar, int id) {

        String actualizarPersona = "UPDATE persona SET Nombre=?,Apellidos=?,Correo=?,Fecha_Nacimiento=?,Pais=?,Profesion=?,rol_id=? WHERE id = '" + id + "'";

        try {
            connNewAdmin.conectar();
            PreparedStatement preparaEditar = connNewAdmin.getConexion().prepareStatement(actualizarPersona);
            preparaEditar.setString(1, objetoActualizar.getNombre());
            preparaEditar.setString(2, objetoActualizar.getApellidos());
            preparaEditar.setString(3, objetoActualizar.getCorreo());
            java.sql.Date fechaNacimiento = new java.sql.Date(objetoActualizar.getFecha_Nacimiento().getTime());
            preparaEditar.setDate(4, fechaNacimiento);
            preparaEditar.setString(5, objetoActualizar.getPais());
            preparaEditar.setString(6, objetoActualizar.getProfesion());
            preparaEditar.setInt(7, objetoActualizar.getRol_id());

            preparaEditar.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro" + e, "Error al actualizar", JOptionPane.ERROR_MESSAGE);
        } finally {
            connNewAdmin.desconectar();
        }
    }

    @Override
    public void eliminar(int id) {
        String eliminar = "DELETE FROM persona WHERE id='" + id + "'";
        try {
            connNewAdmin.conectar();
            PreparedStatement eliminacion = connNewAdmin.getConexion().prepareStatement(eliminar);
            int registroEliminado = eliminacion.executeUpdate();
            if (registroEliminado > 0) {
                JOptionPane.showMessageDialog(null, "Datos eliminados.");
            } else {
                JOptionPane.showMessageDialog(null, "Datos no eliminados.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar:" + e);
        } finally {
            connNewAdmin.desconectar();
        }
    }
}
