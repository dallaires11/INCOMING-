package Final.Serveur;

import Final.Serveur.Controller.Physique;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        ServerSocket serveur;
        int clients=0;
        Thread reception;
        Physique physique;

        try {
            serveur = new ServerSocket(9000);

            System.out.println("Mise en place du serveur . . .");
            Thread.sleep(1500);

            System.out.println(InetAddress.getLocalHost()+" en ligne");

            while (true) {
                Socket s = serveur.accept();
                System.out.println("\nUn client s'est connecté: "+clients);
                System.out.println(s.getInetAddress());
                s.getOutputStream().write(clients);
                clients++;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}