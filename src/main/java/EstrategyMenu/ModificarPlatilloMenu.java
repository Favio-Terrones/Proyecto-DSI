/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstrategyMenu;

import Clases.Platillo;
import DAO.Conexion;
import VistaChef.ModificarMenuSemanal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erick
 */
public class ModificarPlatilloMenu extends Conexion implements AlgoritmoMenu {

    PreparedStatement ps;
    ResultSet rs;
    ModificarMenuSemanal vistaModificarMenuSemanal = ModificarMenuSemanal.getInstancia();

    @Override
    public boolean AccionesMenuARealizar(Platillo platillo) {
        platillo.setIdPlatillo(vistaModificarMenuSemanal.Id.getText());
        platillo.setCodigo(vistaModificarMenuSemanal.Codigo.getText());
        platillo.setDiaP((String) vistaModificarMenuSemanal.CbDiaModificar.getSelectedItem());
        platillo.setNombreP(vistaModificarMenuSemanal.Nombre.getText());
        platillo.setPrecioP(Float.valueOf(vistaModificarMenuSemanal.Precio.getText()));
        platillo.setObservaciones(vistaModificarMenuSemanal.Observaciones.getText());
        platillo.setDisponibilidad(vistaModificarMenuSemanal.Disponibilidad.getText());
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("update platillo set codigoPlatillo=?,diaPlatillo=?,nombrePlatillo=?,precioPlatillo=?,observacionesPlatillo=?,disponibilidadPlatillo=? where idPlatillo=?");
            ps.setString(1, platillo.getCodigo());
            ps.setString(2, platillo.getDiaP());
            ps.setString(3, platillo.getNombreP());
            ps.setFloat(4, platillo.getPrecioP());
            ps.setString(5, platillo.getObservaciones());
            ps.setString(6, platillo.getDisponibilidad());
            ps.setString(7, platillo.getIdPlatillo());
            int resultado = ps.executeUpdate();
            return resultado > 0;
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

}
