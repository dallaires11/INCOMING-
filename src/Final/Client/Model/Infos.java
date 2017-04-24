package Final.Client.Model;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by Vincent on 2017-04-20.
 */
public class Infos extends VBox {

    Label posX;
    Label posY;
    Label vitX;
    Label vitY;
    Label masse;

    public Infos (double masseProjectile){
        posX = new javafx.scene.control.Label();
        posY = new javafx.scene.control.Label();
        vitX = new javafx.scene.control.Label();
        vitY = new javafx.scene.control.Label();
        masse = new javafx.scene.control.Label();

        this.masse.setText("Masse : "+masseProjectile);

        this.getChildren().addAll(posX,posY,vitX,vitY,masse);
    }

    public void setLabels(double x,double y, float vitX, float vitY){
        posX.setText("Position X: " + x);
        posY.setText("Position Y: " + y);
        this.vitX.setText("Vitesse  X: "+vitX);
        this.vitY.setText("Vitesse  Y: "+vitY);
    }
}
