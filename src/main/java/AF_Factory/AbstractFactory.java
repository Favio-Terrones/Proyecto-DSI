/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AF_Factory;

import AF_Factory.FactoryMetodosTablasChef;
import AF_Factory.FactoryMetodosTablasCliente;
import AF_Factory.FactoryMetodosTablasMesero;

/**
 *
 * @author Erick
 */
public interface AbstractFactory {
    FactoryMetodosTablasChef getTablaChef(String tipo);
    FactoryMetodosTablasCliente getTablaCliente(String tipo);
    FactoryMetodosTablasMesero getTablaMesero(String tipo);
}
