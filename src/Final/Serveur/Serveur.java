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
        int clientsC=0;
        int clientsJ=0;
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


            while (true) {
                Socket s = serveur.accept();
                System.out.println("\nUn client s'est connecté: "+clientsJ);
                System.out.println(s.getInetAddress());
                int ecran = s.getInputStream().read();
                if (ecran==0) {
                    physique.addCatapulte(clientsJ);
                    s.getOutputStream().write(clientsJ);
                    System.out.println("\nUn client joueur s'est connecté: "+clientsJ);
                    clientsJ++;
                }
                else if(ecran ==1){
                    s.getOutputStream().write(clientsC);
                    System.out.println("\nUn client ciel s'est connecté: "+clientsC);
                    clientsC++;
                }
                else if(ecran==-1){
                    s.getOutputStream().write(-1);
                    System.out.println("\nUn client observateur s'est connecté");
                }
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

0 mouvemnt
1 lancer

 */