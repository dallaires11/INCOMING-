package Final.Client.Model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Projectile extends Group {
    private int masse;
    private int type;
    private double x;
    private double y;

    private Line ligneCombinee;


    public Projectile(int masse, int type) {
        Circle projectile;

        ligneCombinee = new Line();

        this.masse = masse;
        this.type = type;

        switch (type) {
            case 0:
                projectile = new Circle(masse, Color.GREEN);
                this.getChildren().add(projectile);
                break;

            case 1:
                projectile = new Circle(masse, Color.BLACK);
                this.getChildren().add(projectile);
                break;

            case 2:
                projectile = new Circle(masse, Color.RED);
                this.getChildren().add(projectile);
                break;
        }

        this.getChildren().addAll(ligneCombinee);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
        double xBefore;
        double yBefore;

        xBefore = this.getTranslateX();
        yBefore = this.getTranslateY();

        this.setTranslateX(x);
        this.setTranslateY(y);

        this.setLabel((x - xBefore), (y - yBefore));
    }

    public void setLabel(double vX, double vY) {
        this.ligneCombinee.endYProperty().set(vY * 10);
        this.ligneCombinee.endXProperty().set(vX * 10);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public String toString() {
        return "X: " + this.getTranslateX() + " | Y: " + this.getTranslateY() + " | type: " + this.type;
    }
}
