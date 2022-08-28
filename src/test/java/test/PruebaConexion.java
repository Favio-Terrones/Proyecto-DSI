/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Controlador.ControladorPersona;
import DAO.Conexion;
import DAO.DAOPersonaImp;
import DAO_CLASES.Persona;
import DAO_Interfas.DAOPersona;
import EstrategyPlatillos.AccionesChefPlatillo;
import EstrategyPlatillos.EliminarPlatillos;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Erick
 */
public class PruebaConexion {

    DAOPersona dao = new DAOPersonaImp();
    Persona persona = Persona.getInstancia();
    Conexion x = new Conexion();

    public PruebaConexion() {
        String URL = "jdbc:mysql://localhost:3306/BrisasMarinas?autoReconnet=true&useSSL=false";
        String usuario = "root";
        String contraseña = "villalobos";
        x.getConnection();
    }   
    @Test
    public void Probar() {
        assertTrue(Conexion());
    }

     public boolean Conexion() {
        Connection g = x.getConnection();
        if(g!=null){
            return true;
        } else {
            return false;
        }
    }

    @BeforeClass //inicializar 
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

}
