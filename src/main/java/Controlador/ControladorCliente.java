/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Clases.CarritoPedido;
import VistaCliente.ClienteVista;
import DAO_CLASES.Cliente;
import DAO_CLASES.Boleta;
import VistaCliente.BoletaVirtual;
import VistaCliente.DatosTarjeta;
import VistaCliente.RealizarPedidoCliente;
import VistaCliente.VerEstadoPedidoCliente;
import VistaCliente.VerMenuSemanalCliente;
import AF_Seleccion.Factory;
import AF_Factory.AbstractFactory;
import AF_Factory.FactoryMetodosTablasCliente;
import DAO.DAOClienteImp;
import DAO_Interfas.DAOCliente;
import EstrategyPedidos.AccionesClientePedido;
import EstrategyPedidos.EliminarPedidos;
import EstrategyPedidos.ModificarCantidadPedido;
import EstrategyPedidos.RealizarPedido;
import VistaMesero.DatosCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick
 */
public class ControladorCliente implements ActionListener {

    VerMenuSemanalCliente vistaVisualizar = VerMenuSemanalCliente.getInstancia();
    RealizarPedidoCliente vistaRealizarPedido = RealizarPedidoCliente.getInstancia();
    DatosTarjeta vistaTarjeta = DatosTarjeta.getInstancia();
    ClienteVista vistaCliente = ClienteVista.getInstancia();
    VerEstadoPedidoCliente vistaEstadoPedido = VerEstadoPedidoCliente.getInstancia();
    BoletaVirtual vistaBoletaVirtual = BoletaVirtual.getInstancia();
    Cliente cliente = Cliente.getInstancia();
    CarritoPedido carrito = CarritoPedido.getInstancia();
    DatosCliente vistaDatos = DatosCliente.getInstancia();
    Boleta boleta = Boleta.getInstancia();
    String tipo = "Cliente";
    DAOCliente dao = new DAOClienteImp();

    private static ControladorCliente instancia;

    private ControladorCliente() {
        vistaVisualizar.Reservar.addActionListener(this);
        vistaRealizarPedido.RealizarPago.addActionListener(this);
        vistaRealizarPedido.Eliminar.addActionListener(this);
        vistaRealizarPedido.Cambiar.addActionListener(this);
        vistaTarjeta.OK.addActionListener(this);
        vistaBoletaVirtual.OK.addActionListener(this);
    }

    public static ControladorCliente getInstancia() {
        if (instancia == null) {
            instancia = new ControladorCliente();
        }
        return instancia;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {

        //RESERVAR PLATILLO DE LA CARTA
        if (ae.getSource() == vistaVisualizar.Reservar) {
            if (vistaVisualizar.txtNamePlatillo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione platillo a reservar");
                LimpiarCantidad();
            } else {
                if (vistaVisualizar.Cantidad.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Inserte una cantidad");
                } else if (vistaVisualizar.Cantidad.getText().equals("0")) {
                    JOptionPane.showMessageDialog(null, "Cantidad no admitida");
                    LimpiarCantidad();
                } else {
                    if (ValidarCantidad(vistaVisualizar.Cantidad.getText())) {
                        int cant = Integer.parseInt(vistaVisualizar.Cantidad.getText());
                        if (vistaVisualizar.Nombre.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Por favor, seleccione un platillo");
                            LimpiarCantidad();
                        } else {
                            if (vistaVisualizar.Obs.getText().equals("No disponible")) {
                                JOptionPane.showMessageDialog(null, "Platillo no disponible por el momento");
                                LimpiarCantidad();
                            } else {
                                if (cant > 3) {
                                    JOptionPane.showMessageDialog(null, "Has excedido la cantidad a comprar");
                                    LimpiarCantidad();
                                } else {
                                    
                                    if (dao.VerificarDiaIdAReservar(vistaVisualizar.Nombre.getText(), vistaVisualizar.Dia.getText(),
                                            vistaVisualizar.DNICliente.getText()) == 0) {
                                        AccionesClientePedido PedidoCliente = new AccionesClientePedido();
                                        PedidoCliente.setAlgoritmoPedido(new RealizarPedido());
                                        if (PedidoCliente.AccionesPedido()) {
                                            JOptionPane.showMessageDialog(null, "Añadido al carrito");
                                            LimpiarDatos();
                                        } else {
                                            JOptionPane.showMessageDialog(null, "OCURRIO UN ERROR");
                                            LimpiarDatos();
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Plaillo ya registrado en ese dia");
                                        LimpiarDatos();
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, digite un número válido");
                        LimpiarCantidad();
                    }
                }
            }
        }

        //PAGAR LO QUE HE SELECCIONADO
        if (ae.getSource() == vistaTarjeta.OK) {
            if (ValidarCantidad(vistaTarjeta.Tarjeta1.getText())) {
                if (ValidarCantidad(vistaTarjeta.Tarjeta2.getText())) {
                    if (ValidarCantidad(vistaTarjeta.Tarjeta3.getText())) {
                        if (ValidarCantidad(vistaTarjeta.Tarjeta4.getText())) {
                            if (vistaTarjeta.Tarjeta1.getText().equals("") || vistaTarjeta.Tarjeta2.equals("") || vistaTarjeta.Tarjeta3.equals("")
                                    || vistaTarjeta.Tarjeta4.equals("") || vistaTarjeta.Mes.getText().equals("") || vistaTarjeta.Año.getText().equals("")
                                    || vistaTarjeta.Seguridad.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Rellene todo los campos, por favor");
                            } else {
                                int num1 = Integer.parseInt(vistaTarjeta.Tarjeta1.getText());
                                int num2 = Integer.parseInt(vistaTarjeta.Tarjeta2.getText());
                                int num3 = Integer.parseInt(vistaTarjeta.Tarjeta3.getText());
                                int num4 = Integer.parseInt(vistaTarjeta.Tarjeta4.getText());
                                if (999 < num1 && num1 < 10000 && 999 < num2 && num2 < 10000 && 999 < num3 && num3 < 10000 && 999 < num4 && num4 < 10000) {
                                    if (ValidarCantidad(vistaTarjeta.Mes.getText()) && ValidarCantidad(vistaTarjeta.Año.getText())) {
                                        int mes = Integer.parseInt(vistaTarjeta.Mes.getText());
                                        int año = Integer.parseInt(vistaTarjeta.Año.getText());
                                        if (mes < 13 && (año > 2021 && año < 2027)) {
                                            if (ValidarCantidad(vistaTarjeta.Seguridad.getText())) {
                                                int cvv = Integer.parseInt(vistaTarjeta.Seguridad.getText());
                                                if (99 < cvv && cvv < 1000) {
                                                    if (dao.PagarPedidoTarjeta(vistaTarjeta.DNI.getText())) {
                                                        JOptionPane.showMessageDialog(null, "PAGO EXITOSO");
                                                        LimpiarDatosTarjeta();
                                                        dao.EliminarTablaPorDNI(vistaTarjeta.DNI.getText());
                                                        AbstractFactory fabrica = Factory.getFactory(tipo);
                                                        FactoryMetodosTablasCliente tablaCliente = fabrica.getTablaCliente("PedidoRealizados");
                                                        tablaCliente.PedidoRealizados();
                                                        vistaTarjeta.setVisible(false);
                                                        JFrameBoletaVirtual();
                                                        vistaBoletaVirtual.DNI.setText(vistaTarjeta.DNI.getText());
                                                        vistaBoletaVirtual.ClienteBoleta.setText(vistaTarjeta.Cliente.getText());
                                                        vistaBoletaVirtual.setVisible(true);
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "No hay ningun registro a pagar");
                                                        RealizarPedidoCliente.DT = null;
                                                        LimpiarDatosTarjeta();
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Numero de seguridad no reconocido");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Numero de seguridad no válido");
                                            }

                                        } else {
                                            JOptionPane.showMessageDialog(null, "Fecha no válida");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Fecha no admitica");
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Numero de tarjeta no admitido");
                                }
                            }
                        }
                    }
                }

            }
            if (!ValidarCantidad(vistaTarjeta.Tarjeta1.getText()) || !ValidarCantidad(vistaTarjeta.Tarjeta2.getText())
                    || !ValidarCantidad(vistaTarjeta.Tarjeta3.getText()) || !ValidarCantidad(vistaTarjeta.Tarjeta4.getText())) {
                JOptionPane.showMessageDialog(null, "Numero de tarjeta no válidos");
            }

        }

        //CAMBIAR CANTIDAD DE PEDIDO
        if (ae.getSource() == vistaRealizarPedido.Cambiar) {

            if (RealizarPedidoCliente.DT == null) {
                if (vistaRealizarPedido.IdPedido.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Seleccione platillo a modificar");
                    vistaRealizarPedido.Cantidad.setText(null);
                } else {
                    if (vistaRealizarPedido.Cantidad.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
                    } else if (vistaRealizarPedido.Cantidad.getText().equals("0")) {
                        JOptionPane.showMessageDialog(null, "No puede modificar a 0");
                        vistaRealizarPedido.Cantidad.setText(null);
                    } else if (ValidarCantidad(vistaRealizarPedido.Cantidad.getText())) {
                        if (Integer.parseInt(vistaRealizarPedido.Cantidad.getText()) > 3) {
                            JOptionPane.showMessageDialog(null, "Cantidad excedida");
                            vistaRealizarPedido.Cantidad.setText(null);
                        } else {
                            AccionesClientePedido ClientePedido = new AccionesClientePedido();
                            ClientePedido.setAlgoritmoPedido(new ModificarCantidadPedido());
                            if (ClientePedido.AccionesPedido()) {
                                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");
                                AbstractFactory fabrica = Factory.getFactory(tipo);
                                FactoryMetodosTablasCliente tablaCliente = fabrica.getTablaCliente("PedidoRealizados");
                                tablaCliente.PedidoRealizados();
                                vistaTarjeta.setVisible(false);
                                LimpiarRealizarPedido();
                            } else {
                                JOptionPane.showMessageDialog(null, "REGISTRO NO MODIFICADO");
                                LimpiarRealizarPedido();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Digite una cantidad válida");
                        vistaRealizarPedido.Cantidad.setText(null);
                    }

                }
            }

        }

        if (ae.getSource() == vistaRealizarPedido.Eliminar) {
            if (RealizarPedidoCliente.DT == null) {
                if (vistaRealizarPedido.IdPedido.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Seleccione platillo a eliminar");
                } else {
                    AccionesClientePedido ChefPedido = new AccionesClientePedido();
                    ChefPedido.setAlgoritmoPedido(new EliminarPedidos());
                    if (ChefPedido.AccionesPedido()) {
                        JOptionPane.showMessageDialog(null, "Eliminado correctamente");
                        AbstractFactory fabrica = Factory.getFactory(tipo);
                        FactoryMetodosTablasCliente tablaCliente = fabrica.getTablaCliente("PedidoRealizados");
                        tablaCliente.PedidoRealizados();
                        vistaTarjeta.setVisible(false);
                        LimpiarRealizarPedido();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo modificar");
                    }
                }
            }

        }

        if (ae.getSource() == vistaBoletaVirtual.OK) {
            dao.EliminarBoleta(vistaBoletaVirtual.DNI.getText());
        }

    }
    
    public void JFrameVisuarlizarDisponibilidadMenu() {
        vistaVisualizar.setTitle("DISPONIBILIDAD DE MENUS");
        vistaVisualizar.setLocationRelativeTo(null);
        vistaVisualizar.setVisible(true);
        vistaVisualizar.Dia.setVisible(false);
        vistaVisualizar.Nombre.setVisible(false);
        vistaVisualizar.Precio.setVisible(false);
        vistaVisualizar.DNICliente.setVisible(false);
        vistaVisualizar.IdPlatillo.setVisible(false);
        vistaVisualizar.Obs.setVisible(false);
    }

    public void JFrameRealizarPedido() {
        vistaRealizarPedido.setTitle("REGISTRAR PEDIDO");
        vistaRealizarPedido.setLocationRelativeTo(null);
        vistaRealizarPedido.setVisible(true);
        vistaRealizarPedido.Precio.setVisible(false);
        vistaRealizarPedido.PrecioTotal.setVisible(false);
        vistaRealizarPedido.IdPedido.setVisible(false);
        vistaRealizarPedido.DNI.setVisible(false);
    }

    public void JFrameDatosTarjeta() {
        vistaTarjeta.setTitle("DATOS TARJETA");
        vistaTarjeta.setLocationRelativeTo(null);
        vistaTarjeta.DNI.setVisible(false);
    }

    public void JFrameEstadoPedido() {
        vistaEstadoPedido.setTitle("ESTADO DE MI PEDIDO");
        vistaEstadoPedido.setLocationRelativeTo(null);
        vistaEstadoPedido.setVisible(true);
        vistaEstadoPedido.DNI.setVisible(false);

    }

    public void JFrameBoletaVirtual() {
        vistaBoletaVirtual.setTitle("BOLETA VIRTUAL");
        vistaBoletaVirtual.setLocationRelativeTo(null);

    }

    public boolean ValidarCantidad(String cadena) {
        return cadena.matches("[0-9]*");
    }
    
    public void LimpiarCantidad() {
        vistaVisualizar.Cantidad.setText(null);
    }

    public void LimpiarDatos() {
        vistaVisualizar.Cantidad.setText(null);
        vistaVisualizar.txtNamePlatillo.setText(null);
        vistaVisualizar.txtDia.setText(null);
        vistaVisualizar.IdPlatillo.setText(null);
    }

    public void LimpiarRealizarPedido() {
        vistaRealizarPedido.Cantidad.setText(null);
        vistaRealizarPedido.IdPedido.setText(null);
        vistaRealizarPedido.Precio.setText(null);
        vistaRealizarPedido.PrecioTotal.setText(null);
        vistaRealizarPedido.Nombre.setText(null);
        vistaRealizarPedido.Dia.setText(null);
    }
    
    public void LimpiarDatosTarjeta() {
        vistaTarjeta.Tarjeta1.setText(null);
        vistaTarjeta.Tarjeta2.setText(null);
        vistaTarjeta.Tarjeta3.setText(null);
        vistaTarjeta.Tarjeta4.setText(null);
        vistaTarjeta.Mes.setText(null);
        vistaTarjeta.Año.setText(null);
        vistaTarjeta.Seguridad.setText(null);
    }
   

}