/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AF_Tablas;

import DAO.Conexion;
import VistaCliente.BoletaVirtual;
import VistaCliente.RealizarPedidoCliente;
import VistaCliente.VerEstadoPedidoCliente;
import VistaCliente.VerMenuSemanalCliente;
import AF_Factory.FactoryMetodosTablasCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Erick
 */
public class TablasCliente implements FactoryMetodosTablasCliente {
    
    VerMenuSemanalCliente vistaVisualizar = VerMenuSemanalCliente.getInstancia();
    RealizarPedidoCliente vistaRealizarPedido = RealizarPedidoCliente.getInstancia();
    VerEstadoPedidoCliente vistaEstadoPedido = VerEstadoPedidoCliente.getInstancia();
    BoletaVirtual vistaBoletaVirtual = BoletaVirtual.getInstancia();
    
    @Override
    public void MenuSemanal() {
        DefaultTableModel modelo = new DefaultTableModel(); 
        vistaVisualizar.tbInformacionPlatillos.setModel(modelo);
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select idPlatillo, codigoPlatillo,diaPlatillo,nombrePlatillo,precioPlatillo,disponibilidadPlatillo from platillo order by diaPlatillo desc");
            rs = ps.executeQuery(); 
            modelo.addColumn("id");
            modelo.addColumn("Codigo");
            modelo.addColumn("Dia");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Disponibilidad");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            int anchos[] = {20, 50, 80, 100, 50, 100};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaVisualizar.tbInformacionPlatillos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
    public void PedidoRealizados() {
        DefaultTableModel modelo = new DefaultTableModel(); 
        vistaRealizarPedido.TablaPedido.setModel(modelo);
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            String Dni = vistaRealizarPedido.DNI.getText();
            ps = conexion.prepareStatement("select idCarrito, dniCarrito, diaCarrito, nombreCarrito, estadoPagoAntesCarrito, precioCarrito, cantidadCarrito, precioTotalPlatilloCarrito from Carrito where dniCarrito=?");
            ps.setString(1, Dni);
            rs = ps.executeQuery();
            modelo.addColumn("ID Pedido");
            modelo.addColumn("DNI");
            modelo.addColumn("Dia");
            modelo.addColumn("Nombre");
            modelo.addColumn("Pago");
            modelo.addColumn("Precio");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Precio Total");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            int anchos[] = {40, 80, 100, 50, 100, 100, 50, 50};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaRealizarPedido.TablaPedido.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
    public void Boleta() {
        DefaultTableModel modelo = new DefaultTableModel();
        vistaEstadoPedido.TablaBoleta.setModel(modelo);
        PreparedStatement ps = null;
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            String Dni = vistaEstadoPedido.DNI.getText();
            ps = conexion.prepareStatement("select  diaBoleta, nombreBoleta, precioBoleta, cantidadBoleta, estadoPedidoBoleta, estadoPagoDespuesBoleta from boleta where dniBoleta=?");
            ps.setString(1, Dni);
            rs = ps.executeQuery();
            modelo.addColumn("Dia");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Estado Pedido");
            modelo.addColumn("Pago");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount(); 
            int anchos[] = {80, 100, 50, 80, 100, 100};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaEstadoPedido.TablaBoleta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
    public void BoletaPagada() {
        DefaultTableModel modelo = new DefaultTableModel();
        vistaBoletaVirtual.tablaBoleta.setModel(modelo);
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            String Dni = vistaBoletaVirtual.DNI.getText();
            ps = conexion.prepareStatement("select  diaBoletaAuxiliar, nombreBoletaAuxiliar, precioBoletaAuxiliar, cantidadBoletaAuxiliar, precioTotalPlatilloBoletaAuxiliar from boletaAuxiliar where dniBoletaAuxiliar=?");
            ps.setString(1, Dni);
            rs = ps.executeQuery();
            modelo.addColumn("Dia");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Precio Total");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount(); 
            int anchos[] = {80, 100, 50, 80, 80};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaBoletaVirtual.tablaBoleta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
