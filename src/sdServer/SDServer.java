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
                System.out.println("Conexao aceita");
                connection_info = Utils.receiveMessage(connection);
                ThreadCliente cl = new ThreadCliente(connection_info, connection, this);
                clients.put(connection_info, cl);
                Utils.sendMessage(connection, "Sucess");
                new Thread(cl).start();
                //Inicia thread do cliente
                //new ThreadCliente(client).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(SDServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Map<String, ThreadCliente> getClients(){
        return clients;
    }
    
    private boolean checkLogin(String connection_info){
        String[] splited = connection_info.split(":");
        for(Map.Entry<String, ThreadCliente> pair : clients.entrySet()){
            String[] parts = pair.getKey().split(":");
            if (parts[0].equals(splited[0])){
                return false;
            }else if((parts[1] + parts[2]).equals(splited[1] + splited[2])){
                return false;
            }
        }
        return true;
    }
    
//    private boolean checkLogin(String connection_info){
//        for(Map.Entry<String,ThreadCliente> pair: clients.entrySet()){
//            
//        }
//    }
}
