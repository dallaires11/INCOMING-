package Final.Client.Controller;

import Final.Client.Model.Catapulte;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;


public class Emetteur {

    private MulticastSocket multicastSocket;

    InetAddress adresse;
    int pLancer;

    byte[] bytes = new byte[128];
    ByteBuffer bufferSend;

    public Emetteur() {
        pLancer = 50;
        bufferSend = ByteBuffer.wrap(bytes);

        try {
            adresse = InetAddress.getByName("224.0.6.0");
            multicastSocket = new MulticastSocket(9002);
            multicastSocket.setInterface(InetAddress.getLocalHost());
            multicastSocket.joinGroup(adresse);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerLancer() {
        System.out.println(pLancer);
        pLancer+=3;
    }

    public void sendLancer(Catapulte catapulte) {
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("224.0.6.0"), 9002);

            bufferSend.putInt(1); //0 = mouvement, 1 = lancer
            bufferSend.putDouble(catapulte.getTranslateX());
            bufferSend.putDouble(catapulte.getTranslateY());
            bufferSend.putInt(pLancer);  //puissance
            bufferSend.putDouble(catapulte.getAngleDeTir());  // angle tir
            bufferSend.putInt(1);  //type
            multicastSocket.send(datagramPacket);

            pLancer = 50;
            bufferSend.clear();
        } catch (IOException u) {
            System.out.println(u);
        }
    }

    public void mouvement(int joueur, int direction) {
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, adresse, 9002);
            bufferSend.putInt(0);
            bufferSend.putInt(joueur);
            bufferSend.putInt(direction);

            multicastSocket.send(datagramPacket);

            bufferSend.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pret(){
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bytes , bytes.length, adresse, 9002);
            bufferSend.putInt(9);
            multicastSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}