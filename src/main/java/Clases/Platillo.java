/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DAO_CLASES.Cliente;
import EstrategyMenu.AlgoritmoMenu;
import EstrategyPedidos.AlgoritmoPedido;
import EstrategyPlatillos.AlgoritmoPlatillo;

/**
 *
 * @author Erick
 */
public abstract class Platillo extends Cliente {
    //////SINO FUNCA QUITARLO
    //private static Platillo instancia;
    
    private AlgoritmoPlatillo miAlgoritmo;
    private AlgoritmoMenu miAlgoritmoMenu;

    
    public void setAlgoritmo(AlgoritmoPlatillo a) {
        this.miAlgoritmo=a;
    }
    
    public boolean Acciones() {
        this.miAlgoritmo.AccionesARealizar(this);
        return true;
    }
    
    public void setAlgoritoMenu(AlgoritmoMenu a) {
        this.miAlgoritmoMenu=a;
    }
    
    public boolean AccionesMenu() {
        this.miAlgoritmoMenu.AccionesMenuARealizar(this);
        return true;
    }
    

    
    
    
    String codigo;
    String nombreP;
    String diaP;
    float precioP;
    String observaciones;
    String disponibilidad;
    String idPlatillo;

    public Platillo() {

    }

    /*public static Platillo getInstancia() {
        if (instancia == null) {
            instancia = new Platillo();
        }
        return instancia;
    }*/
    
    public String getCodigo() {
        return codigo;
    }

    public String getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(String idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getDiaP() {
        return diaP;
    }

    public void setDiaP(String diaP) {
        this.diaP = diaP;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public float getPrecioP() {
        return precioP;
    }

    public void setPrecioP(float precioP) {
        this.precioP = precioP;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

}
