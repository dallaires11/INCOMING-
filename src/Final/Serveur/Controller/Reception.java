package Final.Serveur.Controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;

public class Reception implements Runnable{
    private MulticastSocket recepteur;
    private Physique physique;
    private InetAddress  adresse;

    public Reception(Physique physique){
        this.physique = physique;
        try {
            adresse = InetAddress.getByName("224.0.6.0");
            recepteur = new MulticastSocket(9002);
            recepteur.setInterface(InetAddress.getLocalHost());
            recepteur.joinGroup(adresse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

        try {

            while(true){
                int typeRecu;
                int joueur;
                int puissanceTir;
                double x, y;
                int mouvement;
                double angle;
                int type;
                byte[] buff = new byte[64];
                DatagramPacket dp = new DatagramPacket(buff,buff.length);

                recepteur.receive(dp);
                ByteBuffer dechifreur = ByteBuffer.wrap(buff);

                typeRecu = dechifreur.getInt();

                if(typeRecu==0){
                    joueur = dechifreur.getInt();
                    mouvement = dechifreur.getInt();
                    physique.mouvementCatapulte(joueur,mouvement);
                    System.out.println("Mouvement");
                }

                else  if(typeRecu==1){
                    x = dechifreur.getDouble();
                    y = dechifreur.getDouble();
                    puissanceTir = dechifreur.getInt();
                    angle = dechifreur.getDouble();
                    type = dechifreur.getInt();
                    System.out.println("Tir" + puissanceTir);
                    System.out.println("angle : " + angle);
                    physique.addProjectile(x, y, puissanceTir, angle, type);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}