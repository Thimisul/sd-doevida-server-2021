/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import utils.Utils;
import entidades.User;
import java.util.ArrayList;
import org.json.JSONArray;

/**
 *
 * @author lsilva
 */
public class SDServerInterface extends javax.swing.JFrame {

    private static final int PORT = 20025;

    private ServerSocket server;
    private Map<String, ThreadCliente> clients;
    public ArrayList<User> conectados = new ArrayList<User>();
    public JSONArray list = new JSONArray();

    /**
     * Creates new form SDServerInterface
     */
    public SDServerInterface() {
        initComponents();
        this.setLocationRelativeTo(null);
        start();
    }

    private void start() {
        this.pack();
        this.setVisible(true);
        inicio();
    }

    private void inicio() {
        jLPort.setText(String.valueOf(PORT));
        String connection_info;
        clients = new HashMap<String, ThreadCliente>();
        try {
            System.out.println("Incializando o servidor...");
            server = new ServerSocket(PORT);
            System.out.println("Servidor iniciado, ouvindo a porta " + Utils.ANSI_PURPLE + PORT + Utils.ANSI_RESET);
            //Aguarda conexões
            while (true) {
                Socket connection = server.accept();
                connection_info = connection.getInetAddress().getHostName();
                System.out.println(Utils.ANSI_GREEN + connection_info + " Conectado" + Utils.ANSI_RESET);
                //connection_info = Utils.receiveMessage(connection);              
                ThreadCliente cl = new ThreadCliente(connection_info, connection, this);
                clients.put(connection_info, cl);
                //Utils.sendMessage(connection, "Sucess");             
                //System.out.println(clients.get(0));
                new Thread(cl).start();
                //Inicia thread do cliente
                //new ThreadCliente(client).start();

            }
        } catch (IOException ex) {
            System.err.println("Conexão Perdida com o Cliente");
        }
    }

    public Map<String, ThreadCliente> getClients() {
        return clients;
    }

    private boolean checkLogin(String connection_info) {
        String[] splited = connection_info.split(":");
        for (Map.Entry<String, ThreadCliente> pair : clients.entrySet()) {
            String[] parts = pair.getKey().split(":");
            if (parts[0].equals(splited[0])) {
                return false;
            } else if ((parts[1] + parts[2]).equals(splited[1] + splited[2])) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLPort = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Servidor ativo na porta:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLPort, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(266, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SDServerInterface sdserverInterface = new SDServerInterface();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLPort;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
