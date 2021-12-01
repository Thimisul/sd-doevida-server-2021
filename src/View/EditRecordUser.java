/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import entidades.User;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Utils;

/**
 *
 * @author lsilva
 */
public class EditRecordUser extends javax.swing.JFrame {

    User userEdit;
    Socket connection;
    ObjectOutputStream saida;
    
    /**
     * Creates new form EditRecordUser
     *
     * @param connection
     * @param userEdit
     */
    public EditRecordUser(Socket connection, User userEdit) {
        this.connection = connection;
        this.userEdit = userEdit;
        System.out.println("veio no editRecord " + this.userEdit.getName());
        initComponents();
        start();

    }

    public void start() {
        this.pack();
        this.setVisible(true);
        jTFNome.setText(userEdit.getName());
        jTFCidade.setText(userEdit.getCity());
        int indexOfElement = Arrays.asList(Utils.federativeUnit).indexOf(userEdit.getFederativeUnit());
        jComboEstado.setSelectedIndex(indexOfElement);
        System.out.println("veio no editRecord metodo start " + this.userEdit.getName());
    }

//    public EditRecordUser(Socket connection) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bGBusca = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jBEditar = new javax.swing.JButton();
        jTFNome = new javax.swing.JTextField();
        jPFSenha = new javax.swing.JPasswordField();
        jPFRepitaSenha = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jCBpendingDonation = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jTFCidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboEstado = new javax.swing.JComboBox<>();

        setTitle("Editar Cadastro");
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(420, 320));
        setPreferredSize(new java.awt.Dimension(420, 310));
        setSize(new java.awt.Dimension(430, 320));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar cadastro"));

        jLabel1.setText("Nome:");

        jLabel3.setText("Senha:");

        jLabel4.setText("Repita a senha:");

        jBEditar.setLabel("Editar");
        jBEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarActionPerformed(evt);
            }
        });

        jTFNome.setActionCommand("<Not Set>");

        jPFSenha.setText("Informe a nova senha");
        jPFSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPFSenhaFocusGained(evt);
            }
        });

        jPFRepitaSenha.setText("Repita a senha");
        jPFRepitaSenha.setToolTipText("");
        jPFRepitaSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPFRepitaSenhaFocusGained(evt);
            }
        });

        jCBpendingDonation.setText("Quero receber doações");
        jCBpendingDonation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBpendingDonationActionPerformed(evt);
            }
        });

        jLabel2.setText("Cidade");

        jTFCidade.setActionCommand("<Not Set>");

        jLabel6.setText("Estado");

        jComboEstado.setModel(new javax.swing.DefaultComboBoxModel(Utils.federativeUnit));
        jComboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(5, 5, 5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFNome)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTFCidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPFSenha))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPFRepitaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jCBpendingDonation)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jPFRepitaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBpendingDonation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCBpendingDonationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBpendingDonationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBpendingDonationActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed
        //Fazer um if: se o campo receptor recebido via json (vindo pela landing page) estava como 99 e o usuário
        //marcar a flag de "Quero receber doações na interface, ele envia 0 que é pendente.
        //Se ele já vier como 1 que seria validado, essa flag já tem q existir. Contudo deve ser disable
        //Se ele vier como 0, a flag ficaria como disable também. Pois significa que está aguardando ser ou não
        //Um receptor
        //jTFNome.setText(userEdit.getName());

        JSONObject editarUser = new JSONObject();
        JSONObject editarUserMessage = new JSONObject();

        if (jPFSenha.getText().equals(jPFRepitaSenha.getText())) {
            try {
                editarUser.put("protocol", 720);
                editarUserMessage.put("name", jTFNome.getText());
                editarUserMessage.put("city", jTFCidade.getText());
                editarUserMessage.put("state",Utils.federativeUnit[jComboEstado.getSelectedIndex()]);
                editarUserMessage.put("password", jPFSenha.getText());
                editarUser.put("message", editarUserMessage);
                Utils.sendMessage(connection, editarUser.toString());
                String messageJson = Utils.receiveMessage(connection);
                JSONObject jsonO = new JSONObject(messageJson);
                System.out.println("mensagem de resposta --->>>" + messageJson);
                if (jsonO.optInt("protocol") == 721){
                    JOptionPane.showMessageDialog(rootPane, "Usuário alterado com sucesso!");
                    dispose();
                    //LandingPage landingpage = new LandingPage(connection);
                }
            } catch (JSONException ex) {
                Logger.getLogger(EditRecordUser.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "As senhas não são iguais. Digite novamente");
            System.out.println("Senha: " + jPFSenha.getText() + "/n" + "Repita senha: " + jPFRepitaSenha.getText());
            jPFSenha.setText("");
            jPFRepitaSenha.setText("");
        }
        //campos enabled na interface
        //if citado acima
        //montar o json para salvar
        //no servidor, ajustar o método pra salvar no banco
        //retornar o true ou false para a interface
        //dar um dispose() para fechar a tela e retornar para a landing page
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jPFSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPFSenhaFocusGained
        jPFSenha.setText("");
    }//GEN-LAST:event_jPFSenhaFocusGained

    private void jPFRepitaSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPFRepitaSenhaFocusGained
        jPFRepitaSenha.setText("");
    }//GEN-LAST:event_jPFRepitaSenhaFocusGained

    private void jComboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboEstadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGBusca;
    private javax.swing.JButton jBEditar;
    private javax.swing.JCheckBox jCBpendingDonation;
    private javax.swing.JComboBox<String> jComboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPFRepitaSenha;
    private javax.swing.JPasswordField jPFSenha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFCidade;
    private javax.swing.JTextField jTFNome;
    // End of variables declaration//GEN-END:variables
}