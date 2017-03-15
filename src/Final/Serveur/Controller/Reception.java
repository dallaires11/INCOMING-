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
                int puissanceTir=-1;
                int joueur=-1;
                int mouvement=-1;
                int angle=-1;
                int type=-1;
                byte[] buff = new byte[64];
                DatagramPacket dp = new DatagramPacket(buff,buff.length);

                recepteur.receive(dp);
                ByteBuffer dechifreur = ByteBuffer.wrap(buff);

                for (int i = 0; i < 1; ++i) {
                    joueur = dechifreur.getInt();
                    puissanceTir = dechifreur.getInt();
                    angle = dechifreur.getInt();
                    type = dechifreur.getInt();
                    mouvement = dechifreur.getInt();

                    System.out.println(joueur+" "+puissanceTir+" "+mouvement);
                }

                if (mouvement==0&&puissanceTir>-1){
                    physique.addProjectile(joueur,puissanceTir,angle,type);
                }
                else if(mouvement !=0&&puissanceTir<0){
                    physique.mouvementCatapulte(joueur,mouvement);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}