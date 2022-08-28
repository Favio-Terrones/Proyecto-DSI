/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO_CLASES.Boleta;
import VistaChef.AtenderPedidoVirtual;
import VistaChef.PedidoEnProceso;
import VistaChef.AdicionarPlatillo;
import VistaChef.ModificarMenuSemanal;
import VistaChef.ModificarPlatillo;
import VistaChef.PlanificarMenu;
import AF_Seleccion.Factory;
import AF_Factory.AbstractFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import AF_Factory.FactoryMetodosTablasChef;
import EstrategyMenu.AccionesChefMenu;
import EstrategyPlatillos.AccionesChefPlatillo;
import EstrategyPlatillos.EliminarPlatillos;
import EstrategyPlatillos.InsertarDiaPlatillo;
import EstrategyPlatillos.ModificarDiaPlatillo;
import EstrategyPlatillos.ModificarPlatillos;
import Clases.Platillo;
import DAO.DAOChefImp;
import DAO_Interfas.DAOChef;
import EstrategyPlatillos.RegistrarPlatillos;
import EstrategyMenu.ModificarPlatilloMenu;
import EstrategyMenu.EliminarPlatilloMenu;

/**
 *
 * @author Erick
 */
public class ControladorChef implements ActionListener {

    AdicionarPlatillo vistaAdicionar = AdicionarPlatillo.getInstancia();
    ModificarPlatillo vistaModificarPlatillo = ModificarPlatillo.getInstancia();
    PlanificarMenu vistaPlanificar = PlanificarMenu.getInstancia();
    AtenderPedidoVirtual vistaAtender = AtenderPedidoVirtual.getInstancia();
    PedidoEnProceso vistaPedidoEnProceso = PedidoEnProceso.getInstancia();
    ModificarMenuSemanal vistaModificarMenuSemanal = ModificarMenuSemanal.getInstancia();
    Platillo platillo = new Platillo() {
    };
    Boleta boleta = Boleta.getInstancia();
    String tipo = "Chef";
    DAOChef dao = new DAOChefImp();

    private static ControladorChef instancia;

    private ControladorChef() {
        vistaAdicionar.Registrar.addActionListener(this);
        vistaModificarPlatillo.ModificarPlatillo.addActionListener(this);
        vistaModificarPlatillo.EliminarPlatillo.addActionListener(this);
        vistaPlanificar.Insertar.addActionListener(this);
        vistaAtender.AtenerPedido.addActionListener(this);
        vistaPedidoEnProceso.MarcarPedidoTerminado.addActionListener(this);
        vistaModificarMenuSemanal.Modificar.addActionListener(this);
        vistaModificarMenuSemanal.Eliminar.addActionListener(this);
    }

    public static ControladorChef getInstancia() {

        if (instancia == null) {
            instancia = new ControladorChef();
        }
        return instancia;
    }

    public void JFrameAdicionar() {
        vistaAdicionar.setTitle("ADICIONAR PLATILLO");
        vistaAdicionar.setLocationRelativeTo(null);
        vistaAdicionar.setVisible(true);

    }

    public void JFrameModificarPlatillo() {
        vistaModificarPlatillo.setTitle("MODIFICAR PLATILLO");
        vistaModificarPlatillo.setLocationRelativeTo(null);
        vistaModificarPlatillo.setVisible(true);

    }

    public void JFramePlanificar() {
        vistaPlanificar.setTitle("PLANIFICAR MENU SEMANAL");
        vistaPlanificar.setLocationRelativeTo(null);
        vistaPlanificar.setVisible(true);
        vistaPlanificar.Dia.setVisible(false);

    }

    public void JFrameAtenderPedido() {
        vistaAtender.setTitle("ATENDER PEDIDO");
        vistaAtender.setLocationRelativeTo(null);
        vistaAtender.setVisible(true);
        vistaAtender.Cantidad.setVisible(false);
        vistaAtender.DNI.setVisible(false);;
        vistaAtender.EstadoPedido.setVisible(false);
        vistaAtender.IdPedido.setVisible(false);
    }

    public void JFramePedidoEnProceso() {
        vistaPedidoEnProceso.setTitle("PEDIDO EN PROCESO");
        vistaPedidoEnProceso.setLocationRelativeTo(null);
        vistaPedidoEnProceso.setVisible(true);
        vistaPedidoEnProceso.DNI.setVisible(false);
        vistaPedidoEnProceso.Dia.setVisible(false);
        vistaPedidoEnProceso.EstadoPedido.setVisible(false);
        vistaPedidoEnProceso.Id.setVisible(false);
    }

    public void JFrameModificarMenuSemanal() {
        vistaModificarMenuSemanal.setTitle("DISPONIBILIDAD DE MENUS");
        vistaModificarMenuSemanal.setLocationRelativeTo(null);
        vistaModificarMenuSemanal.setVisible(true);

    }

    public boolean ValidarCodigo(String cadena) {
        return cadena.matches("[0-9]*");
    }

    public boolean ValidarString(String cadena) {
        return cadena.matches("[0-9]*");
    }

    public boolean ValidarPrecio(String cadena) {
        return cadena.matches("^[0-9]+([.])?([0-9]+)?$");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        //PLATILLO
        if (ae.getSource() == vistaAdicionar.Registrar) {
            if (vistaAdicionar.Codigo.getText().equals("") || vistaAdicionar.Nombre.getText().equals("") || vistaAdicionar.Precio.getText().equals("")
                    || vistaAdicionar.Observaciones.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Rellene todo los campos");
            } else {
                if (ValidarCodigo(vistaAdicionar.Codigo.getText())) {
                    if (ValidarString(vistaAdicionar.Nombre.getText())) {
                        JOptionPane.showMessageDialog(null, "Nombre no admitido");
                        vistaAdicionar.Nombre.setText(null);
                    } else {
                        AccionesChefPlatillo platilloChef = new AccionesChefPlatillo();
                        platilloChef.setAlgoritmo(new RegistrarPlatillos());
                        
                        if (dao.VerificarCodigo(vistaAdicionar.Codigo.getText()) == 0) {
                            //if (modelo.VerificarCodigo(vistaAdicionar.Codigo.getText()) == 0) {
                            if (ValidarPrecio(vistaAdicionar.Precio.getText())) {
                                if (ValidarString(vistaAdicionar.Observaciones.getText())) {
                                    JOptionPane.showMessageDialog(null, "Obervación no válida");
                                    vistaAdicionar.Observaciones.setText(null);
                                } else {
                                    if (dao.VerificarNombre(vistaAdicionar.Nombre.getText()) == 0) {
                                        //if (modelo.VerificarNombre(vistaAdicionar.Nombre.getText()) == 0) {
                                        //AccionesChef platilloChef = new AccionesChef();
                                        platilloChef.setAlgoritmo(new RegistrarPlatillos());
                                        if (platilloChef.Acciones()) {
                                            JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO");
                                            Limpiar();

                                            AbstractFactory fabrica = Factory.getFactory(tipo);
                                            FactoryMetodosTablasChef tablaChef = fabrica.getTablaChef("PlatilloRegistradosChef");
                                            tablaChef.PlatilloRegistrados();

                                        } else {
                                            JOptionPane.showMessageDialog(null, "REGISTRO ERRADO");
                                            Limpiar();
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "PLATILLO YA EXISTE");
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Precio no válido");
                                vistaAdicionar.Precio.setText(null);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "CODIGO YA EXISTE");
                            vistaAdicionar.Codigo.setText(null);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Codigo no valido");
                    vistaAdicionar.Codigo.setText(null);
                }
            }
        }

        //MODIFICAR PLATILLO 
        if (ae.getSource() == vistaModificarPlatillo.ModificarPlatillo) {
            if (vistaModificarPlatillo.Nombre.getText().equals("") || vistaModificarPlatillo.Precio.getText().equals("")
                    || vistaModificarPlatillo.Observaciones.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione platillo a modificar");
            } else {
                if (ValidarString(vistaModificarPlatillo.Nombre.getText())) {
                    JOptionPane.showMessageDialog(null, "Nombre no admitido");
                    vistaModificarPlatillo.Nombre.setText(null);
                } else {
                    if (ValidarPrecio(vistaModificarPlatillo.Precio.getText())) {
                        if (ValidarString(vistaModificarPlatillo.Observaciones.getText())) {
                            JOptionPane.showMessageDialog(null, "Obervación no válida");
                            vistaModificarPlatillo.Observaciones.setText(null);
                        } else {
                            AccionesChefPlatillo platilloChef = new AccionesChefPlatillo();
                            platilloChef.setAlgoritmo(new ModificarPlatillos());
                            if (platilloChef.Acciones()) {
                                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");
                                AbstractFactory fabrica = Factory.getFactory(tipo);
                                FactoryMetodosTablasChef tablaChef = fabrica.getTablaChef("ModificarPlatilloChef");
                                tablaChef.ModificarPlatillo();
                                LimpiarDatos();
                            } else {
                                JOptionPane.showMessageDialog(null, "REGISTRO NO MODIFICADO");
                                LimpiarDatos();
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Precio no válido");
                        vistaModificarPlatillo.Precio.setText(null);
                    }
                }
            }

        }

        //ELIMINAR PLATILLO
        if (ae.getSource() == vistaModificarPlatillo.EliminarPlatillo) {

            if (vistaModificarPlatillo.Nombre.getText().equals("") || vistaModificarPlatillo.Precio.getText().equals("")
                    || vistaModificarPlatillo.Observaciones.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione platillo a eliminar");
            } else {
                //platillo.setCodigo(vistaModificarPlatillo.Codigo.getText());
                AccionesChefPlatillo platilloChef = new AccionesChefPlatillo();
                platilloChef.setAlgoritmo(new EliminarPlatillos());
                if (platilloChef.Acciones()) {
                    JOptionPane.showMessageDialog(null, "Eliminado correctamente");
                    LimpiarDatos();
                    AbstractFactory fabrica = Factory.getFactory(tipo);
                    FactoryMetodosTablasChef tablaChef = fabrica.getTablaChef("ModificarPlatilloChef");
                    tablaChef.ModificarPlatillo();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR, No se puedo eliminar");
                    LimpiarDatos();
                }
            }

        }

        //INSERTE EL MENU A LA CARTA
        if (ae.getSource() == vistaPlanificar.Insertar) {
            if (vistaPlanificar.Observaciones.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione platillo a registrar");
            } else {
                if (vistaPlanificar.Dia.getText().equals("")) {
                    if (dao.VerificarDiaEnMenu((String) vistaPlanificar.CbDia.getSelectedItem(),
                            vistaPlanificar.Codigo.getText()) == 0) {
                        if (ValidarString(vistaPlanificar.Observaciones.getText())) {
                            JOptionPane.showMessageDialog(null, "Obervación no válida");
                            vistaPlanificar.Observaciones.setText(null);
                        } else {
                            AccionesChefPlatillo platilloChef = new AccionesChefPlatillo();
                            platilloChef.setAlgoritmo(new ModificarDiaPlatillo());
                            if (platilloChef.Acciones()) {
                                JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO");
                                AbstractFactory fabrica = Factory.getFactory(tipo);
                                FactoryMetodosTablasChef tablaChef = fabrica.getTablaChef("PlanificarMenuChef");
                                tablaChef.PlanificarMenu();
                            } else {
                                JOptionPane.showMessageDialog(null, "REGISTRO ERRADO");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "PLATILLO YA ESTÁ EN ESE DIA");
                    }
                } else {
                    if (dao.VerificarDiaEnMenu((String) vistaPlanificar.CbDia.getSelectedItem(),
                            vistaPlanificar.Codigo.getText()) == 0) {
                        if (ValidarString(vistaPlanificar.Observaciones.getText())) {
                            JOptionPane.showMessageDialog(null, "Obervación no válida");
                            vistaPlanificar.Observaciones.setText(null);
                        } else {
                            AccionesChefPlatillo platilloChef = new AccionesChefPlatillo();
                            platilloChef.setAlgoritmo(new InsertarDiaPlatillo());
                            if (platilloChef.Acciones()) {
                                JOptionPane.showMessageDialog(null, "REGISTRO CORRECTO");
                                AbstractFactory fabrica = Factory.getFactory(tipo);
                                FactoryMetodosTablasChef tablaChef = fabrica.getTablaChef("PlanificarMenuChef");
                                tablaChef.PlanificarMenu();
                                LimpiarPlanificar();
                            } else {
                                JOptionPane.showMessageDialog(null, "REGISTRO ERRADO");
                                LimpiarPlanificar();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "PLATILLO YA ESTÁ EN ESE DIA");
                    }
                }
            }
        }

        //MODIFICAR MENUSEMANAL
        if (ae.getSource() == vistaModificarMenuSemanal.Modificar) {
            if (vistaModificarMenuSemanal.Codigo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleecion un platillo a modificar");
            } else {

                if (dao.VerificarDiaEnMenu((String) vistaModificarMenuSemanal.CbDiaModificar.getSelectedItem(),
                        vistaModificarMenuSemanal.Codigo.getText()) == 0) {
                    AccionesChefMenu MenuChef = new AccionesChefMenu();
                    MenuChef.setAlgoritoMenu(new ModificarPlatilloMenu());
                    if (MenuChef.AccionesMenu()) {
                        JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");
                        LimpiarModificacion();
                        AbstractFactory fabrica = Factory.getFactory(tipo);
                        FactoryMetodosTablasChef tablaChef = fabrica.getTablaChef("ModificarMenuChef");
                        tablaChef.ModificarMenu();
                    } else {
                        JOptionPane.showMessageDialog(null, "REGISTRO NO MODIFICADO");
                        LimpiarModificacion();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "PLATILLO YA ESTÁ EN ESE DIA");
                }
            }

        }

        //ELIMINAR PLATILLO DE MENU SEMANAL
        if (ae.getSource() == vistaModificarMenuSemanal.Eliminar) {
            if (vistaModificarMenuSemanal.Codigo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleecion un platillo a eliminar");
            } else {
                AccionesChefMenu MenuChef = new AccionesChefMenu();
                MenuChef.setAlgoritoMenu(new EliminarPlatilloMenu());
                if (MenuChef.AccionesMenu()) {
                    JOptionPane.showMessageDialog(null, "Platillo eliminado del día correctamente");
                    LimpiarDatos();
                    AbstractFactory fabrica = Factory.getFactory(tipo);
                    FactoryMetodosTablasChef tablaChef = fabrica.getTablaChef("ModificarMenuChef");
                    tablaChef.ModificarMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar");
                    LimpiarDatos();
                }
            }

        }

        //ATENDER PEDIDO 
        if (ae.getSource() == vistaAtender.AtenerPedido) {
            if (vistaAtender.EstadoPedido.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione un pedido a atender");
            } else {
                boleta.setEstadoPedido("Atendiendo");
                boleta.setIdPedido(Integer.parseInt(vistaAtender.IdPedido.getText()));
                boleta.setDNI(vistaAtender.DNI.getText());
                boleta.setDiaP(vistaAtender.DiaPedido.getText());
                boleta.setNombreP(vistaAtender.NombrePedido.getText());
                boleta.setCantidad(Integer.parseInt(vistaAtender.Cantidad.getText()));

                if (dao.ModificarEstadoBoleta(boleta)) {

                    if (dao.PasarAPedidoEnProceso(boleta)) {
                        if (dao.EliminarPedidoDeBoletaAtender(boleta)) {
                            JOptionPane.showMessageDialog(null, "Listo para atender");
                            AbstractFactory fabrica = Factory.getFactory(tipo);
                            FactoryMetodosTablasChef tablaChef = fabrica.getTablaChef("AtenderPedidoChef");
                            tablaChef.AtenderPedido();
                            LimpiarAtender();
                        } else {
                            JOptionPane.showMessageDialog(null, "ERROR ELIMINAR DE ATENDER");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR PASAR A PROCESO");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "ERROR MODIFICAR BOLETA");
                }
            }

        }

        //TERMINAR PEDIDO
        if (ae.getSource() == vistaPedidoEnProceso.MarcarPedidoTerminado) {
            if (vistaPedidoEnProceso.Dia.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione un pedido a culminar");
            } else {
                boleta.setEstadoPedido("Preparado");
                boleta.setIdPedido(Integer.parseInt(vistaPedidoEnProceso.Id.getText()));
                boleta.setDNI(vistaPedidoEnProceso.DNI.getText());
                boleta.setDiaP(vistaPedidoEnProceso.Dia.getText());
                boleta.setNombreP(vistaPedidoEnProceso.NombrePedido.getText());
                if (dao.ModificarEstadoBoletaFinal(boleta)) {
                    if (dao.EliminarPedidoDeBoletaEnEspera(boleta)) {
                        JOptionPane.showMessageDialog(null, "Preparado");
                        AbstractFactory fabrica = Factory.getFactory(tipo);
                        FactoryMetodosTablasChef tablaChef = fabrica.getTablaChef("PedidoEnProcesoChef");
                        tablaChef.PedidoEnProceso();
                        LimpiarPedidoProceso();
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR ELIMINAR DE ESPERA");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR MODIFICAR BOLETA");
                }
            }

        }

    }

    public void Limpiar() {
        vistaAdicionar.Codigo.setText("");
        vistaAdicionar.Nombre.setText("");
        vistaAdicionar.Precio.setText("");
        vistaAdicionar.Observaciones.setText("");
    }

    public void LimpiarPlanificar() {
        vistaPlanificar.Codigo.setText(null);
        vistaPlanificar.Nombre.setText(null);
        vistaPlanificar.Precio.setText(null);
        vistaPlanificar.Disponibilidad.setText(null);
        vistaPlanificar.Observaciones.setText(null);

    }

    public void LimpiarDatos() {
        vistaModificarPlatillo.Codigo.setText("");
        vistaModificarPlatillo.Nombre.setText(null);
        vistaModificarPlatillo.Precio.setText(null);
        vistaModificarPlatillo.Observaciones.setText(null);
    }

    public void LimpiarModificacion() {
        vistaModificarMenuSemanal.Id.setText(null);
        vistaModificarMenuSemanal.Codigo.setText(null);
        vistaModificarMenuSemanal.Dia.setText(null);
        vistaModificarMenuSemanal.Nombre.setText(null);
        vistaModificarMenuSemanal.Precio.setText(null);
        vistaModificarMenuSemanal.Observaciones.setText(null);
    }

    public void LimpiarPedidoProceso() {
        vistaPedidoEnProceso.DNI.setText(null);
        vistaPedidoEnProceso.Dia.setText(null);
        vistaPedidoEnProceso.Id.setText(null);
        vistaPedidoEnProceso.NombrePedido.setText(null);
        vistaPedidoEnProceso.EstadoPedido.setText(null);
    }

    public void LimpiarAtender() {
        vistaAtender.Cantidad.setText(null);
        vistaAtender.DNI.setText(null);
        vistaAtender.DiaPedido.setText(null);
        vistaAtender.IdPedido.setText(null);
        vistaAtender.NombrePedido.setText(null);
        vistaAtender.EstadoPedido.setText(null);
    }

}
