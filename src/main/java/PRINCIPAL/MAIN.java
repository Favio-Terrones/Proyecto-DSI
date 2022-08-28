/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PRINCIPAL;

/**
 *
 * @author Erick
 */
import Controlador.ControladorPersona;


public class MAIN {

    public static void main(String[] args) {

        ControladorPersona controlador = ControladorPersona.getInstancia();

        controlador.JFrameIniciar();
        
    }

}
