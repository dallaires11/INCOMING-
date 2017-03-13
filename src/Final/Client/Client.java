package Final.Client;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Reception;

import java.net.MulticastSocket;
import java.net.Socket;

/**
 * Created by Chroon on 2017-03-09.
 */
public class Client {

    public static void main(String[] args) {
        int positionClient;
        Emetteur emetteur;
        Reception reception = new Reception();

        Socket socket;
        MulticastSocket multicastSocket;

        positionClient = reception.connect("192.168.1.100");
        System.out.println("position client  = " + positionClient);
        socket = reception.getSocket();
        multicastSocket = reception.getMulticastSocket();

        emetteur = new Emetteur(socket, multicastSocket);
    }
}
