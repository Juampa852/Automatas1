/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import Excepciones.EstadoNoExiste;
import Excepciones.EstadoYaExiste;
import Excepciones.SimboloNoExiste;
import Excepciones.SimboloYaExiste;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author juampa
 */
public class Ventana1 extends javax.swing.JFrame {
    private Automata automata=new Automata();
    private EstadoCustomListModel estados = new EstadoCustomListModel();
    private TransicionesCustomListModel transiciones = new TransicionesCustomListModel();
    private LenguajeCustomListModel lenguaje = new LenguajeCustomListModel();
    private Estado temp;
    private String simboloT;
    /**
     * Creates new form Ventana1
     */
    public Ventana1() {
        initComponents();
        listaLenguaje.setModel(lenguaje);
        listaEstados.setModel(estados);
        listaTransiciones.setModel(transiciones);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lenguajePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lenguajeField = new javax.swing.JTextField();
        ingresarLenguajeButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaLenguaje = new javax.swing.JList<>();
        eliminarLenguajeButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaEstados = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaTransiciones = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Automatas");

        jLabel1.setText("Ingresar:");

        lenguajeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lenguajeFieldKeyReleased(evt);
            }
        });

        ingresarLenguajeButton.setText("Ingresar");
        ingresarLenguajeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarLenguajeButtonActionPerformed(evt);
            }
        });

        listaLenguaje.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaLenguajeValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listaLenguaje);

        eliminarLenguajeButton.setText("Eliminar");
        eliminarLenguajeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarLenguajeButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Terminado");

        javax.swing.GroupLayout lenguajePanelLayout = new javax.swing.GroupLayout(lenguajePanel);
        lenguajePanel.setLayout(lenguajePanelLayout);
        lenguajePanelLayout.setHorizontalGroup(
            lenguajePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lenguajePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lenguajePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lenguajePanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lenguajeField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lenguajePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(eliminarLenguajeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ingresarLenguajeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(lenguajePanelLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jButton1))
        );
        lenguajePanelLayout.setVerticalGroup(
            lenguajePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lenguajePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(lenguajePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lenguajePanelLayout.createSequentialGroup()
                        .addGroup(lenguajePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lenguajeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ingresarLenguajeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarLenguajeButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        listaEstados.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaEstadosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaEstados);

        listaTransiciones.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaTransicionesValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listaTransiciones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lenguajePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lenguajePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarLenguajeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarLenguajeButtonActionPerformed
        ingresar();
    }//GEN-LAST:event_ingresarLenguajeButtonActionPerformed

    private void listaEstadosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaEstadosValueChanged
        int seleccion = listaEstados.getSelectedIndex();
        if (seleccion!=-1) {
            temp=estados.getEstado(seleccion);
            
        }
    }//GEN-LAST:event_listaEstadosValueChanged

    private void listaLenguajeValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaLenguajeValueChanged
        int seleccion = listaLenguaje.getSelectedIndex();
        if (seleccion!=-1) {
            simboloT=lenguaje.getSimbolo(seleccion);
        }
    }//GEN-LAST:event_listaLenguajeValueChanged

    private void eliminarLenguajeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarLenguajeButtonActionPerformed
        try{
            automata.eliminarSimbolo(simboloT);
            lenguaje.eliminarSimbolo(listaLenguaje.getSelectedIndex());
        } catch (SimboloNoExiste ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_eliminarLenguajeButtonActionPerformed

    private void lenguajeFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lenguajeFieldKeyReleased
        int tecla=evt.getKeyCode();
        if(tecla==10){
            ingresar();
        }
    }//GEN-LAST:event_lenguajeFieldKeyReleased

    private void listaTransicionesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaTransicionesValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_listaTransicionesValueChanged
    private void ingresar(){
        if(!(lenguajeField.getText().trim().equals(""))){
            try {
                automata.agregarSimbolo(lenguajeField.getText().trim());
                lenguaje.addSimbolo(lenguajeField.getText().trim());
            } catch (SimboloYaExiste ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            lenguajeField.setText("");
            lenguajeField.requestFocus();
        }
    }
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
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eliminarLenguajeButton;
    private javax.swing.JButton ingresarLenguajeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lenguajeField;
    private javax.swing.JPanel lenguajePanel;
    private javax.swing.JList<String> listaEstados;
    private javax.swing.JList<String> listaLenguaje;
    private javax.swing.JList<String> listaTransiciones;
    // End of variables declaration//GEN-END:variables
}
