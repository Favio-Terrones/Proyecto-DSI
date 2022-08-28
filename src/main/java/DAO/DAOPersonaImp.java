/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO_CLASES.Persona;
import DAO_Interfas.DAOPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Erick
 */
public class DAOPersonaImp extends Conexion implements DAOPersona {

    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean registrar(Persona persona) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("insert into usuario (nombre,dni,correo,usuario,contraseña,idTipo_usuario) values (?,?,?,?,?,?)");
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getDNI());
            ps.setString(3, persona.getCorreo());
            ps.setString(4, persona.getUsuario());
            ps.setString(5, persona.getContraseña());
            ps.setInt(6, persona.getIdTipo_usuario());
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error," + ex);
            }
        }
    }

    @Override
    public int VerificarUser(String usuario) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("select count(id) from usuario where usuario=?");
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException ex) {
            return 1;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("error, "+e);
            }
        }
    }

    @Override
    public boolean ComprobarEmail(String correo) {
        //Patrón para validar correo
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = patron.matcher(correo);
        return matcher.find(); //true patrcon correcto, else false patron incorrecto
    }

    @Override
    public boolean IniciarSesion(Persona persona) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("select u.id,u.nombre,u.dni,u.usuario,u.contraseña,u.idTipo_usuario,t.nombre from usuario as u inner join tipo_usuario as t on u.idTipo_usuario = t.id where usuario=? ");
            ps.setString(1, persona.getUsuario());
            rs = ps.executeQuery();
            //si obtenemos datos entra
            if (rs.next()) { //si corresponder el user
                if (persona.getContraseña().equals(rs.getString("contraseña"))) {
                    ps = conexion.prepareStatement("update usuario set ultima_sesion=? where id=?");
                    ps.setString(1, persona.getUltimaconexion());
                    ps.setInt(2, rs.getInt("id"));
                    ps.executeUpdate();
                    persona.setId(rs.getInt("u.id"));
                    persona.setNombre(rs.getString("u.nombre"));
                    persona.setDNI(rs.getString("u.dni"));
                    persona.setIdTipo_usuario(rs.getInt("u.IdTipo_usuario"));
                    persona.setNombreRol(rs.getString("t.nombre"));
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }

}
