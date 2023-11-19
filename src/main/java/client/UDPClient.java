package client;

import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName("localhost"); // Adresse IP du serveur (localhost dans cet exemple)
            int serverPort = 1234; // Port sur lequel le serveur écoute

            String request = "heure"; // Demande l'heure au serveur
            byte[] sendData = request.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort);
            clientSocket.send(sendPacket); // Envoi de la demande au serveur

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket); // Réception de la réponse du serveur

            String dateTime = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Date et heure actuelles : " + dateTime);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}