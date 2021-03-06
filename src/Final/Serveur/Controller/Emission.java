package Final.Serveur.Controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Emission{
    private MulticastSocket envoyeur;
    private InetAddress adresse;

    public Emission() {
        try {
            adresse=InetAddress.getByName("224.0.6.0");
            envoyeur = new MulticastSocket(9001);
            envoyeur.setInterface(InetAddress.getLocalHost());
            envoyeur.joinGroup(InetAddress.getByName("224.0.6.0"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void envoyer(byte[] aEnvoyer, int length) throws IOException {
        DatagramPacket paquet = new DatagramPacket(aEnvoyer, length, adresse, 9001);

        envoyeur.send(paquet);
    }
}