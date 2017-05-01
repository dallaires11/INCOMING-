package Final.Client.Model;

import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Catapulte extends Group {

    private int joueur;
    private int HP;
    private int positionX;
    private  int positionY;
    private double angleRadians, angleDeTir;
    private Line visee;
    private VBox vBox;

    public Catapulte(int joueur) {
        this.joueur = joueur;
        HP = 1000;
        //hpBar = new Group();
        visee = new Line();

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
            positionY = 1945;
            angleDeTir = 315;
            angleRadians = Math.toRadians(angleDeTir);


            Rectangle rectangle = new Rectangle(positionX, positionY, 50, 50);
            rectangle.setFill(Color.BLUE);

            vBox.getChildren().addAll(rectangle);
            this.getChildren().addAll(vBox, visee);
            this.visee.endXProperty().set(Math.cos(angleRadians) * 30);
            this.visee.endYProperty().set(Math.sin(angleRadians) * 30);

            setPosition(positionX, positionY);

        } else if (joueur == 2) {
            positionX = 5560;
            positionY = 1945;
            angleDeTir = 225;
            angleRadians = Math.toRadians(angleDeTir);

            Rectangle rectangle = new Rectangle(positionX, positionY, 50, 50);
            rectangle.setFill(Color.RED);

            vBox.getChildren().addAll(rectangle);
            this.getChildren().addAll(vBox, visee);
            this.visee.endXProperty().set(Math.cos(angleRadians) * 60);
            this.visee.endYProperty().set(Math.sin(angleRadians) * 60);

            setPosition(positionX, positionY);
        }
    }

    private void setPosition(int x, int y){
        positionX = x;
        positionY = y;

        this.setTranslateX(positionX);
        this.setTranslateY(positionY);
    }

    public void rotation(int direction) {
        if(joueur == 1) {
            angleDeTir -= (direction * 5);//Negatif = anti-horaire
        } else if (joueur == 2){
            angleDeTir += (direction * 5);
        }

        if(angleDeTir > 360) {
            angleDeTir = 0;
        } else if (angleDeTir < 0){
            angleDeTir = 360;
        }

        System.out.println(angleDeTir);

        angleRadians = Math.toRadians(angleDeTir);
        this.visee.endXProperty().set(Math.cos(angleRadians) * 30);
        this.visee.endYProperty().set(Math.sin(angleRadians) * 30);
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