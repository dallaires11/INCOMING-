package Final.Client.Controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Reception extends Thread{

    String adresse;
    Socket socket;
    MulticastSocket multicastSocket;

    byte[] bytes;
    ByteBuffer buffer;

    public Reception(){

    }

    public int connect(String adresse){
        int positionClient = -1;
        try {
            socket = new Socket(InetAddress.getByName(adresse),9000);
            multicastSocket = new MulticastSocket();
            multicastSocket.joinGroup(InetAddress.getByName("224.0.6.0"));

            bytes = new byte[64];
            buffer = ByteBuffer.wrap(bytes);

            positionClient = buffer.getInt(socket.getInputStream().read());

        } catch (IOException e){
            System.out.println(e);
        }
        return positionClient;
    }

    public Socket getSocket(){
        return this.socket;
    }

    public MulticastSocket getMulticastSocket(){
        return this.multicastSocket;
    }
}
