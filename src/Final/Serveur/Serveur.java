package Final.Serveur;

import Final.Serveur.Controller.Emission;
import Final.Serveur.Controller.GestionnnaireThread;
import Final.Serveur.Controller.Physique;
import Final.Serveur.Controller.Reception;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur implements GestionnnaireThread{
    Physique physique;
    Thread reception;
    public static void main(String[] args) {

        Serveur s = new Serveur();
        s.start();

    }

    public void start(){
        ServerSocket serveur;
        int clientsC = 0;
        int clientsJ = 0;

        boolean running = false;

        Emission emission = new Emission();
        physique = new Physique(emission,this);
        reception = new Thread(new Reception(physique,emission,this));

        try {
            serveur = new ServerSocket(9000);

            System.out.println("Mise en place du serveur . . .");
            Thread.sleep(1500);

            reception.start();

            System.out.println(InetAddress.getLocalHost() + " en ligne");


            while (true) {
                Socket s = serveur.accept();
                System.out.println(s.getInetAddress());
                int ecran = s.getInputStream().read();
                System.out.println(ecran);
                if (ecran == 0) {
                    s.getOutputStream().write(clientsC);
                    System.out.println("\nUn client ciel s'est connecté: " + clientsC);
                    clientsC++;
                } else if (ecran == 1) {
                    s.getOutputStream().write(clientsJ);
                    System.out.println("\nUn client joueur s'est connecté: " + clientsJ);
                    clientsJ++;
                } else if (ecran == 10) {
                    s.getOutputStream().write(10);
                    System.out.println("\nUn client observateur s'est connecté");
                }
                s.getOutputStream().flush();

                if (clientsJ >= 2 && !running) {
                    running = true;
                    physique.addCatapulte(0);
                    physique.addCatapulte(1);
                    physique.setInterface();
                    physique.start();
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finJeu(){
        try {
            physique.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void recommencer(){
        physique.recommencer();
        //physique.notify();
    }
}

/*
9000 - serveur
9001 - donnees projectiles
9002 - nouveau projectile

0 mouvemnt
1 lancer

6 fin de jeu

8 debut de jeu

 */