package Final.Client.Controller;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

/**
 * Created by Chroon on 2017-03-09.
 */
public class Emetteur {

    Socket socket;
    MulticastSocket multicastSocket;

    int lancer;

    byte[] bytes = new byte[64];
    ByteBuffer bufferSend;
    DatagramPacket packet;

    public Emetteur(Socket socket, MulticastSocket multicastSocket){
        this.socket = socket;
        this.multicastSocket = multicastSocket;
        lancer = 0;

        bufferSend.wrap(bytes);
    }

    public void chargerLancer(){
        lancer++;
    }
    public void sendLancer(){
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("224.0.6.0"), 9002);

            System.out.println("Force lancer : " + lancer);
            bufferSend.putInt(lancer);
            multicastSocket.send(datagramPacket);

            lancer = 0;
        } catch (UnknownHostException u){
            System.out.println(u);
        } catch (IOException io){
            System.out.println(io);
        }
    }
}
