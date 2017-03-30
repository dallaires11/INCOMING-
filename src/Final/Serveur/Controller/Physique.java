package Final.Serveur.Controller;

import Final.Serveur.Model.Catapulte;
import Final.Serveur.Model.Projectile;
import Final.Serveur.Model.Tableaux;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Physique extends Thread{
    private Tableaux tableaux;
    private Emission emission;

    public Physique(Emission emission){
        tableaux = new Tableaux();
        this.emission = emission;
    }

    public void run() {
        try {

            while (true) {

                byte[] aEnvoyer = new byte[1024];

                int nbProjectile = tableaux.getProjectiles().size();
                int nbCatapulte = tableaux.getCatapultes().size();

                ByteBuffer b = ByteBuffer.wrap(aEnvoyer);

                b.putInt(nbCatapulte);

                for(int nbc = 0;nbc < nbCatapulte; nbc++){
                    tableaux.getCatapultes().get(nbc).bouger();

                    Catapulte tmp = tableaux.getCatapultes().get(nbc);

                    b.putInt(tmp.getX());
                    b.putInt(tmp.getY());
                }


                b.putInt(nbProjectile);

                for (int nbp = 0; nbp < nbProjectile; nbp++) {
                    tableaux.getProjectiles().get(nbp).accelerer();

                    Projectile tmp = tableaux.getProjectiles().get(nbp);

                    b.putDouble(tmp.getX());
                    b.putDouble(tmp.getY());
                    b.putInt(tmp.getMasse());
                    b.putInt(tmp.getType());
                }

                emission.envoyer(aEnvoyer,aEnvoyer.length);

                b.clear();

                sleep(15);
            }

        } catch (IOException | InterruptedException ioex) {
            ioex.printStackTrace();
        }
    }

    void addProjectile (double x, double y, int puissanceTir, double angle, int type){
        tableaux.addProjectile(x, y, puissanceTir, type, angle);
    }

    void mouvementCatapulte(int joueur, int mouvement){
        tableaux.getCatapultes().get(joueur).setMouvement(mouvement);
    }

    public void addCatapulte(int joueur){
        tableaux.addCatapulte(joueur);
    }
}