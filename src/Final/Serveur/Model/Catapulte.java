package Final.Serveur.Model;

public class Catapulte {
    private int x,y,mouvement;

    Catapulte(int joueur){
        y=450;
        setPositionInitial(joueur);
        mouvement=0;
    }

    private void setPositionInitial (int joueur){
        if (joueur==0){
            x=1900;
        }
        else if (joueur==1){
            x=100;
        }
    }

    public void setMouvement(int mouvement){
        this.mouvement=mouvement;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void bouger(int joueur){
        if((x+mouvement)>(joueur * 1000)&&(x+mouvement)<1000+(joueur*1000))
            x=x+mouvement;
    }
}
