package Final.Serveur.Model;

import java.util.ArrayList;

public class Tableaux {
    private ArrayList<Projectile> projectiles;
    private ArrayList<Catapulte> catapultes;

    public Tableaux(){
        projectiles = new ArrayList<>();
        catapultes = new ArrayList<>();
    }

    public ArrayList<Projectile> getProjectiles(){
        return projectiles;
    }

    public void addProjectile(int puissance,int type,int joueur,int angle){
        projectiles.add(new Projectile(puissance,type,joueur,angle));
    }

    public void addCatapulte(int joueur){
        catapultes.add(new Catapulte(joueur));
    }

    public ArrayList<Catapulte> getCatapultes(){
        return catapultes;
    }
}