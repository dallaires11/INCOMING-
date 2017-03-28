package Final.Client.Controller;

public interface Passeur {

    void passe(int position, double x, double y, int masse, int type);

    void mouvement(int joueur, int x, int y);

}
