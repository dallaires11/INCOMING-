import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;
import java.io.*;
import java.net.DatagramSocket;
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
    BufferedReader in;
    PrintWriter out;
    DataOutputStream out2;
    boolean connect = false;
    Physique moteur=new Physique();

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
    Thread t1;
    Timeline mouvment;
    Physique physique;


    Communi(ServerSocket s){
        serverSocket = s;
        mouvment = new Timeline(new KeyFrame(javafx.util.Duration.millis(15),event ->{
            physique.effectuerMouvement();
        }));

    }




    public void run() {
        while(true){
            mouvment.play();
            System.out.println("Un zéro veut se connecter  ");

            t1 = new Thread(new Message(physique));
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

    }
}

class Message extends Thread {
    private DatagramSocket slave;
    PrintWriter out;
    DataOutputStream out1;
    DataInputStream in;
    Scanner sc;
    int x=0;
    private Physique physique;

    Message(Physique physique) {
        this.physique=physique;
    }


    public void run() {
        try {
            //sc=new Scanner(System.in);
            //out=new PrintWriter(socket.getOutputStream());
            //out1=new DataOutputStream(socket.getOutputStream());
            //in=new DataInputStream(socket.getInputStream());//Callse differente
            int message;
            int recu;

        while (true){


            slave.send(physique.info());

            /*if(in.readBoolean())
                physique.addProjectile();*/


            //message=sc.nextInt();
            //out.println(" Serveur:"+message);
            //out1.writeInt(x);
            //out.flush();
            //out1.flush();
            //x++;
            //in.readInt();
            /*if(recu>0) {
                System.out.println("Recu");
                System.out.println(recu);
            }*/
            this.sleep(10);
        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}