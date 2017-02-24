/**
 * Created by Chroon on 2017-02-06.
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class Serveur {

    public static void main(String[] args) {
        ServerSocket srv = null;
        Physique physique;
        Thread reception;


        try {
            srv = new ServerSocket(9012);

            System.out.println(InetAddress.getLocalHost());

            physique = new Physique();
            physique.start();

            //physique.addProjectile(60);

            while (true) {
                Socket s = srv.accept();

                System.out.println("Hola");
                physique.AjouterUnVoyeur(s);
                reception = new Thread(new Reception(physique));
                reception.start();

                System.out.println(s.getInetAddress());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Reception implements Runnable {
    MulticastSocket multiSocket;
    Physique physique;
    DatagramPacket reçu;
    byte[] aDecrypter;
    ByteBuffer decrypteur;

    Reception(Physique physique) throws IOException {
        this.physique = physique;
        System.out.println("Test");
        multiSocket = new MulticastSocket(4445);
        multiSocket.joinGroup(InetAddress.getByName("224.0.6.0"));
        //aDecrypter = new byte[1];
        //decrypteur = ByteBuffer.wrap(aDecrypter);
        //reçu = new DatagramPacket(aDecrypter, aDecrypter.length);
    }

    @Override
    public void run() {
        while(true) {
            try {
                byte[] buff = new byte[64];
                DatagramPacket dp = new DatagramPacket(buff, buff.length);
                multiSocket.receive(dp);
                ByteBuffer pourLire = ByteBuffer.wrap(buff);
                //physique.start();

                for (int i = 0; i < 1; ++i) {
                    int puissance = pourLire.getInt();
                    System.out.println(puissance);
                    physique.addProjectile(puissance);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
    /*static ServerSocket serveur;
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
        mouvment = new Timeline(new KeyFrame(javafx.util.Duration.millis(15), event ->{
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
            slave.close();

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
            }
            this.sleep(10);
        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}*/