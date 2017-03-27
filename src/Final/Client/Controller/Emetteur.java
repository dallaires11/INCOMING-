package Final.Client.Controller;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;


public class Emetteur implements Passeur{

    MulticastSocket multicastSocket;
    int positionClientX;
    int positionClientY;

    int pLancer;

    byte[] bytes = new byte[64];
    ByteBuffer bufferSend;

    public Emetteur(){
        pLancer = 0;
        bufferSend = ByteBuffer.wrap(bytes);

        try {
            multicastSocket = new MulticastSocket(9002);
            multicastSocket.joinGroup(InetAddress.getByName("224.0.6.0"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerLancer(){
        System.out.println(pLancer);
        pLancer++;
    }
    public void sendLancer(double angle){
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("224.0.6.0"), 9002);

            System.out.println("Force lancer : " + pLancer);
            bufferSend.putInt(1); //0 = mouvement, 1 = lancer
            bufferSend.putInt(positionClientX); //joueur
            bufferSend.putInt(pLancer);  //puissance
            bufferSend.putDouble(angle);  // angle tir
            bufferSend.putInt(1);  //type
            multicastSocket.send(datagramPacket);

            pLancer = 0;
        } catch (IOException u){
            System.out.println(u);
        }
    }

    public void mouvement(int direction){
        bufferSend.putInt(0);
        bufferSend.putInt(positionClientX);
        bufferSend.putInt(direction);
    }

    public void setMulticastSocket(MulticastSocket m){
        this.multicastSocket = m;
    }

    public void setPositionClient(int positionClientX, int positionClientY){
        this.positionClientX = positionClientX;
        this.positionClientY = positionClientY;
    }

    public void passe(int position, double x, double y){
    }

    public void mouvement(int i, int u, int y){}
}
