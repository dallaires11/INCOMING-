package Final.Client.Controller;

import Final.Client.View.SceneJeu;
import javafx.application.Platform;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;

public class Recepteur extends Thread {
    private MulticastSocket multicastSocket;
    private Passeur passeur;
    private Stoppeur stoppeur;
    private byte[] byteReceive;
    private ByteBuffer buffer;
    private DatagramPacket dataReceive;

    public Recepteur() {
        byteReceive = new byte[1024];
        buffer = ByteBuffer.wrap(byteReceive);
        dataReceive = new DatagramPacket(byteReceive, byteReceive.length);
    }

    public void run() {
        try {
            multicastSocket = new MulticastSocket(9001);
            multicastSocket.setInterface(InetAddress.getLocalHost());
            multicastSocket.joinGroup(InetAddress.getByName("224.0.6.0"));

            while (true) {
                multicastSocket.receive(dataReceive);

                int nombreDeCatapultes = buffer.getInt();

                if (nombreDeCatapultes==6){
                    int gagnant = buffer.getInt();
                    passeur.setToBlack(gagnant);
                }

                else if (nombreDeCatapultes==8){
                    passeur.setToGame();
                    stoppeur.stop();
                }

                else {

                    for (int i = 0; i < nombreDeCatapultes; i++) {
                        int position = i;
                        int posCataX = buffer.getInt();
                        int posCataY = buffer.getInt();


                        Platform.runLater(() -> passeur.mouvement(position, posCataX, posCataY));
                    }

                    int nombreDeProjectiles = buffer.getInt();

                    if (nombreDeProjectiles != 0) {

                        for (int z = 0; z < nombreDeProjectiles; z++) {
                            int position = z;
                            double x = buffer.getDouble();
                            double y = buffer.getDouble();
                            int masse = buffer.getInt();
                            int type = buffer.getInt();

                            Platform.runLater(() -> passeur.passe(position, x, y, masse, type));
                        }

                    }

                    buffer.clear();

                    sleep(10);
                }

            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }

    public void setInterface(Passeur passeur) {
        this.passeur = passeur;
    }
}
