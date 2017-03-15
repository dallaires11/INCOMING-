package Final.Client.Controller;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;


public class Emetteur {

    Socket socket;
    MulticastSocket multicastSocket;

    int pLancer;

    byte[] bytes = new byte[64];
    ByteBuffer bufferSend;

    public Emetteur(){
        pLancer = 0;

        bufferSend = ByteBuffer.wrap(bytes);
    }

    public void chargerLancer(){
        pLancer++;
    }
    public void sendLancer(){
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("224.0.6.0"), 9002);

            System.out.println("Force lancer : " + pLancer);
            bufferSend.putInt(pLancer);
            multicastSocket.send(datagramPacket);

            pLancer = 0;
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
