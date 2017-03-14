package Protoype.Client;

import javafx.scene.Group;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class ControllerPr {
    private MulticastSocket client;
    private Socket clientN;
    private String adresse;
    private int x = 0;

    private byte[] byteSend = new byte[1];
    private ByteBuffer bufferSend = ByteBuffer.wrap(byteSend);
    private DatagramPacket dataSend;
    private InetAddress adresseINET;

    ReceptionPr reception;



    ControllerPr(Group root) {
    }

    void connect(String adresse) {
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

    void lancer() {
        x++;
    }


    void sendLancer() {
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

    MulticastSocket getSocket(){
        return this.client;
    }
 }