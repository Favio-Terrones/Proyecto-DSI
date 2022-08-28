/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Erick
 */
public class Conexion {
    public static final String URL = "jdbc:mysql://localhost:3306/BrisasMarinas?autoReconnet=true&useSSL=false";
    public static final String usuario = "root";
    public static final String contraseña = "villalobos";
    
    public Connection getConnection() {
        Connection conexion = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(URL, usuario, contraseña);
            //JOptionPane.showMessageDialog(null,"Conexion exitosa");
            
        } catch (Exception ex) {
            System.err.println("Error, "+ex);
           
        }
        return conexion;
        
    }
}
