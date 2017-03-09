package Protoype.Serveur;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class Serveur {

    public static void main(String[] args) {
        ServerSocket serveur;
        Physique physique;
        Thread reception;

        try {

            serveur = new ServerSocket(9012);

            System.out.println(InetAddress.getLocalHost());

            physique = new Physique();
            physique.start();

            reception = new Thread(new Reception(physique));
            reception.start();

            while (true) {
                Socket s = serveur.accept();

                System.out.println("Un client s'est connecté");
                System.out.println(s.getInetAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}