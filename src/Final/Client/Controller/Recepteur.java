package Final.Client.Controller;

import javafx.application.Platform;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;

public class Recepteur extends Thread{
    MulticastSocket multicastSocket;
    Passeur passeur;
    byte[] byteReceive;
    ByteBuffer buffer;
    DatagramPacket dataReceive;

    public Recepteur(){
        byteReceive = new byte[1024];
        buffer = ByteBuffer.wrap(byteReceive);
        dataReceive = new DatagramPacket(byteReceive, byteReceive.length);
    }

    public void run(){
        try{
            multicastSocket = new MulticastSocket(9001);
            multicastSocket.joinGroup(InetAddress.getByName("224.0.6.0"));

            while (true) {
                multicastSocket.receive(dataReceive);

                int nombreDeCatapultes = buffer.getInt();

                for(int i = 0;i<nombreDeCatapultes;i++){
                    int posCataX = buffer.getInt();
                    int posCataY = buffer.getInt();

                    passeur.mouvement(i, posCataX, posCataY);
                }

                int nombreDeProjectiles = buffer.getInt();

                if (nombreDeProjectiles != 0) {

                    for (int z = 0; z < nombreDeProjectiles; z++) {
                        int position = z;
                        double x = buffer.getDouble();
                        double y = buffer.getDouble();
                        float vitesseX = buffer.getFloat();
                        float vitesseY = buffer.getFloat();
                        int masse = buffer.getInt();
                        int type = buffer.getInt();

                        Platform.runLater(() -> passeur.passe(position, x, y));
                    }

                    buffer.clear();
                }

                sleep(15);

                }} catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }
}
