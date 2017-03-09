package Protoype.Serveur;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;

class Reception implements Runnable {
    private MulticastSocket multiSocket;
    private Physique physique;

    Reception(Physique physique) throws IOException {
        this.physique = physique;
        multiSocket = new MulticastSocket(4444);
        multiSocket.joinGroup(InetAddress.getByName("224.0.6.0"));
    }

    public void run() {
        try {

            while(true) {

                byte[] buff = new byte[64];
                DatagramPacket dp = new DatagramPacket(buff, buff.length);

                multiSocket.receive(dp);
                ByteBuffer pourLire = ByteBuffer.wrap(buff);

                for (int i = 0; i < 1; ++i) {
                    int puissanceTir = pourLire.getInt();
                    System.out.println(puissanceTir);
                    physique.addProjectile(puissanceTir);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}