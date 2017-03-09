package Final.Serveur.Model;

public class Projectile {
    private int masse,type;
    private float x,y,vitesseX,vitesseY;

    Projectile(int puissance,int type,int joueur,int angle){
        this.type=type;
        setMasse(type);
        setVitesseInitial(angle,puissance);
        setPositionInitial(joueur);
    }

    private void setMasse(int type){
        switch (type){
            default:
                masse=20;
        }
    }

    private void setVitesseInitial(int angle,int forceLancer){
        double radian=Math.toRadians(angle);
        vitesseX =(float) (forceLancer/5 * Math.cos(radian));
        vitesseY =(float) (forceLancer/5 * Math.sin(radian));

    }

    private void setPositionInitial(int joueur){

    }

    private void bouger(){
        x += vitesseX/2;
        y -= vitesseY/2;

        if(y>=450)
            y=450;
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
}