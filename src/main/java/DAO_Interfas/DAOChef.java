/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_Interfas;

import DAO_CLASES.Boleta;

/**
 *
 * @author Erick
 */
public interface DAOChef {
    public int VerificarCodigo(String platillo);
    public int VerificarDiaEnMenu(String dia, String codigo);
    public int VerificarNombre(String nombre);
    public boolean ModificarEstadoBoleta(Boleta boleta);
    public boolean EliminarPedidoDeBoletaAtender(Boleta boleta);
    public boolean PasarAPedidoEnProceso(Boleta boleta);
    public boolean ModificarEstadoBoletaFinal(Boleta boleta);
    public boolean EliminarPedidoDeBoletaEnEspera(Boleta boleta);
}
