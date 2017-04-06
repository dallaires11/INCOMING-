package Final.Client.Controller;

public interface Passeur {

    void passe(int position, double x, double y, float vitX, float vitY, int masse, int type);

    void mouvement(int joueur, int x, int y);

    void setToBlack(int gagnant);

    void setToGame();
}