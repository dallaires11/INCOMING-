/**
 * Created by Chroon on 2017-01-20.
 */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application{
    int x=0;
    ArrayList<Projectile> projectiles=new ArrayList<>();

    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        Rectangle catapulte=new Rectangle(60,40, Color.DARKOLIVEGREEN);
        catapulte.setTranslateX(40);
        catapulte.setTranslateY(450);
        root.getChildren().add(catapulte);
        Scene jeu=new Scene(root, 1200,900);
        jeu.setOnKeyPressed(event ->{
            if(event.getCode()== KeyCode.SPACE){
                x++;
            }
        });
        jeu.setOnKeyReleased(event ->{
            if(event.getCode()==KeyCode.SPACE){
                System.out.println(x);
                projectiles.add(new Projectile(x));
                x=0;

            }
        });
        primaryStage.setTitle("INCOMING!!!");
        primaryStage.setScene(jeu);
        primaryStage.show();
    }




    public static void main(String[] args) {
            launch(args);
        }

}