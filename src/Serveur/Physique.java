package Serveur;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

class Physique extends Thread{
    private ArrayList<Projectile> projectiles=new ArrayList<>();
    private int nbVoyeur;
    private MulticastSocket envoyeur;
    private InetAddress adresse;

    Physique(){
        nbVoyeur = 0;
        try {

            adresse=InetAddress.getByName("224.0.6.0");
            envoyeur = new MulticastSocket(4445);
            envoyeur.joinGroup(InetAddress.getByName("224.0.6.0"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addProjectile(int puissance){
        System.out.println("addpro : " + puissance);
        projectiles.add(new Projectile(puissance));
        System.out.println("projectiles.size" + projectiles.size());
    }

    @Override
    public void run() {
        try {

            while (true) {

                int nbProjectile = projectiles.size();
                int tailleBuffer = 1 + nbProjectile * 4;

                ByteBuffer b = ByteBuffer.allocate(tailleBuffer * 4);

                b.putInt(nbProjectile);

                for (int nb = 0; nb < nbProjectile; nb++) {
                    projectiles.get(nb).accelerer();

                    Projectile tmp = projectiles.get(nb);

                    b.putDouble(tmp.getX());
                    b.putDouble(tmp.getY());
                }

                byte[] aEnvoyer;
                aEnvoyer = b.array();

                DatagramPacket paquet = new DatagramPacket(aEnvoyer, aEnvoyer.length, adresse, 4445);

                if(projectiles.size()!=0)
                    envoyeur.send(paquet);

                sleep(30);
            }

        } catch (IOException | InterruptedException ioex) {
            ioex.printStackTrace();
        }
    }
}