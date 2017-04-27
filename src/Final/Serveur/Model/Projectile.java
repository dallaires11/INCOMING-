package Final.Serveur.Model;

public class Projectile {

    private int resistanceMouvTerreDur;
    private double taille;
    private float x,y,vitesseX,vitesseY;
    private double aeroX,aeroY,masse;
    private boolean collision,sol;

    Projectile(double x, double y, int puissance, double taille, double angle){
        resistanceMouvTerreDur=0;
        this.taille=taille;
        collision=false;
        sol=false;
        setMasse(taille);
        setVitesseInitial(angle,puissance);
        setPositionInitial(x, y);
    }

    private void setMasse(double taille){
        masse=2600*4*Math.PI*Math.pow((taille / 24) ,2);
    }

    private void setVitesseInitial(double angle,int forceLancer){
        double radian=Math.toRadians(angle);
        vitesseX =(float) (forceLancer/5 * Math.cos(radian));
        System.out.println("angle: " + radian + " VitesseX : " + vitesseX);
        vitesseY = -((float) (forceLancer/5 * Math.sin(radian)));

    }

    private void setPositionInitial(double x, double y){
        this.x = (float) x;
        this.y = (float) y;
    }

    private void bouger(){
        if (vitesseX>5&&sol)
            vitesseX=5;
        else if (vitesseX<-5&&sol)
            vitesseX=-5;
        x += vitesseX;
        if(!sol) {
            y -= vitesseY;

            if (y >= 1960) {
                y = 1960;
                sol = true;
            }
        }

        resistanceMouvTerreDur=8;
        if (vitesseX<0)
            resistanceMouvTerreDur =-8;

    }

    public void accelerer(){
        double vitXtemp = (double) vitesseX;

        aeroX = 0.5*1.225*Math.pow(vitXtemp,2)*4*Math.PI*Math.pow((taille/24),2)*0.47;

        //vitesseX -= ((aeroX)+resistanceMouvTerreDur)/60;
        if (!sol)
            vitesseY -= (9.8/60);
        else{
            vitesseY=0;
        }

        bouger();
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public float getVitesseX(){
        return vitesseX;
    }

    public float getVitesseY(){
        return vitesseY;
    }

    public double getMasse(){
        return masse;
    }

    public double getTaille(){
        return taille;
    }

    public  boolean getCollision(){
        return collision;
    }

    public void setCollision(){
        collision=true;
    }
}