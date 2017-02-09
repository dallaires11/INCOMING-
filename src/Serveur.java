import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
        BufferedReader in;
        PrintWriter out;
        DataOutputStream out2;


        try {
            serveur = new ServerSocket(81);
            System.out.println(InetAddress.getLocalHost());
            slave = serveur.accept();
            System.out.println("Un zéro s'est connecté !");
            //out = new PrintWriter(slave.getOutputStream());
            out2=new DataOutputStream(slave.getOutputStream());
            out2.writeInt(1000000000);
            out2.flush();

            int x;
            float y;
            String s="SAlut";
            boolean g;
            char t='a';

            serveur.close();
            slave.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}