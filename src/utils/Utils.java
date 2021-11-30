/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thi_s
 */
public class Utils {

    public static String federativeUnit[] = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static boolean sendMessage(Socket connection, String message) {
        try {
            DataOutputStream output = new DataOutputStream(connection.getOutputStream());
            output.flush();
            output.write((message + "\n").getBytes("UTF-8"));
            output.flush();
            System.out.println("Mensagem enviada: " + message);
            output.flush();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String receiveMessage(Socket connection) {

        String response = null;

        try {
            DataInputStream input = new DataInputStream(connection.getInputStream());
            response = input.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}

//    private static String toAscii(final String hexInput) {
//        final int length = hexInput.length();
//        final StringBuilder ascii = new StringBuilder();
//        final StringBuilder integers = new StringBuilder();
//        for (int i = 0; i < length; i += 2) {
//            final String twoDigitHex = hexInput.substring(i, i + 2);
//            final int integer = Integer.parseInt(twoDigitHex, 16);
//            ascii.append((char) integer);
//            integers.append(String.format("%03d", integer)).append(" ");
//        }
//        return hexInput + " ==> " + integers.deleteCharAt(integers.length() - 1).toString() + " ==> " + ascii.toString();
//    }
//}
