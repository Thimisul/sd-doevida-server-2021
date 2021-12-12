/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdServer;

import DAO.DonationDAO;
import DAO.UserDAO;
import entidades.Donation;
import entidades.User;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Utils;

/**
 *
 * @author thi_s
 */
public class ThreadCliente extends Thread {

    private String connection_info;
    private Socket connection;
    private SDServerInterface serversd;
    private boolean running;


    public ThreadCliente(String connection_info, Socket connection, SDServerInterface serversd) {
        this.connection_info = connection_info;
        this.connection = connection;
        this.serversd = serversd;
        this.running = false;
    }

    ThreadCliente(String connection_info, Socket connection, ServerSocket server) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        System.out.println(Utils.ANSI_YELLOW + "SERVIDOR: " + Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET + " Thread Iniciada --> ");
        running = true;
        String messageJson;
        UserDAO userDao;
        DonationDAO donationDao;
        User connectedUSer = new User();
        User findUser;
        User newUser;
        Donation donation;
        List<User> usersList;
        JSONObject jsonMessageO;
        JSONObject response;
        JSONObject jsonO;
        JSONObject responseMessage;
        while (running) {
            try {
                System.out.println(Utils.ANSI_YELLOW + "SERVIDOR: aguardando requisição do cliente: " + Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET);
                messageJson = Utils.receiveMessage(connection);
                jsonO = new JSONObject(messageJson);
                int choice = (Integer) jsonO.opt("protocol");
                System.out.println(Utils.ANSI_GREEN + connection_info + " enviou - >>> " + Utils.ANSI_RESET + messageJson);
                userDao = new UserDAO();
                switch (choice) {
                    case 100: //OK
                        response = new JSONObject();
                        responseMessage = new JSONObject();
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_CYAN + " #100    LOGIN ----> " + Utils.ANSI_RESET);
                        jsonMessageO = (JSONObject) jsonO.opt("message");
                        try {
                            connectedUSer = userDao.userLogin(jsonMessageO.optString("username"), jsonMessageO.optString("password"));
                            connection_info = connection_info.concat(" -- " + connectedUSer.getUsername());
                            System.out.println(Utils.ANSI_GREEN + connection_info + " Logado " + Utils.ANSI_RESET);
                            responseMessage.put("result", true);
                            responseMessage.put("usertype", connectedUSer.getUserType());
                            response.put("protocol", 101);
                            response.put("message", responseMessage);
                        } catch (NoResultException e) {
                            System.err.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET + " Username ou password incorretos ");
                            responseMessage.put("result", false);
                            responseMessage.put("reason", "Login Falhou");
                            response.put("protocol", 102);
                            response.put("message", responseMessage);
                        } finally {
                            System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                            Utils.sendMessage(connection, response.toString());
                        }
                        break;

                    case 199: //OK
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_CYAN + " #100    LOGOUT ----> " + Utils.ANSI_RESET);
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_RESET + " fez Logout");
                        connectedUSer = new User();
                        connection_info = connection.getInetAddress().getHostName();
                        break;

                    case 400:
                        response = new JSONObject();
                        responseMessage = new JSONObject();
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_CYAN + " #400    LISTAR RECEPTADORES ----> " + Utils.ANSI_RESET);
                        jsonMessageO = (JSONObject) jsonO.opt("message");
                        JSONObject jsonFilterO = (JSONObject) jsonMessageO.opt("filter");

                        String filterUsername,
                         filterName,
                         filterCity,
                         filterState;

                        filterUsername = jsonFilterO.optString("username");
                        filterName = jsonFilterO.optString("name");
                        filterCity = jsonFilterO.optString("city");
                        filterState = jsonFilterO.optString("state");

                        usersList = userDao.getReceptors(filterUsername, filterName, filterCity, filterState);

                        if (!usersList.isEmpty()) {
                            responseMessage.put("result", true);
                            JSONArray responseList = new JSONArray();
                            for(int i = 0;i < usersList.size(); i++){
                                JSONObject userO = new JSONObject();
                                userO.put("username", usersList.get(i).getUsername());
                                userO.put("receptor", usersList.get(i).getRecepValidated());
                                userO.put("city", usersList.get(i).getCity());
                                userO.put("state", usersList.get(i).getFederativeUnit());
                                //userO.put("id", usersList.get(i).getId());
                                userO.put("name", usersList.get(i).getName());
                                responseList.put(userO);
                            }
                            responseMessage.put("list", responseList);
                            response.put("protocol", 401);
                            response.put("message", responseMessage);
                        } else {
                            responseMessage.put("result", false);
                            responseMessage.put("reason", "Nenhum Receptor Listado");
                            response.put("protocol", 402);
                            response.put("message", responseMessage);
                        }

                        System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                        Utils.sendMessage(connection, response.toString());

                        break;

                    case 510:
                        response = new JSONObject();
                        responseMessage = new JSONObject();
                        donationDao = new DonationDAO();
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_CYAN + " #510    DOAÇÃO PARA O RECEPTADOR  ----> " + Utils.ANSI_RESET);
                        jsonMessageO = (JSONObject) jsonO.opt("message");
                        try {
                            User findDonor = userDao.getUserByUsername(jsonMessageO.optString("donor"));
                            User findReceptor = userDao.getUserByUsername(jsonMessageO.optString("receptor"));
                            donation = new Donation();
                            donation.setId(null);
                            donation.setIdDonor(findDonor);
                            donation.setIdRecipient(findReceptor.getId());
                            donation.setValue(jsonMessageO.optInt("value"));
                            donation.setIsAnon(false);
                            donation.setDateDonation(Date.from(Instant.now()));
                            
                            donationDao.add(donation);

                            responseMessage.put("result", true);
                            response.put("protocol", 511);
                            response.put("message", responseMessage);

                        } catch (Exception ex) {
                            responseMessage.put("result", false);
                            responseMessage.put("reason", ex);
                            response.put("protocol", 512);
                            response.put("message", responseMessage);
                        }

                        System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                        Utils.sendMessage(connection, response.toString());

                        break;

                    case 600:
                        response = new JSONObject();
                        responseMessage = new JSONObject();
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_CYAN + " #600    LISTAR PENDENTES ----> " + Utils.ANSI_RESET);
                        if (connectedUSer.getUserType() == 3) {
                            usersList = userDao.getPendentUsers();
                            responseMessage.put("result", true);
                            responseMessage.put("list", usersList);
                            response.put("protocol", 601);
                            response.put("message", responseMessage);
                            System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                            Utils.sendMessage(connection, response.toString());
                        } else {
                            responseMessage.put("result", false);
                            responseMessage.put("reason", "Usuario não é administrador");
                            response.put("protocol", 602);
                            response.put("message", responseMessage);
                            System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                            Utils.sendMessage(connection, response.toString());
                        }
                        break;

                    case 610:
                        response = new JSONObject();
                        responseMessage = new JSONObject();
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_CYAN + " #610    ADIÇAO DE RECEPTOR ----> " + Utils.ANSI_RESET);

                        if (connectedUSer.getUserType() == 3) {
                            try {
                                jsonMessageO = (JSONObject) jsonO.opt("message");
                                userDao.receptorAcept(jsonMessageO.getString("username"), jsonMessageO.optInt("receptor"));
                                if (jsonMessageO.optInt("receptor") == 1) {
                                    responseMessage.put("result", true);
                                    responseMessage.put("message", "Aprovado");
                                    response.put("protocol", 611);
                                    response.put("message", responseMessage);
                                } else {
                                    responseMessage.put("result", false);
                                    responseMessage.put("reason", "Reprovado");
                                    response.put("protocol", 612);
                                    response.put("message", responseMessage);
                                }

                                System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                                Utils.sendMessage(connection, response.toString());

                            } catch (Exception ex) {
                                Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            responseMessage.put("result", false);
                            responseMessage.put("reason", "Usuario não é administrador");
                            response.put("protocol", 602);
                            response.put("message", responseMessage);
                            System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                            Utils.sendMessage(connection, response.toString());
                        }
                        break;

                    case 700: //OK
                        response = new JSONObject();
                        responseMessage = new JSONObject();
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_CYAN + " #100    CADASTRO ----> " + Utils.ANSI_RESET);
                        jsonMessageO = (JSONObject) jsonO.opt("message");
                        newUser
                                = new User(null, //id
                                        jsonMessageO.getString("name"),//name
                                        jsonMessageO.getString("username"), //username
                                        1,//Type doador
                                        jsonMessageO.optString("city"),
                                        jsonMessageO.optString("state"),//federativeUnit
                                        99,//recepValidated
                                        jsonMessageO.optString("password")
                                );

                        findUser = userDao.getUserByUsername(jsonMessageO.optString("username"));

                        if (findUser == null) {
                            try {
                                userDao.add(newUser);
                                System.out.println(Utils.ANSI_YELLOW + "SERVIDOR: " + Utils.ANSI_GREEN + " Usuario " + newUser.getUsername() + "Cadastrado " + Utils.ANSI_RESET);
                                responseMessage.put("result", true);
                                response.put("protocol", 701);
                                response.put("message", responseMessage);
                                System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                                Utils.sendMessage(connection, response.toString());
                            } catch (Exception ex) {
                                Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            System.err.println(Utils.ANSI_YELLOW + "SERVIDOR: " + Utils.ANSI_RESET + "Usuario já existe: " + findUser.getUsername());
                            responseMessage.put("result", false);
                            responseMessage.put("reason", "Usuario já existe");
                            response.put("protocol", 702);
                            response.put("message", responseMessage);
                            System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                            Utils.sendMessage(connection, response.toString());
                        }
                        break;

                    case 710: //OK
                        response = new JSONObject();
                        responseMessage = new JSONObject();
                        jsonMessageO = (JSONObject) jsonO.opt("message");
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_CYAN + " #710    CONSULTA DE CADASTRO UNICO POR NOME ----> " + Utils.ANSI_RESET);
                        findUser = userDao.getUserByUsernameEdit(jsonMessageO.optString("username"));
                        if (findUser != null) {
                            System.out.println(Utils.ANSI_YELLOW + "SERVIDOR: " + Utils.ANSI_GREEN + "Usuario Encontrado " + findUser.toString() + Utils.ANSI_RESET);
                            response.put("protocol", 711);
                            responseMessage.put("result", true);
                            responseMessage.put("name", findUser.getName());
                            responseMessage.put("city", findUser.getCity());
                            responseMessage.put("state", findUser.getFederativeUnit());
                            responseMessage.put("receptor", findUser.getRecepValidated());
                            response.put("message", responseMessage);
                        } else {
                            System.err.println(Utils.ANSI_YELLOW + "SERVIDOR: " + Utils.ANSI_RESET + "Usuario não encontrado ");
                            responseMessage.put("result", false);
                            responseMessage.put("reason", "Usuario não encontrado");
                            response.put("protocol", 712);
                            response.put("message", responseMessage);
                        }
                        System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                        Utils.sendMessage(connection, response.toString());
                        break;

                    case 720: //
                        response = new JSONObject();
                        responseMessage = new JSONObject();
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_CYAN + " #720    SALVAR ATUALIZAÇÕES DO CADASTRO ----> " + Utils.ANSI_RESET);
                        responseMessage = new JSONObject();
                        jsonMessageO = (JSONObject) jsonO.opt("message");
                        User userEdit = new User();
                        userEdit.setId(connectedUSer.getId());
                        userEdit.setUsername(connectedUSer.getUsername());
                        userEdit.setName(jsonMessageO.optString("name"));
                        userEdit.setUserType(connectedUSer.getUserType());
                        userEdit.setCity(jsonMessageO.optString("city"));
                        userEdit.setFederativeUnit(jsonMessageO.optString("state").toUpperCase().substring(0, 2));
                        userEdit.setPassword(jsonMessageO.optString("password"));
                        userEdit.setRecepValidated(jsonMessageO.optInt("receptor"));
                         {
                            try {
                                userDao.editUser(userEdit);
                                response.put("protocol", 721);
                                responseMessage.put("result", true);
                                response.put("message", responseMessage);
                            } catch (Exception ex) {
                                System.err.println(ex);
                                response.put("protocol", 722);
                                responseMessage.put("result", false);
                                responseMessage.put("reason", ex.toString());
                                response.put("message", responseMessage);
                            } finally {
                                System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                                utils.Utils.sendMessage(connection, response.toString());
                            }
                        }
                        break;

                    case 900: //OK
                        response = new JSONObject();
                        responseMessage = new JSONObject();
                        findUser = new User();
                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_CYAN + " #900    DELETE ----> " + Utils.ANSI_RESET);
                        jsonMessageO = (JSONObject) jsonO.opt("message");
                        try {
                            if (jsonMessageO.optString("username").equals("")) {
                                System.out.println("username não enviado");
                                findUser = userDao.getUserByUsername(connectedUSer.getUsername());
                            } else {
                                findUser = userDao.getUserByUsername(jsonMessageO.optString("username"));
                            }
                            userDao.remove(findUser.getId());
                            response.put("protocol", 901);
                            responseMessage.put("result", true);
                            response.put("message", responseMessage);
                        } catch (NonexistentEntityException ex) {
                            response.put("protocol", 901);
                            responseMessage.put("result", true);
                            responseMessage.put("reason", ex);
                            response.put("message", responseMessage);
                        } catch (IllegalOrphanException ex) {
                            response.put("protocol", 901);
                            responseMessage.put("result", true);
                            responseMessage.put("reason", ex);
                            response.put("message", responseMessage);
                        }

                        System.out.println(Utils.ANSI_GREEN + connection_info + Utils.ANSI_RED + " Deletado" + Utils.ANSI_RESET);
                        System.out.print(Utils.ANSI_YELLOW + "SERVIDOR enviou - >>> " + Utils.ANSI_RESET);
                        utils.Utils.sendMessage(connection, response.toString());
                        connectedUSer = new User();
                        connection_info = connection.getInetAddress().getHostName();
                        break;

                    default:
                        System.err.println(Utils.ANSI_YELLOW + "SERVIDOR: " + Utils.ANSI_RESET + "protocolo inexistente: " + choice);

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
            } catch (NullPointerException ex) {
                System.out.println(ex.getCause().getMessage());
                System.out.print(Utils.ANSI_RED + connection_info + Utils.ANSI_RESET);
                running = false;
            }
        }
    }
}
