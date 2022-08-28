/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO_Interfas.DAOCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erick
 */
public class DAOClienteImp extends Conexion implements DAOCliente {

    PreparedStatement ps, px, pq;
    ResultSet rs;

    @Override
    public int VerificarDiaIdAReservar(String nombre, String dia, String dni) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("select count(idCarrito) from Carrito where nombreCarrito=? and diaCarrito=? and dniCarrito=?");
            ps.setString(1, nombre);
            ps.setString(2, dia);
            ps.setString(3, dni);
            rs = ps.executeQuery();
            if (rs.next()) { //si retorna 0 no hay, si retorna 1 ya existe
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException ex) {
            return 1;
        }
    }

    @Override
    public boolean PagarPedidoTarjeta(String dni) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("insert into boleta (dniBoleta, diaBoleta, nombreBoleta, precioBoleta, cantidadBoleta, precioTotalPlatilloBoleta, estadoPedidoBoleta, estadoPagoDespuesBoleta,clienteBoleta) select \n"
                    + "dniCarrito, diaCarrito, nombreCarrito, precioCarrito, cantidadCarrito, precioTotalPlatilloCarrito,estadoPedidoCarrito, estadoPagoDespuesCarrito,ClienteCarrito from Carrito where dniCarrito=?");
            ps.setString(1, dni);

            px = conexion.prepareStatement("insert into boletaAtender (dniAtender, diaAtender, nombreAtender, cantidadAtender, estadoPedidoAtender, estadoPagoDespuesAtender) select \n"
                    + "dniCarrito, diaCarrito, nombreCarrito, cantidadCarrito, estadoPedidoCarrito, estadoPagoDespuesCarrito from Carrito where dniCarrito=?");
            px.setString(1, dni);

            pq = conexion.prepareStatement("insert into boletaAuxiliar (dniBoletaAuxiliar, diaBoletaAuxiliar, nombreBoletaAuxiliar, precioBoletaAuxiliar,"
                    + "cantidadBoletaAuxiliar, precioTotalPlatilloBoletaAuxiliar) select dniCarrito, diaCarrito, nombreCarrito,precioCarrito, cantidadCarrito,precioTotalPlatilloCarrito from Carrito where dniCarrito=?");
            pq.setString(1, dni);

            int r = ps.executeUpdate();
            int s = px.executeUpdate();
            int t = pq.executeUpdate();
            return r > 0 && s > 0 && t > 0;
        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error," + ex);
            }
        }
    }

    @Override
    public boolean EliminarTablaPorDNI(String dni) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("delete from Carrito where dniCarrito=?");
            ps.setString(1, dni);
            int r = ps.executeUpdate();
            return r > 0;
        } catch (SQLException ex) {
            System.err.println("Error," + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error, " + ex);
            }
        }
    }

    @Override
    public boolean EliminarBoleta(String dni) {
         Connection conexion = getConnection();
         try {
             ps = conexion.prepareStatement("delete from boletaAuxiliar where dniBoletaAuxiliar=?");
             ps.setString(1, dni); 
             int r = ps.executeUpdate();
             return r>0;  
         } catch (SQLException ex) {
             System.err.println("Error, "+ex);
             return false;
         } finally {
             try {
                 conexion.close();
             } catch (SQLException es) {
                 System.err.println("Error, "+ es);
             }
         }
    }

}
