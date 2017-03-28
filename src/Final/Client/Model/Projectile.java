package Final.Client.Model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Projectile{
    int masse;
    int type;
    double x;
    double y;
    double vX;
    double vY;

    Group view;

    Line ligneX;
    Line ligneY;
    Line ligneCombinee;


    public Projectile(int masse, int type){
        view  = new Group();
        Circle projectile = new Circle(masse);
        ligneX = new Line();
        ligneY = new Line();
        ligneCombinee = new Line();

        this.masse = masse;
        this.type = type;

        switch (type) {
            case 0: projectile = new Circle(masse, Color.GREY);
                break;
            case 1: projectile = new Circle(masse, Color.RED);
                break;
            case 2: projectile = new Circle(masse, Color.AQUA);
                break;
        }

        view.getChildren().addAll(projectile, ligneCombinee, ligneX, ligneY);
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
        double xBefore;
        double yBefore;

        xBefore = view.getLayoutX();
        yBefore = view.getLayoutY();

        view.setLayoutX(x);
        view.setLayoutY(y);

        this.setLabel((x - xBefore), (y - yBefore));
    }

    public void setLabel(double vX, double vY){
        this.ligneX.endXProperty().set(vX*5);
        this.ligneY.endYProperty().set(vY*5);

        this.ligneCombinee.endYProperty().set((Math.sqrt( Math.pow(vX,2) + Math.pow(vY,2))));
        this.ligneCombinee.setRotate(Math.tan(vY/vX));
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public Group getView(){
        return this.view;
    }
}
