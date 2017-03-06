package Serveur;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

class Physique extends Thread{
    private ArrayList<Projectile> projectiles=new ArrayList<>();
    private ArrayList<Socket> lesCeuzeQuiRegardeL;
    private ArrayList<DataOutputStream> pourParlerAuxClients;
    private ByteBuffer b;
    private int nbProjectile,taille, nbVoyeur;
    private MulticastSocket essai;
    DatagramPacket packet;
    private InetAddress adresse;

    Physique(){
        taille=0;
        nbVoyeur = 0;
        lesCeuzeQuiRegardeL = new ArrayList<>();
        pourParlerAuxClients = new ArrayList<>();
        try {
            adresse=InetAddress.getByName("224.0.6.0");
            essai = new MulticastSocket(4445);
            essai.joinGroup(InetAddress.getByName("224.0.6.0"));
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void AjouterUnVoyeur(Socket _v){
        lesCeuzeQuiRegardeL.add(_v);
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

                nbVoyeur = lesCeuzeQuiRegardeL.size();

                if (nbVoyeur > 0) {

                    nbProjectile = projectiles.size();
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
                        essai.send(paquet);

                }
                sleep(30);
            }
        } catch (IOException | InterruptedException ioex) {
            ioex.printStackTrace();
        }
    }
}