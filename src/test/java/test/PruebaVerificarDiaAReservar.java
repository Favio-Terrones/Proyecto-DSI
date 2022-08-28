/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DAO.DAOChefImp;
import DAO.DAOClienteImp;
import DAO_Interfas.DAOChef;
import DAO_Interfas.DAOCliente;
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
public class PruebaVerificarDiaAReservar {
    
    public PruebaVerificarDiaAReservar() {
    }
   

    @Test
    public void Probar() {
        DAOCliente dao = new DAOClienteImp();
        int res = dao.VerificarDiaIdAReservar
        ("Ceviche", "Lunes", "78956421"); 
        assertFalse(Funcion(res));
        //assertFalse(Funcion(res));
    }

    public boolean Funcion(int cantidad) {
        if(cantidad == 1) {
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
