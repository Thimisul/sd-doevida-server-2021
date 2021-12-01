/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import entidades.User;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import utils.UserTableModel;

/**
 *
 * @author lsilva
 */
public class LandingPage extends javax.swing.JFrame {

    Socket connection;
    ObjectOutputStream saida;

    public LandingPage(Socket connection) {
        this.connection = connection;
        initComponents();
        start();
    }

    private void start() {
        this.pack();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBEditarCadastro = new javax.swing.JButton();
        jBLogout = new javax.swing.JButton();
        jBVerificaPendentes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Bem Vindo!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel1)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jBEditarCadastro.setText("Editar cadastro");
        jBEditarCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarCadastroActionPerformed(evt);
            }
        });

        jBLogout.setText("Logout");
        jBLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLogoutActionPerformed(evt);
            }
        });

        jBVerificaPendentes.setText("Verificar pendentes");
        jBVerificaPendentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVerificaPendentesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBLogout)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBEditarCadastro)
                    .addComponent(jBVerificaPendentes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBEditarCadastro)
                .addGap(18, 18, 18)
                .addComponent(jBVerificaPendentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addComponent(jBLogout)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBEditarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarCadastroActionPerformed

        try {
            JSONObject editar = new JSONObject();
            JSONObject editarMessage = new JSONObject();
            editar.put("protocol", 710);
            editar.put("message", editarMessage);
            editarMessage.put("username", Login.usernameglobal);
            //System.out.println("Da landing page do protocolo 710" + Login.usernameglobal);
            Utils.sendMessage(connection, editar.toString());
            String messageJson = Utils.receiveMessage(connection);
            JSONObject jsonO = new JSONObject(messageJson);
            JSONObject messageO = new JSONObject(jsonO.optString("message"));
            Integer protocol = (Integer) jsonO.opt("protocol");
            System.out.println("mensagem de resposta --->>>" + messageJson);
//            User userEdit = new User(
//                                    null, 
//                                    messageO.optString("name"), 
//                                    null, 
//                                    0,
//                                    messageO.optString("city"), //cidade
//                                    messageO.optString("state"), //Estado
//                                    messageO.optInt("receptor"), //Validado
//                                    null //senha
//                                    );
            User userEdit = new User();
            userEdit.setName(messageO.optString("name"));
            userEdit.setCity(messageO.optString("city"));
            userEdit.setFederativeUnit(messageO.optString("state"));
            userEdit.setRecepValidated(messageO.optInt("receptor"));

            new EditRecordUser(connection, userEdit);//que quer abrir
            System.out.println("veio da landing page no final de tudo " + userEdit.getName());

        } catch (JSONException ex) {
            Logger.getLogger(LandingPage.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Mensagem" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBEditarCadastroActionPerformed

    private void jBLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLogoutActionPerformed
        try {
            JSONObject logout = new JSONObject();

            logout.put("protocol", 199);
            Utils.sendMessage(connection, logout.toString());
            JOptionPane.showMessageDialog(rootPane, "Até logo");
            dispose();
            new Login(connection);

        } catch (JSONException ex) {
            JOptionPane.showMessageDialog(rootPane, "Mensagem " + ex, "Erro ao deslogar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBLogoutActionPerformed

    private void jBVerificaPendentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerificaPendentesActionPerformed
        JSONObject verificaPendentes = new JSONObject();
        verificaPendentes.put("protocol", 600);
        Utils.sendMessage(connection, verificaPendentes.toString());
        String messageJson = Utils.receiveMessage(connection);
        JSONObject jsonO = new JSONObject(messageJson);
        System.out.println("mensagem de resposta jsonO --->>>" + jsonO.toString());
        Integer protocol = (Integer) jsonO.opt("protocol");
        JSONObject messageO = new JSONObject(jsonO.optString("message"));
        if (protocol == 602) {
            JOptionPane.showMessageDialog(rootPane, "O usuário não é administrador. Fechando a interface\n" + "Mensagem do servidor: " + messageO.optString("reason"));
        } else {
            AuthorizePending authorizePending = new AuthorizePending(connection);
        }
    }//GEN-LAST:event_jBVerificaPendentesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBEditarCadastro;
    private javax.swing.JButton jBLogout;
    private javax.swing.JButton jBVerificaPendentes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
