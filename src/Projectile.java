class Projectile{
    int angle,masse;
    float x,y,force,vX,vY;

    Projectile(int puissance){
        x=45;
        angle=45;
        y=450;
        masse=30;
        force=puissance;
        double radian=Math.toRadians(angle);
        vX =(float) (force/5 * Math.cos(radian));
        vY =(float) (force/5 * Math.sin(radian));
    }

    private void bouger() {
        x += vX/2;
        y -= vY/2;

        if(y>=450)
            y=450;
    }

    void accelerer() {

        vY -= 0.147/*masse*/;

        bouger();
    }

    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
}