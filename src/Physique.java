import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by Chroon on 2017-02-15.
 */
public class Physique {
    private ArrayList<Projectile> projectiles=new ArrayList<>();
    private ByteBuffer b;
    private int nbProjectile;
    DatagramSocket essai;
    DatagramPacket packet;
    InetAddress adresse;

    public Physique (){
        System.out.print("test");
        nbProjectile=0;
        try {
            adresse=InetAddress.getLocalHost();
            essai=new DatagramSocket(81);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void addProjectile(int puissance){
        projectiles.add(projectiles.size(),new Projectile(puissance));
        nbProjectile++;
    }

    public void effectuerMouvement(){
        projectiles.forEach(Projectile::accelerer);
        int tailleBuffer = 1+nbProjectile*4;
        b= ByteBuffer.allocate(tailleBuffer*4);
        b.putInt(nbProjectile);
        byte[] aEnvoyer;

        for(int w=0;w<nbProjectile;w++){
            Projectile temp = projectiles.get(w);

            b.putFloat(temp.y);
            b.putFloat(temp.x);
        }
        b.putFloat(2f);

        aEnvoyer=b.array();

        packet = new DatagramPacket(aEnvoyer,aEnvoyer.length,adresse,81);

        try {
            essai.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public DatagramPacket info(){
        return packet;
    }

}