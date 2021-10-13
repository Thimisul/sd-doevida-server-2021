/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdServer;


import java.net.Socket;
import java.util.Map;
import utils.Utils;

/**
 *
 * @author thi_s
 */
public class ThreadCliente extends Thread {

    private String connection_info;
    private Socket connection;
    private SDServer server;
    private boolean running;

    public ThreadCliente(String connection_info, Socket connection, SDServer server) {
        this.connection_info = connection_info;
        this.connection = connection;
        this.server = server;
        this.running = false;
    }

//    public void run() {
//        try {
//            String msgReceive = "";
//            ObjectInputStream entrada = null;
//            ObjectOutputStream saida = null;
//
//            while (!msgReceive.equals("sair")) {
//                entrada = new ObjectInputStream(connection.getInputStream());
//                saida = new ObjectOutputStream(connection.getOutputStream());
//                msgReceive = (String) entrada.readObject();
//
//                saida.flush();
//
//                saida.writeObject( " - Recebido - ");
//                
//                System.out.println("#########################################################################");
//                System.out.println("Cliente atendido com sucesso: ");
//                System.out.println("Mensagem Recebida: "+ msgReceive);
//                System.out.println(connection.getRemoteSocketAddress().toString());
//                System.out.println("#########################################################################");
//            }
//
//            entrada.close();
//            saida.close();
//            connection.close();
//        } catch (Exception e) {
//            System.out.println("Excecao ocorrida na thread: " + e.getMessage());
//            try {
//                connection.close();
//            } catch (Exception ec) {
//            }
//        }
//    }
   
    
    public void run(){
                    System.out.println("Thread Iniciada");
        running = true;
        String message;
        while(running){
            message = Utils.receiveMessage(connection);
            if(message.equals("QUIT")){
                System.out.println("running false");
                running = false;
            } else if (message.equals("GET_CONNECTED_RECEPTORS")) {
                System.out.println("Solicitação de lista de receptores...");
                String response = "";
                for(Map.Entry<String, ThreadCliente> pair : server.getClients().entrySet()){
                    response += (pair.getKey() + ";");
                }
                Utils.sendMessage(connection, response);
            } else {
                System.out.println(connection.getRemoteSocketAddress() + " Enviou: " + message);
            }
        }
    }
}
