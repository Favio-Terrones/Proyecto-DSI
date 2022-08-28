/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Clases.Platillo;
import DAO.DAOChefImp;
import DAO_Interfas.DAOChef;
import EstrategyPedidos.AccionesClientePedido;
import EstrategyPedidos.ModificarCantidadPedido;
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
public class PruebaVerificarDiaEnMenu {

    public PruebaVerificarDiaEnMenu() {
    }

    @Test
    public void Probar() {
        DAOChef dao = new DAOChefImp();
        int res = dao.VerificarDiaEnMenu("Lunes", "001"); //retorna 1 
        //assertTrue(Funcion(res));
        assertFalse(Funcion(res));
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
