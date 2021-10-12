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
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author thi_s
 */
public class SDServer {
    
    private static final int PORT = 1234;

    private ServerSocket server;
    private Map<String, ThreadCliente> clients;
    public SDServer(){
        
        String connection_info;
        clients = new HashMap<String, ThreadCliente>();
        try {
            System.out.println("Incializando o servidor...");
            ServerSocket serv = new ServerSocket(PORT);
            System.out.println("Servidor iniciado, ouvindo a porta " + PORT);
            //Aguarda conexões
            while (true) {
                Socket connection = serv.accept();
                connection_info = Utils.receiveMessage(connection);
                ThreadCliente cl = new ThreadCliente(connection_info, connection, this);
                clients.put(connection_info, cl);
                Utils.sendMessage(connection, "Sucess");
                //Inicia thread do cliente
                //new ThreadCliente(client).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(SDServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    private boolean checkLogin(String connection_info){
//        for(Map.Entry<String,ThreadCliente> pair: clients.entrySet()){
//            
//        }
//    }
}
