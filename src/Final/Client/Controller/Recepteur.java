package Final.Client.Controller;

import javafx.application.Platform;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Recepteur extends Thread{
    int positionClient;
    Socket socket;
    MulticastSocket multicastSocket;
    Passeur passeur;

    byte[] bytes;
    ByteBuffer buffer;

    public Recepteur(){

    }

    public void run(){
        while (true) {
            try {
                multicastSocket.receive(new DatagramPacket(bytes, bytes.length));

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

            } catch (IOException | InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public int getPositionClient(){
        return this.positionClient;
    }
}
