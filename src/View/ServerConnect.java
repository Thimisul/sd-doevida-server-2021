/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author thi_s
 */
public class ServerConnect extends JFrame {
    
    Socket server;

    public ServerConnect() {
        super("DoeVida");
        initComponents();
        this.setLocationRelativeTo(null);
        start();
    }

    private void start(){
        this.pack();
        this.setVisible(true); 
   }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pBody = new javax.swing.JPanel();
        lBind = new javax.swing.JLabel();
        tfServer = new javax.swing.JTextField();
        tfBind = new javax.swing.JTextField();
        lServer = new javax.swing.JLabel();
        pFooter = new javax.swing.JPanel();
        bConnect = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lBind.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lBind.setText("Porta: ");

        tfServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfServerActionPerformed(evt);
            }
        });

        tfBind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfBindKeyPressed(evt);
            }
        });

        lServer.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lServer.setText("Servidor: ");

        javax.swing.GroupLayout pBodyLayout = new javax.swing.GroupLayout(pBody);
        pBody.setLayout(pBodyLayout);
        pBodyLayout.setHorizontalGroup(
            pBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lServer)
                .addGap(4, 4, 4)
                .addComponent(tfServer, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lBind)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfBind, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pBodyLayout.setVerticalGroup(
            pBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBodyLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(pBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lServer)
                    .addComponent(tfServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lBind)
                    .addComponent(tfBind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bConnect.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        bConnect.setText("Conectar");
        bConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pFooterLayout = new javax.swing.GroupLayout(pFooter);
        pFooter.setLayout(pFooterLayout);
        pFooterLayout.setHorizontalGroup(
            pFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFooterLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(bConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pFooterLayout.setVerticalGroup(
            pFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bConnect)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Doações para serviços de saneamento e água potável");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addComponent(pFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfServerActionPerformed

    private void bConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConnectActionPerformed
        send();
    }//GEN-LAST:event_bConnectActionPerformed

    private void tfBindKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBindKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            send();
        }
    }//GEN-LAST:event_tfBindKeyPressed

    private void send(){
        try {
            // TODO add your handling code here:
            server = new Socket(tfServer.getText(), Integer.parseInt(tfBind.getText()));
            //new Chat(server).setVisible(true);//que quer abrir
            new Login(server).setVisible(true);//que quer abrir
            //new ChatReceptor(server).setVisible(true);//que quer abrir
            //new EditRecordUser(server).setVisible(true); // tá lançando a exception da linha 33. Não entendi o motivo
            //new AuthorizePending(server).setVisible(true);
            //new ReceptorsList(server).setVisible(true);
            dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
        "Servidor não encontrado:\n\n", //mensagem
        "Erro 404", // titulo da janela 
        JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
        "A porta precisa ser um numero:\n\n ", //mensagem
        "Erro 404", // titulo da janela
        JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex);
        } 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

       ServerConnect serverConnect = new ServerConnect();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lBind;
    private javax.swing.JLabel lServer;
    private javax.swing.JPanel pBody;
    private javax.swing.JPanel pFooter;
    private javax.swing.JTextField tfBind;
    private javax.swing.JTextField tfServer;
    // End of variables declaration//GEN-END:variables
}
