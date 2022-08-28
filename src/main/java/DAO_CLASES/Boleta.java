/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_CLASES;

import Clases.CarritoPedido;

/**
 *
 * @author Erick
 */
public class Boleta extends CarritoPedido {
    String EstadoPedido;
    String fecha;
    int NumMeza;
    
    private static Boleta instancia;
    public void Boleta() {
        
    }
    public static Boleta getInstancia() {
        if(instancia == null) {
            instancia = new Boleta();
        }
        return instancia;
    }

    public int getNumMeza() {
        return NumMeza;
    }

    public void setNumMeza(int NumMeza) {
        this.NumMeza = NumMeza;
    }
    
    
    
    public String getEstadoPedido() {
        return EstadoPedido;
    }

    public void setEstadoPedido(String EstadoPedido) {
        this.EstadoPedido = EstadoPedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    
    
    
}
