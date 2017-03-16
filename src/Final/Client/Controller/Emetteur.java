package Final.Client.Controller;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;


public class Emetteur {

    MulticastSocket multicastSocket;
    int positionJoueur;

    int pLancer;

    byte[] bytes = new byte[64];
    ByteBuffer bufferSend;

    public Emetteur(){
        pLancer = 0;
        this.positionJoueur = positionJoueur;
        bufferSend = ByteBuffer.wrap(bytes);
    }

    public void chargerLancer(){
        pLancer++;
    }
    public void sendLancer(){
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("224.0.6.0"), 9002);

            System.out.println("Force lancer : " + pLancer);
            bufferSend.putInt(1); //0 = mouvement, 1 = lancer
            bufferSend.putInt(positionJoueur); //joueur
            bufferSend.putInt(pLancer);  //puissance
            bufferSend.putInt(45);  // angle tir
            bufferSend.putInt(1);  //type
            multicastSocket.send(datagramPacket);

            pLancer = 0;
        } catch (IOException u){
            System.out.println(u);
        }
    }

    public void mouvement(int direction){
        bufferSend.putInt(0);
        bufferSend.putInt(positionJoueur);
        bufferSend.putInt(direction);
    }

    public void setMulticastSocket(MulticastSocket m){
        this.multicastSocket = m;
    }

    public void setPositionJoueur(int positionJoueur){
        this.positionJoueur = positionJoueur;
    }
}
