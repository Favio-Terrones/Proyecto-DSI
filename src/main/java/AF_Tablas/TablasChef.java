/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AF_Tablas;

import DAO.Conexion;
import VistaChef.AdicionarPlatillo;
import VistaChef.AtenderPedidoVirtual;
import VistaChef.ModificarMenuSemanal;
import VistaChef.ModificarPlatillo;
import VistaChef.PedidoEnProceso;
import VistaChef.PlanificarMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import AF_Factory.FactoryMetodosTablasChef;

/**
 *
 * @author Erick
 */
public class TablasChef implements FactoryMetodosTablasChef {

    AdicionarPlatillo vistaAdicionar = AdicionarPlatillo.getInstancia();
    PlanificarMenu vistaPlanificar = PlanificarMenu.getInstancia();
    ModificarPlatillo vistaModificarPlatillo = ModificarPlatillo.getInstancia();
    ModificarMenuSemanal vistaModificarMenuSemanal = ModificarMenuSemanal.getInstancia();
    AtenderPedidoVirtual vistaAtender = AtenderPedidoVirtual.getInstancia();
    PedidoEnProceso vistaPedidoEnProceso = PedidoEnProceso.getInstancia();

    @Override

    public void PlatilloRegistrados() {
        DefaultTableModel modelo = new DefaultTableModel(); 
        vistaAdicionar.TablaPlatillo.setModel(modelo);
        PreparedStatement ps = null;
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select distinct codigoPlatillo, nombrePlatillo, precioPlatillo, observacionesPlatillo, disponibilidadPlatillo from platillo ");
            rs = ps.executeQuery(); 

            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Observaciones");
            modelo.addColumn("Disponibilidad");

            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            int anchos[] = {50, 80, 50, 100, 100};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaAdicionar.TablaPlatillo.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) { 
                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            vistaAdicionar.NombreBuscar.setText(null);

        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
        }
    }

    @Override
    public void PlanificarMenu() {
        DefaultTableModel modelo = new DefaultTableModel(); 
        vistaPlanificar.TablaPlatillo.setModel(modelo);
        String campo = vistaPlanificar.CodigoB.getText();
        String where = "";
        if (!"".equals(campo)) { 
            where = "where codigoPlatillo='" + campo + "'";
        }
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select codigoPlatillo,diaPlatillo,nombrePlatillo,precioPlatillo,observacionesPlatillo,disponibilidadPlatillo from platillo " + where);
            rs = ps.executeQuery();
            modelo.addColumn("Codigo");
            modelo.addColumn("Dia");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Observaciones");
            modelo.addColumn("Disponibilidad");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            int anchos[] = {70, 60, 100, 50, 100, 100};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaPlanificar.TablaPlatillo.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) { 
                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            vistaPlanificar.CodigoB.setText(null);
        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
        }
    }
    
    @Override
    public void ModificarPlatillo() {
        DefaultTableModel modelo = new DefaultTableModel(); 
        vistaModificarPlatillo.TablaPlatillo.setModel(modelo);
        String campo = vistaModificarPlatillo.CodigoB.getText();
        String where = "";
        if (!"".equals(campo)) { 
            where = "where codigoPlatillo='" + campo + "'";
        }
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select distinct codigoPlatillo, nombrePlatillo, precioPlatillo, observacionesPlatillo, disponibilidadPlatillo from platillo " + where);
            rs = ps.executeQuery(); 
            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Observaciones");
            modelo.addColumn("Disponibilidad");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            int anchos[] = {70, 100, 50, 100, 100};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaModificarPlatillo.TablaPlatillo.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) { 
                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            vistaModificarPlatillo.CodigoB.setText(null);

        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
        }
    }

    @Override
    public void ModificarMenu() {
        DefaultTableModel modelo = new DefaultTableModel(); //agregar las filas a mi tabla
        vistaModificarMenuSemanal.tbInformacionPlatillos.setModel(modelo);
        String where = "";
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select idPlatillo, codigoPlatillo,diaPlatillo,nombrePlatillo,precioPlatillo,disponibilidadPlatillo,observacionesPlatillo from platillo " + where);
            rs = ps.executeQuery(); 
            modelo.addColumn("N°");
            modelo.addColumn("Codigo");
            modelo.addColumn("Dia");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Disponibilidad");
            modelo.addColumn("Observaciones");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount(); 
            int anchos[] = {10, 70, 100, 50, 100, 100, 80};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaModificarMenuSemanal.tbInformacionPlatillos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) { 
                Object fila[] = new Object[cantidadColumnas]; 
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
        }
    }

    @Override
    public void AtenderPedido() {
        DefaultTableModel modelo = new DefaultTableModel(); 
        vistaAtender.TablaPedidosEspera.setModel(modelo);
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select  idAtender, dniAtender, diaAtender, nombreAtender, cantidadAtender, estadoPedidoAtender, estadoPagoDespuesAtender from boletaAtender");
            rs = ps.executeQuery();
            modelo.addColumn("Id");
            modelo.addColumn("Dni");
            modelo.addColumn("Dia");
            modelo.addColumn("Nombre");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Estado Pedido");
            modelo.addColumn("Pago");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount(); 
            int anchos[] = {50, 80, 80, 100, 50, 100, 100};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaAtender.TablaPedidosEspera.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) { 
                Object fila[] = new Object[cantidadColumnas]; 
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
        }
    }

    @Override
    public void PedidoEnProceso() {
        DefaultTableModel modelo = new DefaultTableModel(); 
        vistaPedidoEnProceso.TablaPedidoProceso.setModel(modelo);
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select  idEspera, dniEspera, diaEspera, nombreEspera, cantidadEspera, estadoPedidoEspera from boletaEspera");
            rs = ps.executeQuery();
            modelo.addColumn("Id");
            modelo.addColumn("Dni");
            modelo.addColumn("Dia");
            modelo.addColumn("Nombre");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Estado Pedido");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount(); 
            int anchos[] = {50, 80, 80, 100, 50, 100};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaPedidoEnProceso.TablaPedidoProceso.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object fila[] = new Object[cantidadColumnas]; 
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
        }
    }
  
    
}
