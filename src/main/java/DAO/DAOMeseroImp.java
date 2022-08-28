/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO_CLASES.Boleta;
import DAO_Interfas.DAOMesero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erick
 */
public class DAOMeseroImp extends Conexion implements DAOMesero {

    PreparedStatement ps, px;
    ResultSet rs;

    @Override
    public boolean TomarPedido(Boleta boleta) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("insert into boleta (dniBoleta, mesaBoleta, diaBoleta, nombreBoleta, precioBoleta, cantidadBoleta, "
                    + "precioTotalPlatilloBoleta, estadoPedidoBoleta, estadoPagoDespuesBoleta, clienteBoleta) "
                    + "values (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, boleta.getDNI());
            ps.setInt(2, boleta.getNumMeza());
            ps.setString(3, boleta.getDiaP());
            ps.setString(4, boleta.getNombreP());
            ps.setFloat(5, boleta.getPrecioP());
            ps.setInt(6, boleta.getCantidad());
            ps.setFloat(7, boleta.getPrecioTotalPlatillo());
            ps.setString(8, boleta.getEstadoPedido());
            ps.setString(9, boleta.getEstadoPagoDespues());
            ps.setString(10, boleta.getModalidad());

            px = conexion.prepareStatement("insert into carritoVirtual (mesacarritoVirtual, diacarritoVirtual, nombrecarritoVirtual, preciocarritoVirtual, cantidadcarritoVirtual, "
                    + "precioTotalPlatillocarritoVirtual) "
                    + "values (?,?,?,?,?,?)");
            px.setInt(1, boleta.getNumMeza());
            px.setString(2, boleta.getDiaP());
            px.setString(3, boleta.getNombreP());
            px.setFloat(4, boleta.getPrecioP());
            px.setInt(5, boleta.getCantidad());
            px.setFloat(6, boleta.getPrecioTotalPlatillo());

            int r = px.executeUpdate();
            int resultado = ps.executeUpdate();
            return resultado > 0 && r > 0;
        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error12," + ex);
            }
        }
    }

    @Override
    public boolean ImprimirBoleta(String numMeza) {
        Connection conexion = getConnection();
        try {
            px = conexion.prepareStatement("insert into boletaAtender(dniAtender, diaAtender, nombreAtender, cantidadAtender,"
                    + "estadoPedidoAtender, estadoPagoDespuesAtender) select dniBoleta, diaBoleta, nombreBoleta, cantidadBoleta, estadoPedidoBoleta, estadoPagoDespuesBoleta"
                    + " from boleta where mesaBoleta=?");
            px.setString(1, numMeza);
            //pasar de boleta a boleta auxiliar
            ps = conexion.prepareStatement("insert into boletaAuxiliar (mesaBoletaAuxiliar, diaBoletaAuxiliar, nombreBoletaAuxiliar, precioBoletaAuxiliar,"
                    + "cantidadBoletaAuxiliar, precioTotalPlatilloBoletaAuxiliar) select  mesacarritoVirtual, diacarritoVirtual, nombrecarritoVirtual,preciocarritoVirtual, cantidadcarritoVirtual,precioTotalPlatillocarritoVirtual from carritoVirtual where mesacarritoVirtual=?");
            ps.setString(1, numMeza);

            int res = px.executeUpdate();
            int r = ps.executeUpdate();
            return res > 0 && r > 0;

        } catch (SQLException es) {
            System.err.println("Error, " + es.getMessage());
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error12," + ex);
            }
        }
    }

    @Override
    public boolean EliminarCarritoVirtual(int numero) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("delete from carritoVirtual where mesacarritoVirtual=?");
            ps.setInt(1, numero);

            px = conexion.prepareStatement("delete from boletaAuxiliar where mesaBoletaAuxiliar=?");
            px.setInt(1, numero);
            int r = px.executeUpdate();
            int resultado = ps.executeUpdate();
            return resultado > 0 && r > 0;
        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
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
    public boolean EliminarPedido(String id) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("delete from boleta where idBoleta=?");
            ps.setString(1, id);
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            System.err.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException es) {
                System.err.println("Error, " + es);
            }
        }
    }

}
