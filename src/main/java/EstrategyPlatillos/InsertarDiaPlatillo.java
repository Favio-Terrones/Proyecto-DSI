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
public class InsertarDiaPlatillo extends Conexion implements AlgoritmoPlatillo {

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
            ps = conexion.prepareStatement("insert into platillo (codigoPlatillo,nombrePlatillo,diaPlatillo,precioPlatillo,observacionesPlatillo,disponibilidadPlatillo) values (?,?,?,?,?,?)");
            ps.setString(1, platillo.getCodigo());
            ps.setString(2, platillo.getNombreP());
            ps.setString(3, platillo.getDiaP());
            ps.setFloat(4, platillo.getPrecioP());
            ps.setString(5, platillo.getObservaciones());
            ps.setString(6, platillo.getDisponibilidad());
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
