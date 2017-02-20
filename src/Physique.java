/**
 * Created by Chroon on 2017-02-15.
 */
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
    private int nbProjectile,taille;
    private DatagramSocket essai;
    DatagramPacket packet;
    private InetAddress adresse;

    Physique(){
        taille=0;
        lesCeuzeQuiRegardeL = new ArrayList<>();
        pourParlerAuxClients = new ArrayList<>();
        try {
            adresse=InetAddress.getByName("224.0.5.0");
            essai=new DatagramSocket(4444);
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    void AjouterUnVoyeur(Socket _v){
        taille += 800;

        try{
            DataOutputStream dos = new DataOutputStream(_v.getOutputStream());
            ByteBuffer b = ByteBuffer.allocate(4);

            b.putInt(taille);

            pourParlerAuxClients.add(dos);
            dos.flush();

            dos.write(b.array());
            dos.flush();
        }
        catch(IOException ignored){

        }

        lesCeuzeQuiRegardeL.add(_v);
    }

    void addProjectile(int puissance){
        projectiles.add(projectiles.size(),new Projectile(puissance));
        nbProjectile++;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int nbVoyeur = lesCeuzeQuiRegardeL.size();

                if (nbVoyeur > 0) {
                    nbProjectile = projectiles.size();

                    int tailleBuffer = 1 + nbProjectile * 4;

                    ByteBuffer b = ByteBuffer.allocate(tailleBuffer * 4);

                    b.putInt(nbProjectile);

                    byte[] aEnvoyer;

                    for (int nb = 0; nb < nbProjectile; nb++) {

                        projectiles.get(nb).accelerer();

                        Projectile tmp = projectiles.get(nb);

                        b.putDouble(tmp.getX());
                        b.putDouble(tmp.getY());

                    }

                    aEnvoyer = b.array();

                    DatagramPacket paquet = new DatagramPacket(aEnvoyer, aEnvoyer.length, adresse, 4444);

                    essai.send(paquet);

                }
                sleep(15);
            }
        } catch (IOException | InterruptedException ioex) {
            ioex.printStackTrace();
        }
    }
}