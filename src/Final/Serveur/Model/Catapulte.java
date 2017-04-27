package Final.Serveur.Model;

import Final.Serveur.Controller.EmmissionFinDeJeu;

public class Catapulte {
    private int x,y,mouvement,joueur,pv;
    private EmmissionFinDeJeu emmissionFinDeJeu;

    Catapulte(int joueur){
        pv=100;
        this.joueur=joueur;
        setPositionInitial(joueur);
        mouvement=0;
    }

    private void setPositionInitial (int joueur){
        if (joueur==0){
            x=100;
            y=1945;
        }
        else if (joueur==1){
            x=5560;
            y=1945;
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

    public void bouger(){
        if((x+mouvement)>(joueur * 1000)&&(x+mouvement)<1000+(joueur*1000))
            x=x+mouvement;
    }

    public boolean touche(int degat){
        pv-=degat;
        if (pv<=0) {
            emmissionFinDeJeu.finJeu(joueur);
            return  false;
        }
        return  true;
    }

    void restart(){
        pv=100;
        setPositionInitial(joueur);
    }

    public void setInterface(EmmissionFinDeJeu emmissionFinDeJeu){
        this.emmissionFinDeJeu=emmissionFinDeJeu;
    }
}