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

    public void addProjectile(double x, double y, int puissance,int type,double angle){
        projectiles.add(new Projectile(x, y, puissance,type,angle));
    }

    public void addCatapulte(int joueur){
        catapultes.add(new Catapulte(joueur));
    }

    public ArrayList<Catapulte> getCatapultes(){
        return catapultes;
    }

    public void restart(){
        projectiles.clear();
        for(int x=0;x < projectiles.size();x++){
            catapultes.get(x).restart();
        }
    }
}