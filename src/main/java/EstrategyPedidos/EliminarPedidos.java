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
public class EliminarPedidos extends Conexion implements AlgoritmoPedido{
    
    PreparedStatement ps;
    ResultSet rs;
    RealizarPedidoCliente vistaRealizarPedido = RealizarPedidoCliente.getInstancia();
    
    @Override
    public boolean AccionesPedidoARealizar(CarritoPedido carrito) {
        Connection conexion = getConnection();
        carrito.setIdPedido(Integer.parseInt(vistaRealizarPedido.IdPedido.getText()));
        try {     
            ps = conexion.prepareStatement("delete from Carrito where idCarrito=?");
            ps.setInt(1, carrito.getIdPedido());
            
            int resultado = ps.executeUpdate();
            return resultado>0; //se pudo eliminar
        } catch (SQLException ex) {
            System.err.println("Error, "+ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error,"+ex);
            }
        }
     }
    
}
