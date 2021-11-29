/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdServer;

import DAO.UserDAO;
import entidades.User;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import org.json.JSONObject;
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
    public void run() {
        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET + "Thread Iniciada -- ");
        running = true;
        String messageJson = "";
        User connectedUSer = new User();
        User findUser;
        User newUser;
        JSONObject jsonMessageO = null;
        JSONObject response = new JSONObject();
        JSONObject responseMessage = new JSONObject();
        while (running) {
//            try{
            System.out.println( " Servidor aguardando requisição do cliente: " + Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET);
            messageJson = Utils.receiveMessage(connection);
            JSONObject jsonO = new JSONObject(messageJson);
            int choice = (Integer) jsonO.opt("protocol");
            System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET + " enviou --> "  + messageJson);
            UserDAO userDao = new UserDAO(); 

            switch (choice) {
                case 100: //OK
                    System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET +" #100#--LOGIN ----> ");
                    jsonMessageO = (JSONObject) jsonO.opt("message");
                    String username = (String) jsonMessageO.opt("username");
                    String password = (String) jsonMessageO.opt("password");
                    try{
                    connectedUSer = userDao.userLogin(username, password);
                    connection_info = connection_info.concat(" -- " + connectedUSer.getUserName());
                        System.out.println("Usuario Conectado: " + Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET);
                        responseMessage.put("result", true);
                        response.put("protocol", 101);
                        response.put("message", responseMessage);
                    }catch(NoResultException e){
                        System.err.println("Username ou password incorretos");
                        responseMessage.put("result", false);
                        responseMessage.put("reason", "Login Falhou");
                        response.put("protocol", 102);
                        response.put("message", responseMessage);
                    } finally {
                    Utils.sendMessage(connection, response.toString());
                    System.out.println(Utils.ANSI_YELLOW + "Servidor enviou - >>> " + Utils.ANSI_RESET + response.toString());
                    }
                    break;

                case 199: //OK
                    System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET +" #100#--LOGOUT ----> ");
                    break;

                case 700: //OK
                    System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET +" #100#--CADASTRO ----> ");
                    jsonMessageO = (JSONObject) jsonO.opt("message");
                    newUser
                            = new User(null, //id
                                    jsonMessageO.optString("name"),//name
                                    jsonMessageO.optString("username"), //username
                                    1,//Type doador
                                    "Ponta Grossa",//jsonMessageO.optString("city"),
                                    "pr",//jsonMessageO.optString("federativeUnit"),//federativeUnit
                                    99,//recepValidated
                                    jsonMessageO.optString("password")
                            );

                    findUser = userDao.getUserByUsername(jsonMessageO.optString("username"));

                    if (findUser == null) {
                        try {
                            userDao.add(newUser);
                            System.out.println("Usuario Cadastrado");
                            responseMessage.put("result", true);
                            response.put("protocol", 101);
                            response.put("message", responseMessage);
                        } catch (Exception ex) {
                            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.out.println("Usuario já existe" + findUser.getName());
                        responseMessage.put("result", false);
                        responseMessage.put("reason", "Usuario já existe");
                        response.put("protocol", 702);
                        response.put("message", responseMessage);
                    }
                    break;

                case 710:
                    System.out.println(connection_info + "--- 710.Consulta de Cadastro unico por nome ----> ");
                    findUser = userDao.getUserByUsernameEdit(jsonMessageO.optString("username"));
                    if (findUser != null) {
                        System.out.println("Usuario Encontrado" + findUser.toString());
                        response.put("protocol", 711);
                        responseMessage.put("result", true);
                        responseMessage.put("name", findUser.getName());
                        responseMessage.put("city", findUser.getCity());
                        responseMessage.put("federative_unit", findUser.getFederativeUnit());
                        responseMessage.put("receptor", findUser.getRecepValidated());
                        response.put("message", responseMessage);
                        //System.out.println("threadcliente.java " + responseMessage);

                        System.out.println("Usuario encontrado" + responseMessage);
                        responseMessage.put("result", true);
                        //response.put("protocol", 101);
                        response.put("message", responseMessage);
                    } else {
                        System.out.println("Usuario não existe" + findUser.toString());
                        responseMessage.put("result", false);
                        responseMessage.put("reason", "Usuario já existe");
                        response.put("protocol", 712);
                        response.put("message", responseMessage);
                    }
                    System.out.println("Resposta - >>> " + response.toString());
                    Utils.sendMessage(connection, response.toString());
                    break;
                case 720:
                    System.out.println(connection_info + "--- 720. Salvar atualizaçao do Cadastro ----> ");
                    jsonMessageO = null;
                    jsonMessageO = (JSONObject) jsonO.opt("message");

                    User userEdit = new User();
                    userEdit.setId(connectedUSer.getId());
                    userEdit.setUserName(connectedUSer.getUserName());
                    userEdit.setRecepValidated(connectedUSer.getRecepValidated());
                    userEdit.setName(jsonMessageO.optString("name"));
                    userEdit.setCity(jsonMessageO.optString("city"));
                    userEdit.setFederativeUnit(jsonMessageO.optString("state").toUpperCase().substring(0, 2));
                    userEdit.setPassword(jsonMessageO.optString("password"));
                    userEdit.setRecepValidated(jsonMessageO.optInt("receptor "));
                     {
                        try {
                            System.out.println("Id usuario para edição: " + userEdit.getId());
                            userDao.editUser(userEdit);
                            response.put("protocol", 721);
                            responseMessage.put("result", true);
                            response.put("message", responseMessage);
                            utils.Utils.sendMessage(connection, response.toString());
                        } catch (Exception ex) {
                            System.err.println(ex);
                            response.put("protocol", 722);
                            responseMessage.put("result", false);
                            responseMessage.put("reason", ex.toString());
                            response.put("message", responseMessage);
                            utils.Utils.sendMessage(connection, response.toString());
                        }
                    }
                    break;

                default:
                    System.err.println("protocolo inexistente: " + choice);

            }

//            if(message.equals("QUIT")){
//                System.out.println("running false");
//                running = false;
//            } else if (message.equals("GET_CONNECTED_RECEPTORS")) {
//                System.out.println("Solicitação de lista de receptores...");
//                String response = "";
//                for(Map.Entry<String, ThreadCliente> pair : server.getClients().entrySet()){
//                    response += (pair.getKey() + ";");
//                }
//                Utils.sendMessage(connection, response);
//            } else {
//                System.out.println(connection.getRemoteSocketAddress() + " Enviou: " + message);
//            }  
//        } catch ( SocketException ex){
//                System.out.println("Conexão perdida com o Cliente: " + connection_info);
//        }
        }
    }
}
