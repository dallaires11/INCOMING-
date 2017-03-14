package Final.Client.Controller;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;


public class Emetteur {

    Socket socket;
    MulticastSocket multicastSocket;

    int lancer;

    byte[] bytes = new byte[64];
    ByteBuffer bufferSend;

    public Emetteur(){
        lancer = 0;

        bufferSend = ByteBuffer.wrap(bytes);
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
        } catch (IOException u){
            System.out.println(u);
        }
    }

    public void setSocket(Socket s){
        this.socket = s;
    }

    public void setMulticastSocket(MulticastSocket m){
        this.multicastSocket = m;
    }
}
