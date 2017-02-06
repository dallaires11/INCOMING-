/**
 * Created by Chroon on 2017-01-20.
 */
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Main extends Application{
    int x=50;
    int proj=0;
    boolean running=false;
    ArrayList<Projectile> projectiles=new ArrayList<>();



    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Rectangle catapulte = new Rectangle(60, 40, Color.DARKOLIVEGREEN);
        catapulte.setTranslateX(40);
        catapulte.setTranslateY(450);
        root.getChildren().add(catapulte);
        Scene jeu = new Scene(root, 1200, 900);
        Timeline phystique = new Timeline(new KeyFrame(Duration.millis(15), event -> {
            if (!projectiles.isEmpty()) {
                for (int y = 0; y < projectiles.size(); y++) {
                    projectiles.get(y).accelerer();
                    projectiles.get(y).setTranslateX(projectiles.get(y).getX());
                    projectiles.get(y).setTranslateY(projectiles.get(y).getY());
                }
            }
            //System.out.println("T");
        }));
        phystique.setCycleCount(Animation.INDEFINITE);
        phystique.play();
        jeu.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                x++;
                //System.out.println(x);
            }
        });
        jeu.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                //System.out.println(x);
                /*if(projectiles.size()==1){
                    projectiles.clear();
                    root.getChildren().remove(1);
                }*/
                projectiles.add(new Projectile(x));
                root.getChildren().add(projectiles.get(proj));
                //projectiles.get(proj).setTranslateX(40);
                //projectiles.get(proj).setTranslateY(450);
                proj++;
                x = 50;
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