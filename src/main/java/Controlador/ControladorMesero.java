/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO_CLASES.Boleta;
import VistaMesero.BoletaPresencial;
import VistaMesero.DatosCliente;
import VistaMesero.MeseroVista;
import VistaMesero.TomarPedidoCliente;
import VistaMesero.VerEstadoPedido;
import AF_Seleccion.Factory;
import AF_Factory.AbstractFactory;
import AF_Factory.FactoryMetodosTablasMesero;
import DAO.DAOMeseroImp;
import DAO_Interfas.DAOMesero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick
 */
public class ControladorMesero implements ActionListener {

    Boleta boleta = Boleta.getInstancia();
    TomarPedidoCliente vistaPedidoCliente = TomarPedidoCliente.getInstancia();
    VerEstadoPedido vistaVerEstado = VerEstadoPedido.getInstancia();
    MeseroVista vistaMesero = MeseroVista.getInstancia();
    DatosCliente vistaDatosCliente = DatosCliente.getInstancia();
    BoletaPresencial vistaBoletaPresencial = BoletaPresencial.getInstancia();
    DatosCliente vistaDatos = DatosCliente.getInstancia();
    DAOMesero dao = new DAOMeseroImp();

    private static ControladorMesero instancia;

    private ControladorMesero() {
        vistaPedidoCliente.Reservar.addActionListener(this);
        vistaPedidoCliente.RealizarPago.addActionListener(this);
        vistaDatosCliente.PAGAR.addActionListener(this);
        vistaBoletaPresencial.OK.addActionListener(this);
        vistaVerEstado.EntregarPedido.addActionListener(this);
    }

    public static ControladorMesero getInstancia() {
        if (instancia == null) {
            instancia = new ControladorMesero();
        }
        return instancia;
    }

    public void JFrameTomarPedidoCliente() {
        vistaPedidoCliente.setTitle("REALIZAR PEDIDO DEL CLIENTE");
        vistaPedidoCliente.setLocationRelativeTo(null);
        vistaPedidoCliente.setVisible(true);
        vistaPedidoCliente.Precio.setVisible(false);
        vistaPedidoCliente.Nombre.setVisible(false);
        vistaPedidoCliente.Dia.setVisible(false);
        vistaPedidoCliente.IdPlatillo.setVisible(false);
    }

    public void JFrameEstadoPedido() {
        vistaVerEstado.setTitle("ESPTADO PEDIDO");
        vistaVerEstado.setLocationRelativeTo(null);
        vistaVerEstado.setVisible(true);
        vistaVerEstado.Id.setVisible(false);
        vistaVerEstado.Estado.setVisible(false);
    }

    public void JFrameDatosCliente() {
        vistaDatosCliente.setTitle("DATOS DEL CLIENTE");
        vistaDatosCliente.setLocationRelativeTo(null);
        vistaDatosCliente.setVisible(true);
        vistaDatosCliente.Num.setVisible(false);
    }

    public void JFrameBoletaPresencial() {
        vistaBoletaPresencial.setTitle("BOLETA CLIENTE PRESENCIAL");
        vistaBoletaPresencial.setLocationRelativeTo(null);
        vistaBoletaPresencial.setVisible(true);
    }

    public boolean ValidarCantidad(String cadena) {
        return cadena.matches("[0-9]*");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == vistaPedidoCliente.Reservar) {
            if (vistaPedidoCliente.Nombre.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione un platillo, por favor");
            } else {
                if (vistaPedidoCliente.Cantidad.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese cantidad a reservar");
                } else {
                    if (ValidarCantidad(vistaPedidoCliente.Cantidad.getText())) {
                        if (vistaPedidoCliente.DNICliente.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Ingrese DNI del cliente");
                        } else {
                            if (ValidarCantidad(vistaPedidoCliente.DNICliente.getText())) {
                                int dni = Integer.parseInt(vistaPedidoCliente.DNICliente.getText());
                                if (9999999 < dni && dni < 100000000) {
                                    if (vistaPedidoCliente.NumMesa.getText().equals("")) {
                                        JOptionPane.showMessageDialog(null, "Ingrese el numero de Meza");
                                    } else {
                                        if (ValidarCantidad(vistaPedidoCliente.NumMesa.getText())) {
                                            int cant = Integer.parseInt(vistaPedidoCliente.Cantidad.getText());
                                            float precio = Float.parseFloat(vistaPedidoCliente.Precio.getText());
                                            float precioT = cant * precio;
                                            boleta.setDNI(vistaPedidoCliente.DNICliente.getText());
                                            boleta.setNumMeza(Integer.parseInt(vistaPedidoCliente.NumMesa.getText()));
                                            boleta.setDiaP(vistaPedidoCliente.Dia.getText());
                                            boleta.setNombreP(vistaPedidoCliente.Nombre.getText());
                                            boleta.setPrecioP(Float.parseFloat(vistaPedidoCliente.Precio.getText()));
                                            boleta.setCantidad(Integer.parseInt(vistaPedidoCliente.Cantidad.getText()));
                                            boleta.setPrecioTotalPlatillo(precioT);
                                            boleta.setEstadoPedido("En proceso");
                                            boleta.setEstadoPagoDespues("Cancelado");
                                            boleta.setModalidad("Cliente presencial");
                                            if (dao.TomarPedido(boleta)) {
                                                JOptionPane.showMessageDialog(null, "Pedido Registrado");
                                                Limpiar();
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Ocurrio un error");
                                                Limpiar();
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Numero de Meza no admitido");
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "DNI no reconocido");
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Dni no válido");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cantidad no admitida");
                    }
                }
            }
        }

        if (ae.getSource() == vistaDatosCliente.PAGAR) {
            if (ValidarCantidad(vistaDatosCliente.Tarjeta1.getText())) {
                if (ValidarCantidad(vistaDatosCliente.Tarjeta2.getText())) {
                    if (ValidarCantidad(vistaDatosCliente.Tarjeta3.getText())) {
                        if (ValidarCantidad(vistaDatosCliente.Tarjeta4.getText())) {
                            if (vistaDatosCliente.Tarjeta1.getText().equals("") || vistaDatosCliente.Tarjeta2.equals("") || vistaDatosCliente.Tarjeta3.equals("")
                                    || vistaDatosCliente.Tarjeta4.equals("") || vistaDatosCliente.Seguridad.getText().equals("") || vistaDatosCliente.DNI.getText().equals("")
                                    || vistaDatosCliente.Nombre.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Rellene todo los campos, por favor");
                            } else {
                                if (ValidarCantidad(vistaDatosCliente.DNI.getText())) {
                                    int dni = Integer.parseInt(vistaDatosCliente.DNI.getText());
                                    if (9999999 < dni && dni < 100000000) {
                                        int num1 = Integer.parseInt(vistaDatosCliente.Tarjeta1.getText());
                                        int num2 = Integer.parseInt(vistaDatosCliente.Tarjeta2.getText());
                                        int num3 = Integer.parseInt(vistaDatosCliente.Tarjeta3.getText());
                                        int num4 = Integer.parseInt(vistaDatosCliente.Tarjeta4.getText());
                                        if (999 < num1 && num1 < 10000 && 999 < num2 && num2 < 10000 && 999 < num3 && num3 < 10000 && 999 < num4 && num4 < 10000) {
                                            if (dao.ImprimirBoleta(vistaDatosCliente.Num.getText())) {
                                                JOptionPane.showMessageDialog(null, "Pago correcto");
                                                LimpiarDatosCliente();
                                                JFrameBoletaPresencial();
                                                AbstractFactory fabrica = Factory.getFactory("Mesero");
                                                FactoryMetodosTablasMesero tablaMesero = fabrica.getTablaMesero("BoletaPagada");
                                                tablaMesero.BoletaPagada();
                                            } else {
                                                JOptionPane.showMessageDialog(null, "No se ha registrado ningun pedido");
                                                LimpiarDatosCliente();
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Numero de tarjeta no admitido");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "DNI no reonocido");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "DNI no válido");
                                }
                            }
                        }
                    }
                }
            }
            if (!ValidarCantidad(vistaDatosCliente.Tarjeta1.getText()) || !ValidarCantidad(vistaDatosCliente.Tarjeta2.getText())
                    || !ValidarCantidad(vistaDatosCliente.Tarjeta3.getText()) || !ValidarCantidad(vistaDatosCliente.Tarjeta4.getText())) {
                JOptionPane.showMessageDialog(null, "Numero de tarjeta no válidos");
            }

        }

        if (ae.getSource() == vistaBoletaPresencial.OK) {
            dao.EliminarCarritoVirtual(Integer.parseInt(vistaBoletaPresencial.Mesa.getText()));
        }

        if (ae.getSource() == vistaVerEstado.EntregarPedido) {
            if (vistaVerEstado.Id.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecione un pedido a entregar");
            } else {
                if (vistaVerEstado.Estado.getText().equals("Preparado")) {
                    dao.EliminarPedido(vistaVerEstado.Id.getText());
                    AbstractFactory fabrica = Factory.getFactory("Mesero");
                    FactoryMetodosTablasMesero tablaMesero = fabrica.getTablaMesero("EstadoPedido");
                    tablaMesero.EstadoPedido();
                } else {
                    JOptionPane.showMessageDialog(null, "Aún no se termina de preparar");
                }
            }
        }
    }

    public void Limpiar() {
        vistaPedidoCliente.txtNamePlatillo.setText(null);
        vistaPedidoCliente.Cantidad.setText(null);
        vistaPedidoCliente.txtDia.setText(null);
        vistaPedidoCliente.Nombre.setText(null);
    }

     public void LimpiarDatosCliente() {
        vistaDatos.Nombre.setText(null);
        vistaDatos.DNI.setText(null);
        vistaDatos.Tarjeta1.setText(null);
        vistaDatos.Tarjeta2.setText(null);
        vistaDatos.Tarjeta3.setText(null);
        vistaDatos.Tarjeta4.setText(null);
        vistaDatos.Seguridad.setText(null);
    }
}
