/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_CLASES;

/**
 *
 * @author Erick
 */
public class Cliente extends Persona {
    
    int NumTarjeta;
    int NumSeguridad;
    String FechaV;
    String modalidad;
    
    private static Cliente instancia;

    public Cliente() {
        
    }
    
    public static Cliente getInstancia() {
        if(instancia==null){
            instancia = new Cliente();
        }
        return instancia;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    
    
    
    public String getFechaV() {
        return FechaV;
    }

    public void setFechaV(String FechaV) {
        this.FechaV = FechaV;
    }

    public int getNumTarjeta() {
        return NumTarjeta;
    }

    public void setNumTarjeta(int NumTarjeta) {
        this.NumTarjeta = NumTarjeta;
    }

    public int getNumSeguridad() {
        return NumSeguridad;
    }

    public void setNumSeguridad(int NumSeguridad) {
        this.NumSeguridad = NumSeguridad;
    }
    

 
    
    
}
