package Client;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * Created by utilisateur on 2017-02-20.
 */
public class Sam {

    public static void main(String [] args){
        MulticastSocket multi=null;
        Scanner scan = new Scanner(System.in);
        Socket socket;
        String adresse;
        try{
            System.out.print("Connect to : ");
            adresse = scan.nextLine();
            socket=new Socket(InetAddress.getByName(adresse),81);
            multi=new MulticastSocket(4444);
            multi.joinGroup(InetAddress.getByName("224.0.5.0"));

            byte[] byteReceive = new byte[1024];
            ByteBuffer buffer = ByteBuffer.wrap(byteReceive);
            DatagramPacket dataReceive = new DatagramPacket(byteReceive, byteReceive.length);

            while(true) {
                System.out.println(buffer.getInt());
                System.out.println(buffer.getDouble());
                System.out.println(buffer.getDouble());
            }




        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
