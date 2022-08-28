/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaCliente;

import Controlador.ControladorCliente;
import Controlador.ControladorPersona;
import AF_Seleccion.Factory;
import AF_Factory.AbstractFactory;
import AF_Factory.FactoryMetodosTablasCliente;
import java.awt.Color;

/**
 *
 * @author Erick
 */
public class ClienteVista extends javax.swing.JFrame {

    /**
     * Creates new form Cliente
     */
    private static ClienteVista instancia;
    String tipo = "Cliente";

    private ClienteVista() {
        initComponents();
setLocationRelativeTo(null);
this.getContentPane().setBackground(Color.decode("#FFFFFF"));
    }

    public static ClienteVista getInstancia() {
        if (instancia == null) {
            instancia = new ClienteVista();
        }
        return instancia;
    }
    public static VerMenuSemanalCliente MS;
    public static RealizarPedidoCliente RP;
    public static VerEstadoPedidoCliente EP;
    RealizarPedidoCliente V = RealizarPedidoCliente.getInstancia();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombreCliente = new javax.swing.JLabel();
        txtDNICliente = new javax.swing.JLabel();
        VerMenu = new javax.swing.JButton();
        RealizarPedido = new javax.swing.JButton();
        txtRolCliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CerrarSesion = new javax.swing.JButton();
        EstadoPedido = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtNombreCliente.setText("Valerio");

        txtDNICliente.setText("75140751");

        VerMenu.setBackground(new java.awt.Color(31, 168, 137));
        VerMenu.setForeground(new java.awt.Color(255, 255, 255));
        VerMenu.setText("VER MENU");
        VerMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerMenuActionPerformed(evt);
            }
        });

        RealizarPedido.setBackground(new java.awt.Color(87, 169, 154));
        RealizarPedido.setForeground(new java.awt.Color(255, 255, 255));
        RealizarPedido.setText("REALIZAR PEDIDO");
        RealizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarPedidoActionPerformed(evt);
            }
        });

        txtRolCliente.setText("Cliente");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("BIENVENIDO CLIENTE");

        CerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_close_pane_50px.png"))); // NOI18N
        CerrarSesion.setText("CERRAR SESION");
        CerrarSesion.setMargin(new java.awt.Insets(2, 2, 2, 2));
        CerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarSesionActionPerformed(evt);
            }
        });

        EstadoPedido.setBackground(new java.awt.Color(87, 169, 154));
        EstadoPedido.setForeground(new java.awt.Color(255, 255, 255));
        EstadoPedido.setText("VER ESTADO DE MI PEDIDO");
        EstadoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadoPedidoActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon_cliente.png"))); // NOI18N

        jLabel3.setText("DNI:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRolCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDNICliente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(RealizarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(VerMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EstadoPedido))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(CerrarSesion)
                        .addGap(11, 11, 11)))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRolCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDNICliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(VerMenu)
                        .addGap(18, 18, 18)
                        .addComponent(RealizarPedido)
                        .addGap(18, 18, 18)
                        .addComponent(EstadoPedido)
                        .addGap(26, 26, 26)
                        .addComponent(CerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     public void Totalizar() {
        double t = 0;
        double p = 0;
        if (V.TablaPedido.getRowCount() > 0) {
            for (int i = 0; i < V.TablaPedido.getRowCount(); i++) {
                p = Double.parseDouble(V.TablaPedido.getValueAt(i, 7).toString());
                t += p;
            }
            V.txtTotal.setText(String.valueOf(t));
        }
    }
    private void VerMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerMenuActionPerformed
        ControladorCliente c = ControladorCliente.getInstancia();
        VerMenuSemanalCliente vistaVisualizar = VerMenuSemanalCliente.getInstancia();
        vistaVisualizar.DNICliente.setText(txtDNICliente.getText());
        if (MS == null && RP == null && EP == null) {
            MS = VerMenuSemanalCliente.getInstancia();
            c.JFrameVisuarlizarDisponibilidadMenu();
            AbstractFactory fabrica = Factory.getFactory(tipo);
            FactoryMetodosTablasCliente tablaCliente = fabrica.getTablaCliente("MenuSemanal");
            tablaCliente.MenuSemanal();
        }
    }//GEN-LAST:event_VerMenuActionPerformed

    private void CerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarSesionActionPerformed
        ControladorPersona controlador = ControladorPersona.getInstancia();
        if (MS == null && RP == null && EP == null) {
            controlador.JFrameIniciar();
            setVisible(false);
        }
    }//GEN-LAST:event_CerrarSesionActionPerformed

    private void RealizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarPedidoActionPerformed
        ControladorCliente c = ControladorCliente.getInstancia();
        VerMenuSemanalCliente vistaVisualizar = VerMenuSemanalCliente.getInstancia();
        RealizarPedidoCliente vistaRealizarPedido = RealizarPedidoCliente.getInstancia();
        AbstractFactory fabrica = Factory.getFactory(tipo);
        FactoryMetodosTablasCliente tablaCliente = fabrica.getTablaCliente("PedidoRealizados");
        vistaVisualizar.DNICliente.setText(txtDNICliente.getText());
        if (MS == null && RP == null && EP == null) {
            RP = RealizarPedidoCliente.getInstancia();
            c.JFrameRealizarPedido();
            vistaRealizarPedido.DNI.setText(txtDNICliente.getText());
            vistaRealizarPedido.txtCliente.setText(txtNombreCliente.getText());
            tablaCliente.PedidoRealizados();
            Totalizar();
        }
    }//GEN-LAST:event_RealizarPedidoActionPerformed

    private void EstadoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoPedidoActionPerformed
        ControladorCliente c = ControladorCliente.getInstancia();
        VerMenuSemanalCliente vistaVisualizar = VerMenuSemanalCliente.getInstancia();
        VerEstadoPedidoCliente vistaEstadoPedido = VerEstadoPedidoCliente.getInstancia();
        AbstractFactory fabrica = Factory.getFactory(tipo);
        FactoryMetodosTablasCliente tablaCliente = fabrica.getTablaCliente("Boleta");
        vistaVisualizar.DNICliente.setText(txtDNICliente.getText());
        if (MS == null && RP == null && EP == null) {
            EP = VerEstadoPedidoCliente.getInstancia();
            c.JFrameEstadoPedido();
            vistaEstadoPedido.DNI.setText(txtDNICliente.getText());
            tablaCliente.Boleta();
        }
    }//GEN-LAST:event_EstadoPedidoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CerrarSesion;
    private javax.swing.JButton EstadoPedido;
    public javax.swing.JButton RealizarPedido;
    public javax.swing.JButton VerMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel txtDNICliente;
    public javax.swing.JLabel txtNombreCliente;
    public javax.swing.JLabel txtRolCliente;
    // End of variables declaration//GEN-END:variables
}