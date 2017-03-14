package Final.Client.Controller;

import javafx.application.Platform;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Reception extends Thread{
    Passeur passeur;
    int positionClient;

    String adresse;
    Socket socket;
    MulticastSocket multicastSocket;

    byte[] bytes;
    ByteBuffer buffer;

    public Reception(){

    }

    public void connect(String adresse){

        try {
            socket = new Socket(InetAddress.getByName(adresse),9000);
            multicastSocket = new MulticastSocket();
            multicastSocket.joinGroup(InetAddress.getByName("224.0.6.0"));

            bytes = new byte[128];
            buffer = ByteBuffer.wrap(bytes);

            positionClient = buffer.getInt(socket.getInputStream().read());

        } catch (IOException e){
            System.out.println(e);
        }
    }

    public void run(){
        while (true) {
            try {
                multicastSocket.receive(new DatagramPacket(bytes, bytes.length));

                int nombreDeProjectiles = buffer.getInt();

                if (nombreDeProjectiles != 0) {

                    for (int z = 0; z >= nombreDeProjectiles; z++) {
                        final int position = z;
                        final double x = buffer.getDouble();
                        final double y = buffer.getDouble();

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

    public Socket getSocket(){
        return this.socket;
    }

    public MulticastSocket getMulticastSocket(){
        return this.multicastSocket;
    }

    public int getPositionClient(){
        return this.positionClient;
    }
}
