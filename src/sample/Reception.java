package sample;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by Vincent on 2017-02-25.
 */
public class Reception extends Thread{

    ArrayList<Circle> projectiles;
    MulticastSocket socket;

    byte[] byteReceive;
    ByteBuffer buffer;
    DatagramPacket dataReceive;

    public Reception(){
        this.projectiles = new ArrayList<Circle>();

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
                        if (i >= projectiles.size()) {
                            System.out.println("add");
                            Projectile tmp = new Projectile();
                            projectiles.add(tmp);
                        }
                        projectiles.get(i).setTranslateX(buffer.getDouble());
                        projectiles.get(i).setTranslateY(buffer.getDouble());
                        System.out.println("nice");
                    }
                    buffer.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSocket(MulticastSocket socket){
        this.socket = socket;
    }

    public ArrayList<Circle> getCercles(){
        return this.projectiles;
    }

}
