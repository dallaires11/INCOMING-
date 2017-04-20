package Final.Client.Model;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Projectile extends Group {
    private double taille;
    private double masse;
    private double x;
    private double y;

    private Group lignes;
    private Line ligneX,ligneY,ligneCombinee;

    private VBox labels;

    public Projectile(double masse, double taille) {
        Circle projectile;
        lignes = new Group();
        labels = new VBox();

        ligneCombinee = new Line();
        ligneX = new Line();
        ligneY = new Line();

        ligneCombinee.setStroke(Color.PURPLE);
        ligneX.setStroke(Color.RED);
        ligneY.setStroke(Color.BLUE);


        this.taille = taille;
        this.masse = masse;

        projectile = new Circle(taille, Color.BLACK);
        this.getChildren().add(projectile);

        lignes.getChildren().addAll(ligneCombinee, ligneX, ligneY);
        this.getChildren().addAll(lignes, labels);

        this.getChildren().get(2).setTranslateX(taille + 5);
    }

    public void setPosition(double x, double y, float vitX, float vitY) {
        this.x = x;
        this.y = y;

        this.setTranslateX(x);
        this.setTranslateY(y);
        setLignes(vitX, vitY);
    }

    private void setLignes(double vX, double vY) {
        this.ligneCombinee.endYProperty().set(vY * 10);
        this.ligneCombinee.endXProperty().set(vX * 10);
        this.ligneX.endXProperty().set(vX * 10);
        this.ligneY.endYProperty().set(vY * 10);
    }


    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public String toString() {
        return "X: " + this.getTranslateX() + " | Y: " + this.getTranslateY() + " | Taille : " + taille;
    }
}
