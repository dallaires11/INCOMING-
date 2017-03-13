package Final.Serveur.Controller;

import Final.Serveur.Model.Projectile;
import Final.Serveur.Model.Tableaux;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Physique extends Thread{
    Tableaux tableaux;
    Emission emission;

    public Physique(Emission emission){
        tableaux = new Tableaux();
        this.emission = emission;
    }

    public void run() {
        try {

            while (true) {

                int nbProjectile = tableaux.getProjectiles().size();
                int tailleBuffer = 1 + nbProjectile * 4;

                ByteBuffer b = ByteBuffer.allocate(tailleBuffer * 4);

                b.putInt(nbProjectile);

                for (int nb = 0; nb < nbProjectile; nb++) {
                    tableaux.getProjectiles().get(nb).accelerer();

                    Projectile tmp = tableaux.getProjectiles().get(nb);

                    b.putDouble(tmp.getX());
                    b.putDouble(tmp.getY());
                }

                byte[] aEnvoyer;
                aEnvoyer = b.array();

                if(tableaux.getProjectiles().size()!=0)
                    emission.envoyer(aEnvoyer,aEnvoyer.length);

                sleep(30);
            }

        } catch (IOException | InterruptedException ioex) {
            ioex.printStackTrace();
        }
    }

    public void addProjectile(){
        System.out.println("Pow");
    }
}