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

                int nbProjectile = tableaux.getProjectiles().size();
                int nbCatapulte = tableaux.getCatapultes().size();
                int tailleBuffer = 1 + nbProjectile * 4;

                ByteBuffer b = ByteBuffer.allocate(tailleBuffer * 4);

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
                    b.putFloat(tmp.getVitesseX());
                    b.putFloat(tmp.getVitesseY());
                    b.putInt(tmp.getMasse());
                    b.putInt(tmp.getMasse());
                    b.putInt(tmp.getType());
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

    void addProjectile(int joueur, int puissanceTir, int angle, int type){
        tableaux.addProjectile(joueur,puissanceTir,angle,type);
    }

    void mouvementCatapulte(int joueur, int mouvement){
        tableaux.getCatapultes().get(joueur).setMouvement(mouvement);
    }

    public void addCatapulte(int joueur){
        tableaux.addCatapulte(joueur);
    }
}