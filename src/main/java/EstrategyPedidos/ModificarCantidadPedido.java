/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstrategyPedidos;

import Clases.CarritoPedido;
import DAO.Conexion;
import VistaCliente.RealizarPedidoCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erick
 */
public class ModificarCantidadPedido extends Conexion implements AlgoritmoPedido {

    PreparedStatement ps;
    ResultSet rs;
    RealizarPedidoCliente vistaRealizarPedido = RealizarPedidoCliente.getInstancia();

    @Override
    public boolean AccionesPedidoARealizar(CarritoPedido carrito) {
        Connection conexion = getConnection();
        float precio = Float.parseFloat(vistaRealizarPedido.Precio.getText());
        int cant = Integer.parseInt(vistaRealizarPedido.Cantidad.getText());

        carrito.setIdPedido(Integer.parseInt(vistaRealizarPedido.IdPedido.getText()));
        carrito.setCantidad(3);
        carrito.setCantidad(Integer.parseInt(vistaRealizarPedido.Cantidad.getText()));
        carrito.setPrecioTotalPlatillo(cant*precio);
        try {
            ps = conexion.prepareStatement("update Carrito set idCarrito=?,"
                    + "cantidadCarrito=?, precioTotalPlatilloCarrito=? where idCarrito=?");
            ps.setInt(1, carrito.getIdPedido());
            ps.setInt(2, carrito.getCantidad());
            ps.setFloat(3, carrito.getPrecioTotalPlatillo());
            ps.setInt(4, carrito.getIdPedido());

            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            System.err.println("Error1, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error2," + ex);
            }
        }
    }

}
