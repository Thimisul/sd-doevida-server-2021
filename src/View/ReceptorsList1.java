/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.Socket;
import java.util.Arrays;
import java.util.Vector;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.UserTableModel;
import utils.Utils;

/**
 *
 * @author lsilva
 */
public class ReceptorsList1 extends javax.swing.JFrame {

    Socket connection;
    UserTableModel model;
    Vector<String> data;
    Vector<String> columnNames;
    Vector<Vector<String>> dataList;

    /**
     * Creates new form ReceptorsList1
     *
     * @param connection
     */
    public ReceptorsList1(Socket connection) {
        this.connection = connection;
        initComponents();
        this.setLocationRelativeTo(null);
        start();
    }

    public void start() {
        this.pack();
        this.setVisible(true);
        String TipoFiltro = jComboTipoFiltro.getSelectedItem().toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPDonor = new javax.swing.JPanel();
        jLSelectReceptor = new javax.swing.JLabel();
        jBDoar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListaReceptores = new javax.swing.JTable(dataList, columnNames);
        jLabel1 = new javax.swing.JLabel();
        jComboTipoFiltro = new javax.swing.JComboBox<>();
        jTFFiltroWhere = new javax.swing.JTextField();
        jBPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPDonor.setBorder(javax.swing.BorderFactory.createTitledBorder("Sou Doador"));

        jLSelectReceptor.setText("Selecione um Receptor de doações na lista: ");

        jBDoar.setText("Doar para o receptor selecionado");
        jBDoar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDoarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTListaReceptores);

        jLabel1.setText("Selecione o filtro que deseja:");

        jComboTipoFiltro.setModel(new javax.swing.DefaultComboBoxModel(Utils.filtroReceptores));
        jComboTipoFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboTipoFiltroActionPerformed(evt);
            }
        });
        jComboTipoFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboTipoFiltroActionPerformed(evt);
            }
        });

        jBPesquisar.setText("Pesquisar");
        jBPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPDonorLayout = new javax.swing.GroupLayout(jPDonor);
        jPDonor.setLayout(jPDonorLayout);
        jPDonorLayout.setHorizontalGroup(
            jPDonorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDonorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDonorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBDoar, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPDonorLayout.createSequentialGroup()
                        .addComponent(jTFFiltroWhere)
                        .addGap(38, 38, 38))
                    .addGroup(jPDonorLayout.createSequentialGroup()
                        .addGroup(jPDonorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLSelectReceptor)
                            .addGroup(jPDonorLayout.createSequentialGroup()
                                .addComponent(jComboTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addComponent(jBPesquisar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPDonorLayout.setVerticalGroup(
            jPDonorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDonorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDonorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTFFiltroWhere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBPesquisar)
                .addGap(8, 8, 8)
                .addComponent(jLSelectReceptor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBDoar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPDonor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPDonor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBDoarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDoarActionPerformed

    }//GEN-LAST:event_jBDoarActionPerformed

    private void jBPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarActionPerformed
        JSONObject verificaReceptores = new JSONObject();
        JSONObject verificaReceptoresMessage = new JSONObject();
        JSONObject verificaReceptoresMessageFilter = new JSONObject();

        if (jComboTipoFiltro.getSelectedItem() == "") {
            JOptionPane.showMessageDialog(rootPane, "Selecionar ao menos um tipo de filtro no combobox");
        } else if ((!"".equals(jComboTipoFiltro.getSelectedItem())) && ("".equals(jTFFiltroWhere.getText()))) {
            String filtroTabela = String.valueOf(jComboTipoFiltro.getSelectedItem());
            System.out.println("Texto do combobox: " + filtroTabela);

            String whereTabela = jTFFiltroWhere.getText();
            System.out.println("Texto do textfield: " + whereTabela);

            verificaReceptores.put("protocol", 400);
            verificaReceptores.put("message", verificaReceptoresMessage);
            verificaReceptoresMessage.put("filter", verificaReceptoresMessageFilter);
            verificaReceptoresMessageFilter.put(filtroTabela, "*");

            System.out.println("400 " + verificaReceptores.toString());
            Utils.sendMessage(connection, verificaReceptores.toString());
            System.out.println("Enviou a mensagem");
            String messageJson = Utils.receiveMessage(connection);
            System.out.println("Recebeu");
            JSONObject jsonO = new JSONObject(messageJson);
            System.out.println("mensagem de resposta jsonO --->>>" + jsonO.toString());
            Integer protocol = (Integer) jsonO.opt("protocol");
            System.out.println("mensagem de resposta protocolo --->>>" + protocol.toString());
            JSONObject messageO = new JSONObject(jsonO.optString("message"));
            System.out.println("mensagem de resposta messageO --->>>" + messageO.toString());
            JSONArray listO = new JSONArray(messageO.optString("list"));
            System.out.println("mensagem de resposta listO --->>>" + listO.toString());

            //logica de popular tabela
            dataList = new Vector<>();

            for (int i = 0; i < listO.length(); i++) {

                JSONObject jsonObj = listO.getJSONObject(i);
                data = new Vector<>();

                data.add(jsonObj.getString("username"));
                data.add(jsonObj.getString("name"));
                data.add(jsonObj.getString("city"));
                data.add(jsonObj.getString("state"));

                dataList.add(data);
            }

            columnNames = new Vector<>();
            columnNames.add("Username");//1
            columnNames.add("Nome");//2
            columnNames.add("Cidade");
            columnNames.add("Estado");

        } else {
            String filtroTabela = String.valueOf(jComboTipoFiltro.getSelectedItem());
            System.out.println("Texto do combobox: " + filtroTabela);

            String whereTabela = jTFFiltroWhere.getText();
            System.out.println("Texto do textfield: " + whereTabela);

            verificaReceptores.put("protocol", 400);
            verificaReceptores.put("message", verificaReceptoresMessage);
            verificaReceptoresMessage.put("filter", verificaReceptoresMessageFilter);
            verificaReceptoresMessageFilter.put(filtroTabela, whereTabela);

            System.out.println("400 " + verificaReceptores.toString());
            Utils.sendMessage(connection, verificaReceptores.toString());
            System.out.println("Enviou a mensagem");
            String messageJson = Utils.receiveMessage(connection);
            System.out.println("Recebeu");
            JSONObject jsonO = new JSONObject(messageJson);
            System.out.println("mensagem de resposta jsonO --->>>" + jsonO.toString());
            Integer protocol = (Integer) jsonO.opt("protocol");
            System.out.println("mensagem de resposta protocolo --->>>" + protocol.toString());
            JSONObject messageO = new JSONObject(jsonO.optString("message"));
            System.out.println("mensagem de resposta messageO --->>>" + messageO.toString());
            JSONArray listO = new JSONArray(messageO.optString("list"));
            System.out.println("mensagem de resposta listO --->>>" + listO.toString());

            //logica de popular tabela
            dataList = new Vector<>();

            for (int i = 0; i < listO.length(); i++) {

                JSONObject jsonObj = listO.getJSONObject(i);
                data = new Vector<>();

                data.add(jsonObj.getString("username"));
                data.add(jsonObj.getString("name"));
                data.add(jsonObj.getString("city"));
                data.add(jsonObj.getString("state"));

                dataList.add(data);
            }

            columnNames = new Vector<>();
            columnNames.add("Username");//1
            columnNames.add("Nome");//2
            columnNames.add("Cidade");
            columnNames.add("Estado");
        }
    }//GEN-LAST:event_jBPesquisarActionPerformed

    private void jComboTipoFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboTipoFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboTipoFiltroActionPerformed
   
    /**
     * @param args the command line arguments
     *
     *
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBDoar;
    private javax.swing.JButton jBPesquisar;
    private javax.swing.JComboBox<String> jComboTipoFiltro;
    private javax.swing.JLabel jLSelectReceptor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPDonor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFFiltroWhere;
    private javax.swing.JTable jTListaReceptores;
    // End of variables declaration//GEN-END:variables
}
