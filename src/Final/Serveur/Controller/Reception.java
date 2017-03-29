package Final.Serveur.Controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;

public class Reception implements Runnable{
    private MulticastSocket recepteur;
    private Physique physique;

    public Reception(Physique physique){
        this.physique = physique;
        try {
            recepteur = new MulticastSocket(9002);
            recepteur.joinGroup(InetAddress.getByName("224.0.6.0"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

        try {

            while(true){
                int typeRecu;
                int puissanceTir;
                int joueur;
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
                    joueur = dechifreur.getInt();
                    puissanceTir = dechifreur.getInt();
                    angle = dechifreur.getDouble();
                    type = dechifreur.getInt();
                    System.out.println("Tir" + puissanceTir);
                    System.out.println("angle : " + angle);
                    physique.addProjectile(joueur, puissanceTir,angle,type);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}