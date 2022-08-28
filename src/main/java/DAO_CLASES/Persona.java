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
public class Persona  {
    
    int id;
    String nombre;
    String DNI;
    String correo;
    String usuario;
    String contraseña;
    String ultimaconexion;
    
    int idTipo_usuario;
    private String nombreRol;
    
    private static Persona instancia;
    public Persona() {
        
    }

    public static Persona getInstancia() {
        if(instancia==null) {
            instancia=new Persona();
        }
        return instancia;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUltimaconexion() {
        return ultimaconexion;
    }

    public void setUltimaconexion(String ultimaconexion) {
        this.ultimaconexion = ultimaconexion;
    }

    public int getIdTipo_usuario() {
        return idTipo_usuario;
    }

    public void setIdTipo_usuario(int idTipo_usuario) {
        this.idTipo_usuario = idTipo_usuario;
    }

    
}