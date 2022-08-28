/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO_CLASES.Persona;
import DAO.DAOPersonaImp;
import DAO_Interfas.DAOPersona;
import VistaChef.ChefVista;
import VistaCliente.ClienteVista;
import Vista.IniciarSesion;
import Vista.RegistrarUsuario;
import VistaMesero.MeseroVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick
 */
public class ControladorPersona implements ActionListener {

    RegistrarUsuario vistaRegistrar = RegistrarUsuario.getInstancia();
    IniciarSesion vistaIniciar = IniciarSesion.getInstancia();
    ChefVista vistaChef = ChefVista.getInstancia();
    ClienteVista vistaCliente = ClienteVista.getInstancia();
    MeseroVista vistaMesero = MeseroVista.getInstancia();
    Persona persona = Persona.getInstancia();
    DAOPersona dao = new DAOPersonaImp();

    private static ControladorPersona instancia;

    private ControladorPersona() {
        vistaRegistrar.Registrar.addActionListener(this);
        vistaIniciar.Iniciar.addActionListener(this);
        vistaIniciar.NewAcc.addActionListener(this);
    }

    public static ControladorPersona getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPersona();
        }
        return instancia;
    }

    public void JFrameRegistrar() {
        vistaRegistrar.setTitle("REGISTRAR");
        vistaRegistrar.setLocationRelativeTo(null);

    }

    public void JFrameIniciar() {
        vistaIniciar.setTitle("INICIAR SESION");
        vistaIniciar.setLocationRelativeTo(null);
        vistaIniciar.setVisible(true);

    }

    public void JFrameCliente() {
        vistaCliente.setTitle("BRISAS MARINAS - CLIENTE");
        vistaCliente.setLocationRelativeTo(null);
        vistaCliente.setVisible(true);
        vistaCliente.txtNombreCliente.setText(persona.getNombre());
        vistaCliente.txtRolCliente.setText(persona.getNombreRol());
        vistaCliente.txtDNICliente.setText(persona.getDNI());
    }

    public void JFrameChef() {
        vistaChef.setTitle("BRISAS MARINAS - CHEF");
        vistaChef.setLocationRelativeTo(null);
        vistaChef.setVisible(true);
        vistaChef.txtNombreChef.setText(persona.getNombre());
        vistaChef.txtRolChef.setText(persona.getNombreRol());
        vistaChef.txtDNIChef.setText(persona.getDNI());
    }

    public void JFrameMesero() {
        vistaMesero.setTitle("BRISAS MARINAS - MESERO");
        vistaMesero.setLocationRelativeTo(null);
        vistaMesero.setVisible(true);
        vistaMesero.txtNombre.setText(persona.getNombre());
        vistaMesero.txtRol.setText(persona.getNombreRol());
        vistaMesero.txtDNI.setText(persona.getDNI());
    }

    public boolean ValidarDNI(String cadena) {
        return cadena.matches("[0-9]*");
    }
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        //REGISTRAR USUARIOS
        
        
        if (ae.getSource() == vistaRegistrar.Registrar) {
            String con = new String(vistaRegistrar.Pass.getPassword());
            String conc = new String(vistaRegistrar.CPass.getPassword());
            if (vistaRegistrar.Nombre.getText().equals("") || vistaRegistrar.Dni.getText().equals("")
                    || vistaRegistrar.Correo.getText().equals("") || vistaRegistrar.Usuario.getText().equals("") || con.equals("") || conc.equals("")) {
                JOptionPane.showMessageDialog(null, "LLENE TODO LOS CAMPOS");
            } else {
                if (con.equals(conc)) {
                    if (dao.VerificarUser(vistaRegistrar.Usuario.getText()) == 0) {
                        if (ValidarDNI(vistaRegistrar.Dni.getText())) {
                            int dni = Integer.parseInt(vistaRegistrar.Dni.getText());
                            if (9999999 < dni && dni < 100000000) {
                                if (dao.ComprobarEmail(vistaRegistrar.Correo.getText())) {
                                    persona.setNombre(vistaRegistrar.Nombre.getText());
                                    persona.setDNI(vistaRegistrar.Dni.getText());
                                    persona.setCorreo(vistaRegistrar.Correo.getText());
                                    persona.setUsuario(vistaRegistrar.Usuario.getText());
                                    persona.setContraseña(con);
                                    persona.setIdTipo_usuario(1);
                                    if (dao.registrar(persona)) {
                                        JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO");
                                        Limpiar();
                                        vistaRegistrar.setVisible(false);
                                        vistaIniciar.setVisible(true);
                                        IniciarSesion.RU = null;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "REGISTRO ERRADO");
                                        Limpiar();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "CORREO NO ES VALIDO");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "DNI NO REONOCIDO");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "DNI NO ADMITIDO");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "YA EXISTE ESE USUARIO");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "CONTRASEÑAS NO CONICIDEN");
                }
            }
        }

        if (ae.getSource() == vistaIniciar.Iniciar) {
            if (IniciarSesion.RU == null) {
                Date date = new Date();
                DateFormat fechahora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String contraseña = new String(vistaIniciar.Pass.getPassword());
                if (vistaIniciar.User.getText().equals("") || contraseña.equals("")) {
                    JOptionPane.showMessageDialog(null, "RELLENE TODO LOS CAMPOS");
                } else {
                    persona.setUsuario(vistaIniciar.User.getText());
                    persona.setContraseña(contraseña);
                    persona.setUltimaconexion(fechahora.format(date));

                    if (dao.IniciarSesion(persona)) {
                        switch (persona.getIdTipo_usuario()) {
                            case 1: //CLIENTE
                                JFrameCliente();
                                vistaIniciar.setVisible(false);
                                LimpiarIniciar();
                                break;
                            case 2: //CHEF
                                JFrameChef();
                                vistaIniciar.setVisible(false);
                                LimpiarIniciar();
                                break;
                            case 3:
                                JFrameMesero();
                                vistaIniciar.setVisible(false);
                                LimpiarIniciar();
                                break;
                            default:
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectas");
                    }
                }
            }

        }

    }

    public void LimpiarIniciar() {
        vistaIniciar.User.setText(null);
        vistaIniciar.Pass.setText(null);
    }

    public void Limpiar() {
        vistaRegistrar.Nombre.setText(null);
        vistaRegistrar.Dni.setText(null);
        vistaRegistrar.Correo.setText(null);
        vistaRegistrar.Nombre.setText(null);
        vistaRegistrar.Usuario.setText(null);
        vistaRegistrar.Pass.setText(null);
        vistaRegistrar.CPass.setText(null);
    }
}
