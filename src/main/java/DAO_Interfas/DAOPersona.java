/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_Interfas;

import DAO_CLASES.Persona;

/**
 *
 * @author Erick
 */
public interface DAOPersona {
    public boolean registrar(Persona persona);
    public int VerificarUser(String usuario);
    public boolean ComprobarEmail(String correo);
    public boolean IniciarSesion(Persona persona);
}
