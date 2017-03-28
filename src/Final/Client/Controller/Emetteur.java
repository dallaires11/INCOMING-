package Final.Client.Controller;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;


public class Emetteur {

    MulticastSocket multicastSocket;
    int positionClientX;
    int positionClientY;

    InetAddress adresse;
    int pLancer;

    byte[] bytes = new byte[128];
    ByteBuffer bufferSend;

    public Emetteur() {
        pLancer = 0;
        bufferSend = ByteBuffer.wrap(bytes);

        try {
            adresse = InetAddress.getByName("224.0.6.0");
            multicastSocket = new MulticastSocket(9002);
            multicastSocket.joinGroup(adresse);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerLancer() {
        System.out.println(pLancer);
        pLancer++;
    }

    public void sendLancer(double angle) {
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
            bufferSend.clear();
        } catch (IOException u) {
            System.out.println(u);
        }
    }

    public void mouvement(int direction) {
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, adresse, 9002);
            bufferSend.putInt(0);
            bufferSend.putInt(positionClientX);
            bufferSend.putInt(direction);

            multicastSocket.send(datagramPacket);

            bufferSend.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMulticastSocket(MulticastSocket m) {
        this.multicastSocket = m;
    }

    public void setPositionClient(int positionClientX, int positionClientY) {
        this.positionClientX = positionClientX;
        this.positionClientY = positionClientY;
    }
}