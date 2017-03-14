package Final.Client.View;


import Final.Serveur.Model.Projectile;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class ProjectileView extends Group{
    int masse;
    int type;
    double x;
    double y;
    double vX;
    double vY;

    Line ligneX;
    Line ligneY;
    Line ligneCombinee;

    public ProjectileView(){
        Circle projectile = new Circle(10);

    }

    public ProjectileView(int masse, int type){
        Circle projectile = new Circle();
        ligneX = new Line();
        ligneY = new Line();
        ligneCombinee = new Line();

        switch (type) {
            case 0: projectile = new Circle(masse, Color.GREY);
                break;
            case 1: projectile = new Circle(masse, Color.RED);
                break;
            case 2: projectile = new Circle(masse, Color.AQUA);
        }
        this.getChildren().add(projectile);
    }

    public void setPosition(double x, double y){
        double xBefore;
        double yBefore;

        xBefore = this.getTranslateX();
        yBefore = this.getTranslateY();

        this.setTranslateX(x);
        this.setTranslateY(y);

        this.setLabel((x - xBefore), (y - yBefore));
    }

    public void setLabel(double vX, double vY){
        this.ligneX.endXProperty().set(vX*5);
        this.ligneY.endYProperty().set(vY*5);

        this.ligneCombinee.endYProperty().set((Math.sqrt( Math.pow(vX,2) + Math.pow(vY,2))));
        this.ligneCombinee.setRotate(Math.tan(vY/vX));
    }
}
