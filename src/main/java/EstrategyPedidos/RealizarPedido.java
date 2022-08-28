/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstrategyPedidos;

import Clases.CarritoPedido;
import DAO.Conexion;
import VistaCliente.VerMenuSemanalCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erick
 */
public class RealizarPedido extends Conexion implements AlgoritmoPedido {

    PreparedStatement ps;
    ResultSet rs;
    VerMenuSemanalCliente vistaVisualizar = VerMenuSemanalCliente.getInstancia();

    @Override
    public boolean AccionesPedidoARealizar(CarritoPedido carrito) {
        Connection conexion = getConnection();
        float precio = Float.valueOf(vistaVisualizar.Precio.getText());
        int cantidad = Integer.parseInt(vistaVisualizar.Cantidad.getText());
        float cantidadTotal = precio * cantidad;
        carrito.setDNI(vistaVisualizar.DNICliente.getText());
        carrito.setDiaP(vistaVisualizar.Dia.getText());
        carrito.setNombreP(vistaVisualizar.Nombre.getText());
        carrito.setPrecioP(precio);
        carrito.setCantidad(cantidad);
        carrito.setPrecioTotalPlatillo(cantidadTotal);
        carrito.setTipoMozo("Mozo Virtual");
        carrito.setEstadoPedido("En proceso");
        carrito.setEstadoPagoAntes("Por cancelar");
        carrito.setEstadoPagoDespues("Cancelado");
        try {
            ps = conexion.prepareStatement("insert into Carrito (dniCarrito,diaCarrito,nombreCarrito,precioCarrito,cantidadCarrito,precioTotalPlatilloCarrito,tipoMozoCarrito,"
                    + "estadoPedidoCarrito,estadoPagoAntesCarrito,estadoPagoDespuesCarrito) values (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, carrito.getDNI());
            ps.setString(2, carrito.getDiaP());
            ps.setString(3, carrito.getNombreP());
            ps.setFloat(4, carrito.getPrecioP());
            ps.setInt(5, carrito.getCantidad());
            ps.setFloat(6, (carrito.getPrecioTotalPlatillo()));
            ps.setString(7, carrito.getTipoMozo());
            ps.setString(8, carrito.getEstadoPedido());
            ps.setString(9, carrito.getEstadoPagoAntes());
            ps.setString(10, carrito.getEstadoPagoDespues());
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            System.err.println("Errorasd, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error12," + ex);
            }
        }
    }

}
