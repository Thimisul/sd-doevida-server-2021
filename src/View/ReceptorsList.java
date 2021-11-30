/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author thi_s
 */
public class ReceptorsList extends javax.swing.JFrame {

    Socket server;
    ObjectOutputStream saida;

     public ReceptorsList(Socket server) { 
        this.server = server;
        initComponents();
        start();
    }

    private void start(){
        this.pack();
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

        jLabel3 = new javax.swing.JLabel();
        jPDonor = new javax.swing.JPanel();
        jLConnected = new javax.swing.JLabel();
        jLSelectReceptor = new javax.swing.JLabel();
        jSPReceptorList = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLFilter = new javax.swing.JLabel();
        jTFFilter = new javax.swing.JTextField();
        jBChatReceptor = new javax.swing.JButton();
        jPReceptor = new javax.swing.JPanel();
        jLLogin = new javax.swing.JLabel();
        jTFLogin = new javax.swing.JTextField();
        jLPassword = new javax.swing.JLabel();
        jPFPassword = new javax.swing.JPasswordField();
        jBLogin = new javax.swing.JButton();
        jBCreateReceptor = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPDonor.setBorder(javax.swing.BorderFactory.createTitledBorder("Sou Doador"));

        jLConnected.setText("Conectado");

        jLSelectReceptor.setText("Selecione um Receptor de doações na lista: ");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jSPReceptorList.setViewportView(jList1);

        jLFilter.setText("Filtro:");
        jLFilter.setToolTipText("");

        jBChatReceptor.setText("Doar / Abrir Chat com Receptor numero 1");
        jBChatReceptor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBChatReceptorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPDonorLayout = new javax.swing.GroupLayout(jPDonor);
        jPDonor.setLayout(jPDonorLayout);
        jPDonorLayout.setHorizontalGroup(
            jPDonorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDonorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDonorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSPReceptorList)
                    .addGroup(jPDonorLayout.createSequentialGroup()
                        .addComponent(jLFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFFilter))
                    .addGroup(jPDonorLayout.createSequentialGroup()
                        .addComponent(jLSelectReceptor)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jBChatReceptor, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDonorLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLConnected)))
                .addContainerGap())
        );
        jPDonorLayout.setVerticalGroup(
            jPDonorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDonorLayout.createSequentialGroup()
                .addComponent(jLConnected)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLSelectReceptor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDonorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLFilter)
                    .addComponent(jTFFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSPReceptorList, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBChatReceptor)
                .addContainerGap())
        );

        jPReceptor.setBorder(javax.swing.BorderFactory.createTitledBorder("Sou um Receptor"));

        jLLogin.setText("Login:");

        jLPassword.setText("Password:");

        jBLogin.setText("Login");
        jBLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLoginActionPerformed(evt);
            }
        });

        jBCreateReceptor.setText("Ainda não tenho uma conta de Receptor");

        javax.swing.GroupLayout jPReceptorLayout = new javax.swing.GroupLayout(jPReceptor);
        jPReceptor.setLayout(jPReceptorLayout);
        jPReceptorLayout.setHorizontalGroup(
            jPReceptorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPReceptorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPReceptorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPReceptorLayout.createSequentialGroup()
                        .addGroup(jPReceptorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLLogin)
                            .addComponent(jLPassword))
                        .addGap(10, 10, 10)
                        .addGroup(jPReceptorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPFPassword)
                            .addComponent(jTFLogin)))
                    .addComponent(jBLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBCreateReceptor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPReceptorLayout.setVerticalGroup(
            jPReceptorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPReceptorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPReceptorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLLogin)
                    .addComponent(jTFLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPReceptorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLPassword)
                    .addComponent(jPFPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCreateReceptor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPDonor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPReceptor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPDonor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBChatReceptorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBChatReceptorActionPerformed
         new Chat("receptor1",server).setVisible(true);//que quer abrir
    }//GEN-LAST:event_jBChatReceptorActionPerformed

    private void jBLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLoginActionPerformed
        new Login(server).setVisible(true);//que quer abrir
        dispose();
    }//GEN-LAST:event_jBLoginActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBChatReceptor;
    private javax.swing.JButton jBCreateReceptor;
    private javax.swing.JButton jBLogin;
    private javax.swing.JLabel jLConnected;
    private javax.swing.JLabel jLFilter;
    private javax.swing.JLabel jLLogin;
    private javax.swing.JLabel jLPassword;
    private javax.swing.JLabel jLSelectReceptor;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPDonor;
    private javax.swing.JPasswordField jPFPassword;
    private javax.swing.JPanel jPReceptor;
    private javax.swing.JScrollPane jSPReceptorList;
    private javax.swing.JTextField jTFFilter;
    private javax.swing.JTextField jTFLogin;
    // End of variables declaration//GEN-END:variables
}
