package Final.Client.Model;

import com.sun.javaws.progress.Progress;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Created by Vincent on 2017-03-23.
 */
public class Catapulte extends Group {

    int HP;
    int positionX;
    int positionY;
    double angleDegres, angleDeTir;

    private Line visee;
    Group view;
    //Group hpBar;
    VBox vBox;

    public Catapulte(int joueur) {
        HP = 1000;
        //hpBar = new Group();

        visee = new Line();

        this.visee.endYProperty().set(40);

        Rectangle hpBack = new Rectangle(22, 12);
        hpBack.setFill(Color.GREY);

        Rectangle hpFront = new Rectangle(20, 10);
        hpFront.setFill(Color.CRIMSON);

        /*
        hpBar.getChildren().addAll(hpBack, hpFront);
        hpBar.getChildren().get(1).setTranslateX(1);
        hpBar.getChildren().get(1).setTranslateY(1);
        */

        vBox = new VBox();

        if (joueur == 1) {
            positionX = 100;
            positionY = 1960;
            angleDeTir = 45;


            Rectangle rectangle = new Rectangle(positionX, positionY, 20, 20);
            rectangle.setFill(Color.BLUE);
            this.visee.setRotate(angleDeTir);
            vBox.getChildren().addAll(rectangle);
            this.getChildren().addAll(vBox, visee);

            //setPosition(positionX, positionY);

        } else if (joueur == 2) {
            positionX = 5560;
            positionY = 1960;
            angleDeTir = 315;

            Rectangle rectangle = new Rectangle(positionX, positionY, 20, 20);
            rectangle.setFill(Color.RED);
            this.visee.setRotate(angleDeTir);
            vBox.getChildren().addAll(rectangle);
            this.getChildren().addAll(vBox, visee);

            setPosition(positionX, positionY);
        }
    }

    public void setPosition(int x, int y){
        positionX = x;
        positionY = y;

        this.setTranslateX(positionX);
        this.setTranslateY(positionY);
    }

    public void rotation(int direction) {
        angleDegres = -(direction * 5);//Negatif = anti-horaire
        angleDeTir = Math.toRadians(angleDegres);
        this.visee.setRotate(angleDeTir);
    }

    public Group getView() {
        return this;
    }

    public double getAngleDeTir() {
        return angleDeTir;
    }

    public String toString() {
        return "X: " + this.getTranslateX() + " | Y: " + this.getTranslateY();
    }
}

