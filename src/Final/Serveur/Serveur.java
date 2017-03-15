package Final.Serveur;

import Final.Serveur.Controller.Emission;
import Final.Serveur.Controller.Physique;
import Final.Serveur.Controller.Reception;

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

        Emission emission = new Emission();
        physique = new Physique(emission);
        reception = new Thread(new Reception(physique));

        try {
            serveur = new ServerSocket(9000);

            System.out.println("Mise en place du serveur . . .");
            Thread.sleep(1500);

            physique.start();
            reception.start();

            System.out.println(InetAddress.getLocalHost()+" en ligne");


            while (clients<6) {
                Socket s = serveur.accept();
                System.out.println("\nUn client s'est connecté: "+clients);
                System.out.println(s.getInetAddress());
                if (clients<2)
                    physique.addCatapulte(clients);
                s.getOutputStream().write(clients);
                clients++;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
9000 - serveur
9001 - donnees projectiles
9002 - nouveau projectile
 */