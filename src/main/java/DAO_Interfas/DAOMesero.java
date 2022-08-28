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
public interface DAOMesero {
    public boolean TomarPedido(Boleta boleta);
    public boolean ImprimirBoleta(String numMeza);
    public boolean EliminarCarritoVirtual(int numero);
    public boolean EliminarPedido(String id);
}
