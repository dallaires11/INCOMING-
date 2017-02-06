import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Chroon on 2017-02-06.
 */
public class Serveur {
    public static void main(String [] args) {
        ServerSocket serveur;
        Socket slave;


        try {
            serveur = new ServerSocket(80);
            System.out.println(InetAddress.getLocalHost());
            slave = serveur.accept();
            System.out.println("Un zéro s'est connecté !");
            serveur.close();
            slave.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}