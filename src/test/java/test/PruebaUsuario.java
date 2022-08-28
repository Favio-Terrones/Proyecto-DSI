/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DAO.DAOPersonaImp;
import DAO_Interfas.DAOPersona;
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
public class PruebaUsuario {
    
    DAOPersona dao = new DAOPersonaImp();
    
    public PruebaUsuario() {
    }
    
 
    
     @Test
    public void Probar() {
       
        //Como ya hay un erick retorna falso;
       int valor = dao.VerificarUser("erick");
       assertFalse(funcion(valor));
       // System.out.println(valor);
    }

     public boolean funcion(int valor) {
      if(valor==0) { //no hay con ese usuario
          return true;
      } else {
          return false; //este devuelve
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
