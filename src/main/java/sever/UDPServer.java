package sever;

import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(1234); // Le serveur écoute sur le port 1234

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket); // Attente de la demande du client

                InetAddress clientIP = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());

                if (request.trim().equalsIgnoreCase("heure")) {
                    // Récupération de la date et de l'heure actuelles
                    String dateTime = java.time.LocalDateTime.now().toString();
                    sendData = dateTime.getBytes();

                    // Envoi de la date et de l'heure au client
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
                    serverSocket.send(sendPacket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
