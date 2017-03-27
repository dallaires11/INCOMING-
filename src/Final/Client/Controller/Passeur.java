package Final.Client.Controller;

/**
 * Created by Vincent on 2017-03-20.
 */
public interface Passeur {

    public void passe(int position, double x, double y, int masse, int type);

    public void mouvement(int joueur, int x, int y);

    public void setPositionClient(int positionClientX, int positionClientY);
}
