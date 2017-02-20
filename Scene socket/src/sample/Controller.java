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
    MulticastSocket client;
    Socket clientN;
    String adresse;
    Scanner scan = new Scanner(System.in);

    int x = 0;

    Group root = new Group();
    Scene scene;

    Boolean serverOuvert;
    Boolean serverTrue = false;

    byte[] byteReceive = new byte[1024];
    ByteBuffer buffer = ByteBuffer.wrap(byteReceive);
    DatagramPacket dataReceive = new DatagramPacket(byteReceive, byteReceive.length);

    byte[] byteSend = new byte[1];
    ByteBuffer bufferSend = ByteBuffer.wrap(byteSend);
    DatagramPacket dataSend = new DatagramPacket(byteSend, byteSend.length);

    ArrayList<Circle> projectiles;

    public Controller(ArrayList<Circle> projectiles, Group root) {
        System.out.println("What's up");
        this.projectiles = projectiles;


        this.root = root;
    }

    public void run() {
        try {

            System.out.print("Connect to : ");
            adresse = scan.nextLine();
            clientN = new Socket(InetAddress.getByName(adresse), 81);
            client = new MulticastSocket(4444);
            client.joinGroup(InetAddress.getByName("224.0.5.0"));

            System.out.println("connected");

            while (clientN.isConnected()) {
                client.receive(dataReceive);

                int nombreDeProjectiles = buffer.getInt();

                for (int i = 0; i < nombreDeProjectiles; i++) {
                    projectiles.get(i).setTranslateX(buffer.getDouble());
                    projectiles.get(i).setTranslateY(buffer.getDouble());
                }

                buffer.clear();

                sleep(15);
            }

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
                bufferSend.putInt(x);
                System.out.println(x);

                client.send(dataSend);

                x = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
 }


