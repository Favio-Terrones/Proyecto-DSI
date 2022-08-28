/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_Interfas;

/**
 *
 * @author Erick
 */
public interface DAOCliente {
    public int VerificarDiaIdAReservar(String nombre, String dia, String dni);
    public boolean PagarPedidoTarjeta(String dni);
    public boolean EliminarTablaPorDNI(String dni);
     public boolean EliminarBoleta(String dni);
}
