/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstrategyPlatillos;

import Clases.Platillo;
import DAO.Conexion;
import VistaChef.ModificarPlatillo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erick
 */
public class EliminarPlatillos extends Conexion implements AlgoritmoPlatillo{
    
    PreparedStatement ps;
    ResultSet rs;
    ModificarPlatillo vistaModificarPlatillo = ModificarPlatillo.getInstancia();
    
    @Override
    public boolean AccionesARealizar(Platillo platillo) {
        Connection conexion = getConnection();
        platillo.setCodigo(vistaModificarPlatillo.Codigo.getText());
        try {     
            ps = conexion.prepareStatement("delete from platillo where codigoPlatillo=?");
            ps.setString(1, platillo.getCodigo());   
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


/*    @Override
    public int VerficarCodigo(String platillo) {
        return 0;
    }*/
    
}
