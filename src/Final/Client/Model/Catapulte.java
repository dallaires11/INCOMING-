package Final.Client.Model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Vincent on 2017-03-23.
 */
public class Catapulte {

    int HP;
    int positionX;
    int positionY;
    double angleDeTir;

    Group view;

    public Catapulte(int joueur){
        HP = 1000;

        if (joueur == 1){
            positionX = 200;
            positionY = 1960;
            angleDeTir = 45;

            Rectangle rectangle = new Rectangle(positionX, positionY, 20, 20);
            rectangle.setFill(Color.BLUE);
            view.getChildren().add(rectangle);

        } else if (joueur == 2){
            positionX = 5560;
            positionY = 1960;
            angleDeTir = 325;

            Rectangle rectangle = new Rectangle(positionX, positionY, 20, 20);
            rectangle.setFill(Color.RED);
            view.getChildren().add(rectangle);
        }



    }

    public void setPosition(int x, int y){
        positionX = x;
        positionY = y;

        view.setTranslateX(positionX);
        view.setTranslateY(positionY);
    }

    public void rotation(int direction){
        angleDeTir=- (direction * 5);  //Negatif = anti-horaire
                                       //Positif = horaire
    }
}
