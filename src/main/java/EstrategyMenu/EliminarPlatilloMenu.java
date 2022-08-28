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

/**
 *
 * @author Erick
 */
public class EliminarPlatilloMenu extends Conexion implements AlgoritmoMenu {

    PreparedStatement ps;
    ResultSet rs;
    ModificarMenuSemanal vistaModificarMenuSemanal = ModificarMenuSemanal.getInstancia();
    
    @Override
    public boolean AccionesMenuARealizar(Platillo platillo) {
        Connection conexion = getConnection();
        platillo.setIdPlatillo(vistaModificarMenuSemanal.Id.getText());
        try {
            ps = conexion.prepareStatement("delete from platillo where idPlatillo=?");
            ps.setString(1, platillo.getIdPlatillo());

            int resultado = ps.executeUpdate();
            if (resultado > 0) { //se pudo eliminar
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error, " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                System.err.println("Error," + ex);
            }
        }
    }

}
