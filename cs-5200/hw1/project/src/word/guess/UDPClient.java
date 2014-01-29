/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package word.guess;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alan
 */
public class UDPClient {

    private DatagramSocket clientSocket;
    private InetAddress IPAddress;
    private Integer port;

    public UDPClient(String host, Integer port) {
        this.port = port;

        try {
            clientSocket = new DatagramSocket();
            IPAddress = InetAddress.getByName(host);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String send(String message) throws Exception {
        byte[] sendData = message.getBytes();
        byte[] receiveData = new byte[1024];
        DatagramPacket sendPacket = new DatagramPacket(
                sendData, sendData.length, IPAddress, port
        );
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(
                receiveData, receiveData.length
        );
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println(
                "FROM SERVER:" + modifiedSentence
        );

        return modifiedSentence;
    }

    public GameDef sendNewGame(String aNumber, String lastName,
            String firstName) throws Exception {

        String message = "newgame:" + aNumber + "," + lastName + "," + firstName;
        String response;

        response = send(message);

        String[] colonSplit = response.split(":");

        if (colonSplit[0].equals("def")) {
            String[] commaSplit = colonSplit[1].split(",");

            if (commaSplit.length == 3) {
                String gameID = commaSplit[0].trim();
                String hint = commaSplit[1].trim();
                String definition = commaSplit[2].trim();

                return new GameDef(gameID, hint, definition);
            } else if (commaSplit.length > 3) {
                String stuff = colonSplit[1];

                Integer comma1 = stuff.indexOf(",");
                Integer comma2 = stuff.indexOf(",", comma1 + 1);

                String gameID = stuff.substring(0, comma1);
                String hint = stuff.substring(comma1 + 1, comma2);
                String definition = stuff.substring(comma2 + 1).trim();

                return new GameDef(gameID, hint, definition);
            } else {
                throw new Exception("Something broke: I only got "
                        + commaSplit.length + " items in the response on colon split.");
            }
        } else {
            throw new Exception("Something broke: I only got "
                    + colonSplit.length + " items in the response on comma split.");
        }
    }

    public class GameDef {

        public String gameID;
        public String hint;
        public String definition;

        public GameDef(String gameID, String hint, String definition) {
            this.gameID = gameID;
            this.hint = hint;
            this.definition = definition;
        }
    }

    public Answer sendGuess(String gameID, String word) throws Exception {

        String message = "guess:" + gameID + "," + word;
        String response;

        response = send(message);

        String[] colonSplit = response.split(":");

        if (colonSplit[0].equals("answer")) {
            String[] commaSplit = colonSplit[1].split(",");

            if (commaSplit.length == 3) {
                gameID = commaSplit[0].trim();
                String result = commaSplit[1].trim();
                String score = commaSplit[2].trim();

                return new Answer(gameID, result, score);
            } else {
                throw new Exception("Something broke: I only got "
                        + commaSplit.length + " items in the response on colon split.");
            }
        } else {
            throw new Exception("Something broke: I only got "
                    + colonSplit.length + " items in the response on comma split.");

        }
    }

    public class Answer {

        public String gameID;
        public String result;
        public String score;

        public Answer(String gameID, String result, String score) {
            this.gameID = gameID;
            this.result = result;
            this.score = score;
        }
    }

    public Hint sendGetHint(String gameID) throws Exception {

        String message = "gethint:" + gameID;
        String response;

        response = send(message);

        String[] colonSplit = response.split(":");

        if (colonSplit[0].equals("hint")) {
            String[] commaSplit = colonSplit[1].split(",");

            if (commaSplit.length == 2) {
                gameID = commaSplit[0].trim();
                String hint = commaSplit[1].trim();

                return new Hint(gameID, hint);
            } else {
                throw new Exception("Something broke: I only got "
                        + commaSplit.length + " items in the response on colon split.");
            }
        } else {
            throw new Exception("Something broke: I only got "
                    + colonSplit.length + " items in the response on comma split.");

        }
    }

    public class Hint {

        public String gameID;
        public String hint;

        public Hint(String gameID, String hint) {
            this.gameID = gameID;
            this.hint = hint;
        }
    }

    public String sendExit(String gameID) throws Exception {

        String message = "exit:12";// + gameID;
        String response = "";

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(
                sendData, sendData.length, IPAddress, port
        );
        clientSocket.send(sendPacket);

        if (response.trim().equals("")) {
            return "Exited";
        } else {
            String[] colonSplit = response.split(":");

            if (colonSplit[0].equals("error")) {
                return colonSplit[1].trim();
            } else {
                return "Error";
            }
        }
    }
}
