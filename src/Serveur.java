import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.util.Scanner;

/**
 * Created by Chroon on 2017-02-06.
 */
public class Serveur {
    static ServerSocket serveur;
    Socket slave;
    BufferedReader in;
    PrintWriter out;
    DataOutputStream out2;
    boolean connect = false;

    public static void main(String[] args) {
        try {
            serveur = new ServerSocket(81);
            System.out.println(InetAddress.getLocalHost());
            Thread t = new Thread(new Communi(serveur));
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
class Communi implements Runnable{
    ServerSocket serverSocket;
    Socket socket;
    Thread t1;

    Communi(ServerSocket s){
        serverSocket = s;

    }


    public void run() {
        try {
            while(true){

                socket = serverSocket.accept();
                System.out.println("Un zéro veut se connecter  ");

                t1 = new Thread(new Message(socket));
                t1.start();

            }

            /*slave = serveur.accept();
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
            slave.close();*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Message extends Thread {
    Socket socket;
    PrintWriter out;
    DataOutputStream out1;
    DataInputStream in;
    Scanner sc;
    int x=0;

    Timeline temps;

    Message(Socket socket){
        this.socket=socket;
    }


    public void run() {
        try {
            sc=new Scanner(System.in);
            out=new PrintWriter(socket.getOutputStream());
            out1=new DataOutputStream(socket.getOutputStream());
            in=new DataInputStream(socket.getInputStream());//Callse differente
            int message;
            int recu;

        while (true){
            //message=sc.nextInt();
            //out.println(" Serveur:"+message);
            //out1.writeInt(x);
            //out.flush();
            //out1.flush();
            //x++;
            in.readInt();
            /*if(recu>0) {
                System.out.println("Recu");
                System.out.println(recu);
            }*/
            this.sleep(3);
        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}