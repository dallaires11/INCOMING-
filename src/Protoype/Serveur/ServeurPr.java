package Protoype.Serveur;

import java.io.*;
import java.net.*;

public class ServeurPr {

    public static void main(String[] args) {
        ServerSocket serveur;
        PhysiquePr physique;
        Thread reception;

        try {

            serveur = new ServerSocket(9012);

            System.out.println(InetAddress.getLocalHost());

            physique = new PhysiquePr();
            physique.start();

            reception = new Thread(new ReceptionPr(physique));
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