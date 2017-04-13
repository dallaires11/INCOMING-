package Final.Client.Model;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
    private Label posX,posY,vitX,vitY,masselab;

    private HBox hBox;
    private VBox labels;

    public Projectile(double masse, double taille) {
        Circle projectile;
        lignes = new Group();
        labels = new VBox();
        hBox = new HBox();

        ligneCombinee = new Line();
        ligneX = new Line();
        ligneY = new Line();

        ligneCombinee.setStroke(Color.PURPLE);
        ligneX.setStroke(Color.RED);
        ligneY.setStroke(Color.BLUE);

        posX = new Label();
        posY = new Label();
        vitX = new Label();
        vitY = new Label();
        masselab = new Label();

        labels.getChildren().addAll(posX,posY,vitX,vitY,masselab);

        this.taille = taille;
        this.masse = masse;

        projectile = new Circle(taille, Color.BLACK);
        this.getChildren().add(projectile);

        lignes.getChildren().addAll(ligneCombinee, ligneX, ligneY);
        hBox.getChildren().addAll(lignes,labels);
        this.getChildren().add(hBox);
    }

    public void setPosition(double x, double y, float vitX, float vitY) {
        this.x = x;
        this.y = y;
        double xBefore;
        double yBefore;

        xBefore = this.getTranslateX();
        yBefore = this.getTranslateY();

        this.setTranslateX(x);
        this.setTranslateY(y);
        setLignes((x - xBefore), (y - yBefore));
        setLabels(x, y, vitX, vitY, masse);
    }

    private void setLignes(double vX, double vY) {
        this.ligneCombinee.endYProperty().set(vY * 10);
        this.ligneCombinee.endXProperty().set(vX * 10);
        this.ligneX.endXProperty().set(vX * 10);
        this.ligneY.endYProperty().set(vY * 10);
    }

    private void setLabels(double x,double y, float vitX, float vitY, double masse){
        posX.setText("Position X: "+x);
        posY.setText("Position Y: ");
        this.vitX.setText("Vitesse  X: "+vitX);
        this.vitY.setText("Vitesse  Y: "+vitY);
        masselab.setText("Masse : "+masse);
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
