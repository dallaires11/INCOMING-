package Final.Serveur.Controller;

import Final.Serveur.Model.Catapulte;
import Final.Serveur.Model.Projectile;
import Final.Serveur.Model.Tableaux;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Physique extends Thread implements EmmissionFinDeJeu{
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
                    if(!tmp.getCollision())
                        collision(tmp);

                    b.putDouble(tmp.getX());
                    b.putDouble(tmp.getY());
                    b.putFloat(tmp.getVitesseX());
                    b.putFloat(tmp.getVitesseY());
                    b.putDouble(tmp.getMasse());
                    b.putDouble(tmp.getTaille());
                }

                emission.envoyer(aEnvoyer,aEnvoyer.length);

                b.clear();

                sleep(15);
            }

        } catch (IOException | InterruptedException ioex) {
            ioex.printStackTrace();
        }
    }

    void addProjectile (double x, double y, int puissanceTir, double angle, int taille){
        tableaux.addProjectile(x, y, puissanceTir, taille, angle);
    }

    void mouvementCatapulte(int joueur, int mouvement){
        tableaux.getCatapultes().get(joueur).setMouvement(mouvement);
    }

    public void addCatapulte(int joueur){
        tableaux.addCatapulte(joueur);
    }

    public void finJeu(int joueur){
        try {
            byte[] aEnvoyer = new byte[1024];
            ByteBuffer b = ByteBuffer.wrap(aEnvoyer);
            b.putInt(6);
            b.putInt(joueur);
            emission.envoyer(aEnvoyer,aEnvoyer.length);
            tableaux.restart();
            this.wait();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private void collision(Projectile projectile) {
        Catapulte zero = tableaux.getCatapultes().get(0);
        Catapulte un = tableaux.getCatapultes().get(1);
        if (zero.getX() < projectile.getX() && (zero.getX() + 5) >= projectile.getX() &&projectile.getY()>=1910){
            zero.touche(20);
            projectile.setCollision();
        }

        else if (un.getX() < projectile.getX() && (un.getX() + 5) >= projectile.getX() &&projectile.getY()>=1910){
            un.touche(20);
            projectile.setCollision();
        }
    }

    public void setInterface(){
        tableaux.setInterface(this);
    }

}