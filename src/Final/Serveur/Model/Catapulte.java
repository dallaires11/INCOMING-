package Final.Serveur.Model;

public class Catapulte {
    // TODO: 2017-03-13  travailler sur un model de catapulte
    private int x,y,mouvement;

    Catapulte(int joueur){
        int x=0;
        int y=0;
        mouvement=0;
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

    public void bouger(){
        x=x+mouvement;
    }
}
