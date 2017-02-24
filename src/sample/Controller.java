package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller extends Thread{
    MulticastSocket clientReceive;
    DatagramSocket clientSend;
    Socket clientN;
    String adresse;

    ArrayList<Circle> projectiles;
    Group cercles;
    int x = 0;

    byte[] byteReceive = new byte[1024];
    ByteBuffer buffer = ByteBuffer.wrap(byteReceive);
    DatagramPacket dataReceive = new DatagramPacket(byteReceive, byteReceive.length);

    byte[] byteSend = new byte[1];
    ByteBuffer bufferSend = ByteBuffer.wrap(byteSend);
    DatagramPacket dataSend;
    InetAddress adresseINET;



    public Controller(ArrayList<Circle> projectiles, Group cercles) {
        System.out.println("What's up");
        this.projectiles = projectiles;
        this.cercles = cercles;
    }

    public void run(String adresse) {
        try {
            adresseINET = Inet4Address.getByName("localhost");
            clientN = new Socket(adresseINET, 9012);

            clientReceive = new MulticastSocket(4444);
            clientSend = new MulticastSocket(4445);

            clientReceive.joinGroup(InetAddress.getByName("224.0.6.0"));

            System.out.println("connected");

            sleep(15);

            /*clientReceive.receive(dataReceive);

            int nombreDeProjectiles = buffer.getInt();
            System.out.println(nombreDeProjectiles);

            if (nombreDeProjectiles != 0) {

                for (int i = 0; i < nombreDeProjectiles; i++) {
                    if (i > projectiles.size()) {
                        projectiles.add(new Projectile());
                        cercles.getChildren().add(projectiles.get(i));
                    }
                    projectiles.get(i).setTranslateX(buffer.getDouble());
                    projectiles.get(i).setTranslateY(buffer.getDouble());
                }
                buffer.clear();
            }*/

        } catch (UnknownHostException c) {
            System.out.println("Unknown Host Exception");

        } catch (IOException i) {
            System.out.println("IOException");
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void lancer() {
        x++;
    }


    public void sendLancer() {
            try {
                bufferSend = ByteBuffer.allocate(64);
                bufferSend.putInt(x);
                System.out.println(x);
                byteSend = bufferSend.array();

                dataSend = new DatagramPacket(byteSend, byteSend.length, InetAddress.getByName("224.0.6.0"), 4445);
                clientSend.send(dataSend);

                x = 0;
                bufferSend.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
 }


