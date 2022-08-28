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
import AF_Tablas.TablasMesero;

public class SeleccionTablaMesero implements AbstractFactory {

    @Override
    public FactoryMetodosTablasMesero getTablaMesero(String tipo) {
        switch (tipo) {
            case "MenuSemanal":
                return new TablasMesero();
            case "EstadoPedido":
                return new TablasMesero();
            case "BoletaPagada":
                return new TablasMesero();
            default:
                return null;
        }
    }

    @Override
    public FactoryMetodosTablasChef getTablaChef(String tipo) {
        return null;
    }

    @Override
    public FactoryMetodosTablasCliente getTablaCliente(String tipo) {
        return null;
    }
}
