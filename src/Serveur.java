import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class Serveur {

    public static void main(String[] args) {
        ServerSocket srv = null;
        Physique physique;
        Thread reception;

        try {
            srv = new ServerSocket(9012);

            System.out.println(InetAddress.getLocalHost());

            physique = new Physique();
            physique.start();

            reception = new Thread(new Reception(physique));
            reception.start();

            while (true) {
                Socket s = srv.accept();

                System.out.println("Hola");
                physique.AjouterUnVoyeur(s);

                System.out.println(s.getInetAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Reception implements Runnable {
    MulticastSocket multiSocket;
    Physique physique;

    Reception(Physique physique) throws IOException {
        this.physique = physique;
        System.out.println("Test");
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
                    int puissance = pourLire.getInt();
                    System.out.println(puissance);
                    physique.addProjectile(puissance);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}