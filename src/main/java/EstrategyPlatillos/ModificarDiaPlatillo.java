/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstrategyPlatillos;

import Clases.Platillo;
import DAO.Conexion;
import VistaChef.PlanificarMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erick
 */
public class ModificarDiaPlatillo extends Conexion implements AlgoritmoPlatillo {

    PreparedStatement ps;
    ResultSet rs;
    PlanificarMenu vistaPlanificar = PlanificarMenu.getInstancia();

    @Override
    public boolean AccionesARealizar(Platillo platillo) {
        Connection conexion = getConnection();
        platillo.setCodigo(vistaPlanificar.Codigo.getText());
        platillo.setDiaP((String) vistaPlanificar.CbDia.getSelectedItem());
        platillo.setNombreP(vistaPlanificar.Nombre.getText());
        platillo.setPrecioP(Float.valueOf(vistaPlanificar.Precio.getText()));
        platillo.setDisponibilidad(vistaPlanificar.Disponibilidad.getText());
        platillo.setObservaciones(vistaPlanificar.Observaciones.getText());
        try {
            ps = conexion.prepareStatement("update platillo set codigoPlatillo=?,diaPlatillo=?,nombrePlatillo=?,precioPlatillo=?,observacionesPlatillo=?,disponibilidadPlatillo=? where codigoPlatillo=?");
            ps.setString(1, platillo.getCodigo());
            ps.setString(2, platillo.getDiaP());
            ps.setString(3, platillo.getNombreP());
            ps.setFloat(4, platillo.getPrecioP());
            ps.setString(5, platillo.getObservaciones());
            ps.setString(6, platillo.getDisponibilidad());
            ps.setString(7, platillo.getCodigo());

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
