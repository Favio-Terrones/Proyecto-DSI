/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DAO.DAOMeseroImp;
import DAO_Interfas.DAOMesero;
import EstrategyPlatillos.AccionesChefPlatillo;
import EstrategyPlatillos.EliminarPlatillos;
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
public class PruebaEliminarBoleta {

    public PruebaEliminarBoleta() {
    }

    @Test
    public void ProbarEliminar() {
        DAOMesero dao = new DAOMeseroImp();
        boolean x = dao.EliminarPedido("4"); //lo elimina
        assertFalse(Funcion(x)); //error
    }

    public boolean Funcion(boolean x) {
        if(x==true) {
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
