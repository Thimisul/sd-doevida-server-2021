/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdServer;

import com.sun.security.ntlm.Server;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    public void run() {
        try {
            String msgReceive = "";
            ObjectInputStream entrada = null;
            ObjectOutputStream saida = null;

            while (!msgReceive.equals("sair")) {
                entrada = new ObjectInputStream(connection.getInputStream());
                saida = new ObjectOutputStream(connection.getOutputStream());
                msgReceive = (String) entrada.readObject();

                saida.flush();

                saida.writeObject( " - Recebido - ");
                
                System.out.println("#########################################################################");
                System.out.println("Cliente atendido com sucesso: ");
                System.out.println("Mensagem Recebida: "+ msgReceive);
                System.out.println(connection.getRemoteSocketAddress().toString());
                System.out.println("#########################################################################");
            }

            entrada.close();
            saida.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Excecao ocorrida na thread: " + e.getMessage());
            try {
                connection.close();
            } catch (Exception ec) {
            }
        }
    }
}
