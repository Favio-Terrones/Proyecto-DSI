/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AF_Seleccion;

import AF_Factory.AbstractFactory;
import AF_Factory.FactoryMetodosTablasChef;
import AF_Factory.FactoryMetodosTablasCliente;
import AF_Factory.FactoryMetodosTablasMesero;
import AF_Tablas.TablasCliente;

/**
 *
 * @author Erick
 */
public class SeleccionTablaCliente implements AbstractFactory {
    
    @Override
    public FactoryMetodosTablasCliente getTablaCliente(String tipo) {
        switch (tipo) {
            case "MenuSemanal":
                return new TablasCliente();
            case "PedidoRealizados":
                return new TablasCliente();
            case "Boleta":
                return new TablasCliente();
            case "BoletaPagada":
                return new TablasCliente();
            default:
                return null;
        }
    }
    
    
    @Override
    public FactoryMetodosTablasChef getTablaChef(String tipo) {
        return null;
    }

    @Override
    public FactoryMetodosTablasMesero getTablaMesero(String tipo) {
        return null;
    }
    
}
