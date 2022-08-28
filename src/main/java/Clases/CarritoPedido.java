/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import EstrategyPedidos.AlgoritmoPedido;

/**
 *
 * @author hp
 */
public class CarritoPedido extends Platillo {

    private AlgoritmoPedido miAlgoritmoPedido;

    public void setAlgoritmoPedido(AlgoritmoPedido a) {
        this.miAlgoritmoPedido = a;
    }

    public boolean AccionesPedido() {
        this.miAlgoritmoPedido.AccionesPedidoARealizar(this);
        return true;
    }

    int idPedido;
    String estadoPagoAntes;
    String estadoPagoDespues;
    String estadoPedido;
    int Cantidad;
    String tipoMozo;
    Float PrecioTotalPlatillo;

    private static CarritoPedido instancia;

    public CarritoPedido() {

    }

    public static CarritoPedido getInstancia() {
        if (instancia == null) {
            instancia = new CarritoPedido();
        }
        return instancia;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Float getPrecioTotalPlatillo() {
        return PrecioTotalPlatillo;
    }

    public void setPrecioTotalPlatillo(Float PrecioTotalPlatillo) {
        this.PrecioTotalPlatillo = PrecioTotalPlatillo;
    }

    public String getEstadoPagoAntes() {
        return estadoPagoAntes;
    }

    public void setEstadoPagoAntes(String estadoPagoAntes) {
        this.estadoPagoAntes = estadoPagoAntes;
    }

    public String getEstadoPagoDespues() {
        return estadoPagoDespues;
    }

    public void setEstadoPagoDespues(String estadoPagoDespues) {
        this.estadoPagoDespues = estadoPagoDespues;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getTipoMozo() {
        return tipoMozo;
    }

    public void setTipoMozo(String tipoMozo) {
        this.tipoMozo = tipoMozo;
    }

}
