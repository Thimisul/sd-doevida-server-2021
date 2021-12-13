/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Utils;

/**
 *
 * @author Hideo
 */
public class DonateList extends javax.swing.JFrame {

    Socket connection;
    Vector<String> data;
    Vector<String> columnNames;
    Vector<Vector<String>> dataList;

    /**
     * Creates new form DonateList
     */
    public DonateList(Socket connection) {
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
        JSONObject gerarRelatorio = new JSONObject();
        JSONObject gerarRelatorioMessage = new JSONObject();
        gerarRelatorioMessage.put("username", Login.usernameglobal);
        gerarRelatorioMessage.put("usertype", 3); // TODO: Descobrir o usertype pelo Client ou fazer requisição
        gerarRelatorio.put("protocol", 800);
        gerarRelatorio.put("message", gerarRelatorioMessage);
        System.out.println("800 " + gerarRelatorio.toString());
        Utils.sendMessage(connection, gerarRelatorio.toString());
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
        //Mensagem enviada: {"protocol":602,"message":{"result":false,"reason":"Usuario não é administrador"}} 
        //System.out.println(protocol);

        dataList = new Vector<>();

        for (int i = 0; i < listO.length(); i++) {

            JSONObject jsonObj = listO.getJSONObject(i);
            data = new Vector<>();

            data.add(jsonObj.getString("donor"));
            data.add(jsonObj.getString("receptor"));
            data.add(String.valueOf(jsonObj.getInt("value")));
            data.add((jsonObj.getBoolean("anonymous"))? "Sim":"Não");

            dataList.add(data);
        }

        columnNames = new Vector<>();
        //columnNames.add("ID");//0
        columnNames.add("Doador");//0
        columnNames.add("Receptor");//1
        columnNames.add("Valor");//2
        columnNames.add("Anônimo");//3
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPReceptor1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListaDoacao = new javax.swing.JTable(dataList, columnNames);

        jPReceptor1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Doação"));

        jScrollPane1.setViewportView(jTListaDoacao);

        javax.swing.GroupLayout jPReceptor1Layout = new javax.swing.GroupLayout(jPReceptor1);
        jPReceptor1.setLayout(jPReceptor1Layout);
        jPReceptor1Layout.setHorizontalGroup(
            jPReceptor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPReceptor1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPReceptor1Layout.setVerticalGroup(
            jPReceptor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPReceptor1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPReceptor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPReceptor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPReceptor1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListaDoacao;
    // End of variables declaration//GEN-END:variables
}
