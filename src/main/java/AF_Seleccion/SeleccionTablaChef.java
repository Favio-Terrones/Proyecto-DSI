/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AF_Seleccion;

import AF_Factory.AbstractFactory;
import AF_Tablas.TablasChef;
import AF_Factory.FactoryMetodosTablasChef;
import AF_Factory.FactoryMetodosTablasCliente;
import AF_Factory.FactoryMetodosTablasMesero;

/**
 *
 * @author Erick
 */
public class SeleccionTablaChef implements AbstractFactory {

    @Override
    public FactoryMetodosTablasChef getTablaChef(String tipo) {
        switch (tipo) {
            case "PlatilloRegistradosChef":
                return new TablasChef();
            case "PlanificarMenuChef":
                return new TablasChef();
            case "ModificarPlatilloChef":
                return new TablasChef();
            case "ModificarMenuChef":
                return new TablasChef();
            case "AtenderPedidoChef":
                return new TablasChef();
            case "PedidoEnProcesoChef":
                return new TablasChef();
            default:
                return null;
        }
    }

    @Override
    public FactoryMetodosTablasCliente getTablaCliente(String tipo) {
        return null;
    }

    @Override
    public FactoryMetodosTablasMesero getTablaMesero(String tipo) {
        return null;
    }

}
