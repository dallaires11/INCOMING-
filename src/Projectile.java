public class Projectile{
    int angle,masse;
    float x,y,force,vX,vY;
    //Circle forme;
    //HBox ensemble;
    //VBox label;
    //Label posX,posY,grav;

    public Projectile(int puissance){
        x=45;
        angle=45;
        y=450;
        masse=30;
        force=puissance;
        double radian=Math.toRadians(angle);
        vX =(float) (force/5 * Math.cos(radian));
        vY =(float) (force/5 * Math.sin(radian));
        //forme=new Circle(x,y,10, Color.AQUAMARINE);
        //setLabel();
        //ensemble=new HBox(forme,label);
        //this.getChildren().add(ensemble);

    }

    public void setLabel(){
        //posX=new Label ("X: "+x);
        //posY=new Label ("Y: "+y);
        //grav=new Label ("9,8 m/s");
        //label=new VBox();
        //label.getChildren().addAll(posX,posY,grav);
    }

    private void bouger() {
        x += vX/2;
        y -= vY/2;

        if(y>=450)
            y=450;
        //posX.setText("X: "+x);
        //posY.setText("Y: "+y);
        //System.out.println(x+"holla"+y);

    }

    public void accelerer() {

        vY -= 0.147/*masse*/;

        bouger();
    }



    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
}