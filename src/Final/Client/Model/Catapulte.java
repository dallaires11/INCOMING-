package Final.Client.Model;

import com.sun.javaws.progress.Progress;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;
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

    ProgressBar progressBar;

    Group view;
    VBox vBox;

    public Catapulte(int joueur){
        HP = 1000;
        progressBar = new ProgressBar(1);
        view = new Group();
        vBox = new VBox();

        if (joueur == 1){
            positionX = 200;
            positionY = 1960;
            angleDeTir = 45;


            Rectangle rectangle = new Rectangle(positionX, positionY, 20, 20);
            rectangle.setFill(Color.BLUE);
            vBox.getChildren().addAll(progressBar, rectangle);
            view.getChildren().add(vBox);

            setPosition(positionX, positionY);

        } else if (joueur == 2){
            positionX = 5560;
            positionY = 1960;
            angleDeTir = 315;

            Rectangle rectangle = new Rectangle(positionX, positionY, 20, 20);
            rectangle.setFill(Color.RED);
            vBox.getChildren().addAll(progressBar, rectangle);
            view.getChildren().add(vBox);

            setPosition(positionX, positionY);
        }
    }

    public void setPosition(int x, int y){
        positionX = x;
        positionY = y;

        view.setLayoutX(positionX);
        view.setLayoutY(positionY);
    }

    public void rotation(int direction){
        angleDeTir=- (direction * 5);  //Negatif = anti-horaire
                                       //Positif = horaire
    }

    public Group getView(){
        return view;
    }

    public double getAngleDeTir(){
        return angleDeTir;
    }

    public void receiveDamage(int damage){
        HP -= damage;
        progressBar.setProgress(HP/1000);
    }
}
