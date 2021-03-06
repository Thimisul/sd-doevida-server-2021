/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.Socket;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.getInt;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.UserTableModel;
import utils.Utils;

/**
 *
 * @author lsilva
 */
public class AuthorizePending extends javax.swing.JFrame {

    Socket connection;
    UserTableModel model;
    Vector<String> data;
    Vector<String> columnNames;
    Vector<Vector<String>> dataList;

    /**
     * Creates new form AuthorizePending
     *
     * @param connection
     */
    public AuthorizePending(Socket connection) {
        this.connection = connection;
        CarregaTabela();
        initComponents();
        this.setLocationRelativeTo(null);
        start();
    }

    public void start() {
        this.pack();
        this.setVisible(true);
    }

    public void CarregaTabela() {
        System.out.println("Inicio do Carrega tabela \n");
        JSONObject verificaPendentes = new JSONObject();
        verificaPendentes.put("protocol", 600);
        System.out.println("600 " + verificaPendentes.toString());
        Utils.sendMessage(connection, verificaPendentes.toString());
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
        //Mensagem enviada: {"protocol":602,"message":{"result":false,"reason":"Usuario n??o ?? administrador"}} 
        //System.out.println(protocol);
        dataList = new Vector<>();

        for (int i = 0; i < listO.length(); i++) {

            JSONObject jsonObj = listO.getJSONObject(i);
            data = new Vector<>();

            //data.add(String.valueOf(jsonObj.getInt("id")));
            data.add(jsonObj.getString("name"));
            data.add(jsonObj.getString("username"));
            data.add(jsonObj.getString("city"));
            data.add(jsonObj.getString("state"));

            dataList.add(data);
        }

        columnNames = new Vector<>();
        //columnNames.add("ID");//0
        columnNames.add("Nome");//0
        columnNames.add("Username");//1
        columnNames.add("Cidade");//2
        columnNames.add("Estado");//3

        //JTable table = new JTable(dataList, columnNames);
//        List<String> usuariosS = new ArrayList<String>();
//        for (int i = 0; i < listO.length(); i++) {
//            usuariosS.add(listO.getJSONObject(i).toString());
//        }
//
//        System.out.println("Print do array " + usuariosS.get(0));
//
//        ObjectMapper userOM = new ObjectMapper();
//        ArrayList<User> arrayUsuarios = new ArrayList<User>();
//        for (int i = 0; i < usuariosS.size(); i++) {
//            try {
//                arrayUsuarios.add(userOM.readValue(usuariosS.get(i), User.class));
//            } catch (JsonProcessingException ex) {
//                Logger.getLogger(LandingPage.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        model = new UserTableModel(arrayUsuarios);
//        
//        JTable table = new JTable(model) {
//                    @Override
//                    public Dimension getPreferredScrollableViewportSize() {
//                        return new Dimension(300, 100);
//                    }
//                };
//
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListaPendentes = new javax.swing.JTable(dataList, columnNames);
        jLabel1 = new javax.swing.JLabel();
        jBAceitar = new javax.swing.JButton();
        jBRecusar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Recebedores pendentes de aprova????o"));

        jScrollPane1.setViewportView(jTListaPendentes);

        jLabel1.setText("Para aprovar ou reprovar, seleciona a linha acima correspondente ao usu??rio");

        jBAceitar.setText("Aceitar");
        jBAceitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAceitarActionPerformed(evt);
            }
        });

        jBRecusar.setText("Recusar");
        jBRecusar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRecusarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jBAceitar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBRecusar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAceitar)
                    .addComponent(jBRecusar))
                .addGap(0, 60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAceitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAceitarActionPerformed
        String txtusername;
        txtusername = (String) jTListaPendentes.getValueAt(jTListaPendentes.getSelectedRow(), 1);
        System.out.println("O valor pego ??: " + txtusername);
        JSONObject enviaPendente = new JSONObject();
        JSONObject enviaPendenteMessage = new JSONObject();
        enviaPendente.put("protocol", 610);
        enviaPendenteMessage.put("username", txtusername);
        enviaPendenteMessage.put("receptor", 1);
        enviaPendente.put("message", enviaPendenteMessage);
        System.out.println("610 " + enviaPendente.toString());
        Utils.sendMessage(connection, enviaPendente.toString());
        System.out.println("Enviou a mensagem");
        String messageJson = Utils.receiveMessage(connection);
        System.out.println("Recebeu");
        JSONObject jsonO = new JSONObject(messageJson);
        System.out.println("mensagem de resposta jsonO --->>>" + jsonO.toString());
        JSONObject messageO = new JSONObject(jsonO.optString("message"));
        System.out.println("mensagem de resposta messageO --->>>" + messageO.toString());
        JOptionPane.showMessageDialog(rootPane, "Usu??rio promovido ?? Receptor\n");
        dispose();
    }//GEN-LAST:event_jBAceitarActionPerformed

    private void jBRecusarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRecusarActionPerformed
        String txtusername;
        txtusername = (String) jTListaPendentes.getValueAt(jTListaPendentes.getSelectedRow(), 1);
        System.out.println("O valor pego ??: " + txtusername);
        JSONObject enviaPendente = new JSONObject();
        JSONObject enviaPendenteMessage = new JSONObject();
        enviaPendente.put("protocol", 610);
        enviaPendenteMessage.put("username", txtusername);
        enviaPendenteMessage.put("receptor", 99);
        enviaPendente.put("message", enviaPendenteMessage);
        System.out.println("610 " + enviaPendente.toString());
        Utils.sendMessage(connection, enviaPendente.toString());
        System.out.println("Enviou a mensagem");
        String messageJson = Utils.receiveMessage(connection);
        System.out.println("Recebeu");
        JSONObject jsonO = new JSONObject(messageJson);
        System.out.println("mensagem de resposta jsonO --->>>" + jsonO.toString());
        JSONObject messageO = new JSONObject(jsonO.optString("message"));
        System.out.println("mensagem de resposta messageO --->>>" + messageO.toString());
        JOptionPane.showMessageDialog(rootPane, "Usu??rio n??o promovido ?? Receptor\n");
        dispose();
    }//GEN-LAST:event_jBRecusarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAceitar;
    private javax.swing.JButton jBRecusar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListaPendentes;
    // End of variables declaration//GEN-END:variables
}
