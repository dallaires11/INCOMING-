package Final.Serveur.Controller;

import Final.Serveur.Model.Catapulte;
import Final.Serveur.Model.Projectile;
import Final.Serveur.Model.Tableaux;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Physique extends Thread implements EmmissionFinDeJeu{
    private Tableaux tableaux;
    private Emission emission;
    private GestionnnaireThread gestionnnaireThread;
    private boolean playing;

    public Physique(Emission emission,GestionnnaireThread gestion){
        gestionnnaireThread = gestion;
        tableaux = new Tableaux();
        this.emission = emission;
        playing=true;
    }

    public void run() {
        try {

            while (true) {

                if(playing) {

                    byte[] aEnvoyer = new byte[2024];

                    int nbProjectile = tableaux.getProjectiles().size();
                    int nbCatapulte = tableaux.getCatapultes().size();

                    ByteBuffer b = ByteBuffer.wrap(aEnvoyer);

                    b.putInt(nbCatapulte);

                    for (int nbc = 0; nbc < nbCatapulte; nbc++) {
                        tableaux.getCatapultes().get(nbc).bouger();

                        Catapulte tmp = tableaux.getCatapultes().get(nbc);

                        b.putInt(tmp.getX());
                        b.putInt(tmp.getY());
                    }


                    b.putInt(nbProjectile);

                    for (int nbp = 0;playing && nbp < nbProjectile; nbp++) {
                        tableaux.getProjectiles().get(nbp).accelerer();

                        Projectile tmp = tableaux.getProjectiles().get(nbp);
                        if (!tmp.getCollision())
                            playing=collision(tmp);


                        if(playing) {
                            b.putDouble(tmp.getX());
                            b.putDouble(tmp.getY());
                            b.putFloat(tmp.getVitesseX());
                            b.putFloat(tmp.getVitesseY());
                            b.putDouble(tmp.getMasse());
                            b.putDouble(tmp.getTaille());
                        }
                    }

                    if(playing) {
                        emission.envoyer(aEnvoyer, aEnvoyer.length);
                    }

                    b.clear();

                }
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
            byte[] aEnvoyer = new byte[1024];
            ByteBuffer b = ByteBuffer.wrap(aEnvoyer);
            b.putInt(6);
            b.putInt(joueur);
        try {
            emission.envoyer(aEnvoyer,aEnvoyer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableaux.restart();
        playing=false;
        //gestionnnaireThread.finJeu();

    }

    private boolean collision(Projectile projectile) {
        boolean notfatal=true;
        Catapulte zero = tableaux.getCatapultes().get(0);
        Catapulte un = tableaux.getCatapultes().get(1);
        if (zero.getX() < projectile.getX() && (zero.getX() + 10) >= projectile.getX() &&projectile.getY()>=1960){
            notfatal=zero.touche(20);
            projectile.setCollision();
            System.out.println("Collision");
        }

        else if (un.getX() < projectile.getX() && (un.getX() + 10) >= projectile.getX() &&projectile.getY()>=1960){
            notfatal=un.touche(20);
            projectile.setCollision();
            System.out.println("Collision");
        }

        return notfatal;
    }

    public void recommencer(){
        playing=true;
    }

    public void setInterface(){
        tableaux.setInterface(this);
    }

}