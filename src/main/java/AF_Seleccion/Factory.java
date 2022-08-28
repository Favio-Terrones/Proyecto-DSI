/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AF_Seleccion;

import AF_Factory.AbstractFactory;

/**
 *
 * @author Erick
 */
public class Factory {
    
    public static AbstractFactory getFactory(String tipoUsuario) {
        switch(tipoUsuario) {
            case "Chef":
                return new SeleccionTablaChef();
            case "Cliente":
                return new SeleccionTablaCliente();
            case "Mesero":
                return new SeleccionTablaMesero();
            default: return null;    
        }
    }
    
}
