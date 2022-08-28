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
public class ModificarPlatillos extends Conexion implements AlgoritmoPlatillo {

    PreparedStatement ps;
    ResultSet rs;
    ModificarPlatillo vistaModificarPlatillo = ModificarPlatillo.getInstancia();
    

    @Override
    public boolean AccionesARealizar(Platillo platillo) {
        Connection conexion = getConnection();
        platillo.setCodigo(vistaModificarPlatillo.Codigo.getText());
        platillo.setNombreP(vistaModificarPlatillo.Nombre.getText());
        platillo.setPrecioP(Float.valueOf(vistaModificarPlatillo.Precio.getText()));
        platillo.setObservaciones(vistaModificarPlatillo.Observaciones.getText());
        platillo.setDisponibilidad((String) vistaModificarPlatillo.CbModificarPlatillo.getSelectedItem());
        try {
            ps = conexion.prepareStatement("update platillo set codigoPlatillo=?,nombrePlatillo=?,precioPlatillo=?,observacionesPlatillo=?,disponibilidadPlatillo=? where codigoPlatillo=?");
            ps.setString(1, platillo.getCodigo());
            ps.setString(2, platillo.getNombreP());
            ps.setFloat(3, platillo.getPrecioP());
            ps.setString(4, platillo.getObservaciones());
            ps.setString(5, platillo.getDisponibilidad());
            ps.setString(6, platillo.getCodigo());

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
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

   /* @Override
    public int VerficarCodigo(String platillo) {
        return 0;
    }*/
}
