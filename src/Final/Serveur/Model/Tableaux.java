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
}