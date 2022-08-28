/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO_CLASES.Boleta;
import DAO_Interfas.DAOChef;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erick
 */
public class DAOChefImp extends Conexion implements DAOChef {
    PreparedStatement ps;
    ResultSet rs;
    @Override
    
    public int VerificarCodigo(String platillo) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("select count(idPlatillo) from platillo where codigoPlatillo=?");

            ps.setString(1, platillo);
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
    public int VerificarDiaEnMenu(String dia, String codigo) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("select count(idPlatillo) from platillo where diaPlatillo=? and codigoPlatillo=?");

            ps.setString(1, dia);
            ps.setString(2, codigo);
            
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
    public int VerificarNombre(String nombre) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("select count(idPlatillo) from platillo where nombrePlatillo=?");

            ps.setString(1, nombre);
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
    public boolean ModificarEstadoBoleta(Boleta boleta) {
        Connection conexion = getConnection();
        //Conexion a nuestra BD
        try {
            ps = conexion.prepareStatement("update boleta set estadoPedidoBoleta=? where idBoleta=?");
            ps.setString(1, boleta.getEstadoPedido());
            ps.setInt(2, boleta.getIdPedido());
            int resultado = ps.executeUpdate();
            return resultado > 0;          
        } catch (SQLException ex) {
            System.err.println("Errorasdasd, " + ex);
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
    public boolean EliminarPedidoDeBoletaAtender(Boleta boleta) {
         Connection conexion = getConnection();
        //Conexion a nuestra BD
        try {     
            ps = conexion.prepareStatement("delete from boletaAtender where idAtender=?");
            ps.setInt(1, boleta.getIdPedido());
            
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

    @Override
    public boolean PasarAPedidoEnProceso(Boleta boleta) {
          Connection conexion = getConnection();

        try {
            ps = conexion.prepareStatement("insert into boletaEspera (dniEspera, diaEspera, nombreEspera, cantidadEspera, estadoPedidoEspera) values (?,?,?,?, ?)");
            ps.setString(1, boleta.getDNI());
            ps.setString(2, boleta.getDiaP());
            ps.setString(3, boleta.getNombreP());
            ps.setInt(4, boleta.getCantidad());
            ps.setString(5, boleta.getEstadoPedido());
            
            int r = ps.executeUpdate();

              return r > 0;

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
    public boolean ModificarEstadoBoletaFinal(Boleta boleta) {
        Connection conexion = getConnection();
        //Conexion a nuestra BD
        try {

            ps = conexion.prepareStatement("update boleta set estadoPedidoBoleta=? where dniBoleta=? and diaBoleta=? and nombreBoleta=?");
            ps.setString(1, boleta.getEstadoPedido());
            ps.setString(2, boleta.getDNI());
            ps.setString(3, boleta.getDiaP());
            ps.setString(4, boleta.getNombreP());


            int resultado = ps.executeUpdate();
            return resultado > 0;          
        } catch (SQLException ex) {
            System.err.println("Errorasdasd, " + ex);
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
    public boolean EliminarPedidoDeBoletaEnEspera(Boleta boleta) {
         Connection conexion = getConnection();
        //Conexion a nuestra BD
        try {     
            ps = conexion.prepareStatement("delete from boletaEspera where idEspera=?");
            ps.setInt(1, boleta.getIdPedido());
            
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
