package Final.Client.Controller;

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
        byteReceive = new byte[2024];
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
                    int perdant = buffer.getInt();
                    Platform.runLater(() -> passeur.setToBlack(perdant));
                }

                else if (nombreDeCatapultes==7){
                    Platform.runLater(() -> {
                        passeur.setToGame();
                        System.out.println("Regarde moi");
                        stoppeur.stop();
                    });

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
                            float vitX =  buffer.getFloat();
                            float vitY = buffer.getFloat();
                            double masse = buffer.getDouble();
                            double taille = buffer.getDouble();

                            Platform.runLater(() -> passeur.passe(position, x, y,vitX, vitY,masse, taille));
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

    public void setInterface(Passeur passeur,Stoppeur stoppeur) {
        this.passeur = passeur;
        this.stoppeur = stoppeur;
    }
}