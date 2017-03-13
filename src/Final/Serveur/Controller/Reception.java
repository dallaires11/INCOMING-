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
                byte[] buff = new byte[64];
                DatagramPacket dp = new DatagramPacket(buff,buff.length);

                recepteur.receive(dp);
                ByteBuffer dechifreur = ByteBuffer.wrap(buff);

                for (int i = 0; i < 1; ++i) {
                    int puissanceTir = dechifreur.getInt();
                    System.out.println(puissanceTir);
                    physique.addProjectile();
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}