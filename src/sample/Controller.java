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

public class Controller{
    MulticastSocket client;
    Socket clientN;
    String adresse;
    int x = 0;

    byte[] byteSend = new byte[1];
    ByteBuffer bufferSend = ByteBuffer.wrap(byteSend);
    DatagramPacket dataSend;
    InetAddress adresseINET;

    Reception reception;



    public Controller(Group root) {
    }

    public void connect(String adresse) {
        try {
            adresseINET = Inet4Address.getByName("localhost");
            clientN = new Socket(adresseINET, 9012);

            client = new MulticastSocket(4444);


            client.joinGroup(InetAddress.getByName("224.0.6.0"));


            System.out.println("connected");
            }
        catch (UnknownHostException c) {
            System.out.println("Unknown Host Exception");

        } catch (IOException i) {
            System.out.println("IOException");
            System.out.println(i);
        }


    }

    public void lancer() {
        x++;
    }


    public void sendLancer() {
            try {
                bufferSend = ByteBuffer.allocate(64);
                bufferSend.putInt(x);
                byteSend = bufferSend.array();

                dataSend = new DatagramPacket(byteSend, byteSend.length, InetAddress.getByName("224.0.6.0"), 4444);
                client.send(dataSend);

                x = 0;
                bufferSend.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public MulticastSocket getSocket(){
        return this.client;
    }


 }


