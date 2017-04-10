package Final.Serveur.Model;

public class Projectile {
    private int masse,type;
    private float x,y,vitesseX,vitesseY;
    private double aeroX, aeroY;
    private boolean collision;

    Projectile(double x, double y, int puissance,int type, double angle){
        this.type=type;
        collision=false;
        setMasse(type);
        setVitesseInitial(angle,puissance);
        setPositionInitial(x, y);
    }

    private void setMasse(int type){
        switch (type){
            default:
                masse=6;
        }
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
        x += vitesseX/2;
        y -= vitesseY/2;

        if(y>=1960)
            y=1960;
    }

    public void accelerer(){
        double vitXtemp = (double) vitesseX;
        double vitYtemp = (double) vitesseY;
        aeroX = 0.5*1.225*Math.pow(vitXtemp,2)*4*Math.PI*Math.pow(0.25,2)*0.47;
        aeroY = 0.5*1.225*Math.pow(vitYtemp,2)*4*Math.PI*Math.pow(0.25,2)*0.47;
        //Aero = 1/2*Masse volumique air 15 degre C*Vitesse^2*Aire objet (Sphere)*Coeffiscient surfacique
        System.out.println("Aero "+aeroX+"  "+aeroY);

        vitesseX += (aeroX/masse)/60;
        vitesseY -= (9.8*(aeroY/masse))/60;

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

    public int getMasse(){
        return masse;
    }

    public int getType(){
        return type;
    }

    public  boolean getCollision(){
        return collision;
    }

    public void setCollision(){
        collision=true;
    }
}