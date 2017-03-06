package sample;

import javafx.application.Platform;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Reception extends Thread{
    ArrayList<Circle> projectiles;
    MulticastSocket socket;
    Rooter rooter;

    byte[] byteReceive;
    ByteBuffer buffer;
    DatagramPacket dataReceive;

    public Reception(){
        byteReceive = new byte[1024];
        buffer = ByteBuffer.wrap(byteReceive);
        dataReceive = new DatagramPacket(byteReceive, byteReceive.length);
    }

    public void run() {
        try {
            socket = new MulticastSocket(4445);
            socket.joinGroup(InetAddress.getByName("224.0.6.0"));

            while (true) {
                socket.receive(dataReceive);


                int nombreDeProjectiles = buffer.getInt();
                System.out.println("reception : " + nombreDeProjectiles);

                if (nombreDeProjectiles != 0) {

                    for (int i = 0; i < nombreDeProjectiles; i++) {
                        final double x=buffer.getDouble();
                        final double y=buffer.getDouble();
                        final int u=i;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                rooter.received(x,y,u);
                            }
                        });
                    }
                    buffer.clear();
                }

                sleep(15);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void setInterface(Rooter input){
        rooter=input;
    }

    void setSocket(MulticastSocket socket){
        this.socket = socket;
    }

    public ArrayList<Circle> getCercles(){
        return this.projectiles;
    }

}