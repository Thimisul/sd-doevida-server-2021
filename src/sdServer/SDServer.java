/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thi_s
 */
public class SDServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Informe a porta a ser ouvida pelos servidor");
//            System.exit(0);
//        }
        try {
            //Converte o parametro recebido para int (número da porta)
            int port = 1234;
            System.out.println("Incializando o servidor...");
            //Iniciliza o servidor
            ServerSocket serv = new ServerSocket(port);
            System.out.println("Servidor iniciado, ouvindo a porta " + port);
            //Aguarda conexões
            while (true) {
                Socket clie = serv.accept();
                //Inicia thread do cliente
                new ThreadCliente(clie).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(SDServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
