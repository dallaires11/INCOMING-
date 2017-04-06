package Final.Serveur.Model;

public class Projectile {
    private int masse,type;
    private float x,y,vitesseX,vitesseY;
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
        vitesseY -= 0.147/*masse*/;

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