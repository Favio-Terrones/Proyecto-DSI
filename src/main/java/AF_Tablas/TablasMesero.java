/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AF_Tablas;

import DAO.Conexion;
import VistaMesero.BoletaPresencial;
import VistaMesero.DatosCliente;
import VistaMesero.TomarPedidoCliente;
import VistaMesero.VerEstadoPedido;
import AF_Factory.FactoryMetodosTablasMesero;
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
public class TablasMesero implements FactoryMetodosTablasMesero {
    
    TomarPedidoCliente vistaPedidoCliente = TomarPedidoCliente.getInstancia();
    VerEstadoPedido vistaVerEstado = VerEstadoPedido.getInstancia();
    BoletaPresencial vistaBoletaPresencial = BoletaPresencial.getInstancia();
    DatosCliente vistaDatosCliente = DatosCliente.getInstancia();
    
    @Override
    public void MenuSemanal() {
        DefaultTableModel modelo = new DefaultTableModel(); 
        vistaPedidoCliente.tbInformacionPlatillos.setModel(modelo);
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
                vistaPedidoCliente.tbInformacionPlatillos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
    public void EstadoPedido() {
       DefaultTableModel modelo = new DefaultTableModel();
        vistaVerEstado.TablaBoleta.setModel(modelo);
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        String campo = vistaVerEstado.NumMesa.getText();
        String where = "";
        if(!"".equals(campo)) {
            where = " and mesaBoleta='"+campo+"'";
        } 
        String cliente = "Cliente presencial";
        String w="where clienteBoleta='"+cliente+"'";
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select idBoleta, mesaBoleta, diaBoleta, nombreBoleta, precioBoleta, cantidadBoleta, estadoPedidoBoleta, estadoPagoDespuesBoleta "
                    + "from boleta "+w+where);
            rs = ps.executeQuery();
            modelo.addColumn("Id");
            modelo.addColumn("Mesa");
            modelo.addColumn("Dia");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Estado Pedido");
            modelo.addColumn("Pago");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            int anchos[] = {30, 50, 80, 100, 50, 80, 100, 100};
            for (int i = 0; i < cantidadColumnas; i++) {
                vistaVerEstado.TablaBoleta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
        vistaBoletaPresencial.TablaBoleta.setModel(modelo);
        PreparedStatement ps = null;
        ResultSet rs = null; 
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            String numMeza = vistaDatosCliente.Num.getText();
            ps = conexion.prepareStatement("select  diaBoletaAuxiliar, nombreBoletaAuxiliar, precioBoletaAuxiliar, cantidadBoletaAuxiliar, precioTotalPlatilloBoletaAuxiliar from boletaAuxiliar where mesaBoletaAuxiliar=?");
            ps.setString(1, numMeza);
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
                vistaBoletaPresencial.TablaBoleta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
