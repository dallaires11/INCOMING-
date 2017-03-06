package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.DatagramPacket;
import java.util.ArrayList;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Main extends Application implements Rooter{
    Group root=new Group();
    ArrayList<Circle> projectiles=new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        ScenePrototype scene = new ScenePrototype(root, 1200, 600);

        Button bouton = new Button("connect");

        root.getChildren().add(bouton);

        bouton.setTranslateY(40);
        bouton.setTranslateX(15);

        Reception reception = new Reception();
        reception.setInterface(this);

        Controller c = new Controller(root);


        bouton.setOnAction(event -> {
            c.connect(scene.getAdresse());
            reception.setSocket(c.getSocket());

            root.getChildren().clear();

            reception.start();
        });




        primaryStage.setTitle("Scene");
        primaryStage.setScene(scene.getScene());
        primaryStage.show();

        scene.getScene().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.SPACE) {
                c.lancer();
            }

        });
        scene.getScene().setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.SPACE) {
                c.sendLancer();
            }
        });
    }

    public void received(double x,double y,int pos){
        if (pos>=projectiles.size()){
            Projectile tmp=new Projectile();
            projectiles.add(tmp);
            root.getChildren().add(tmp);
        }

        projectiles.get(pos).setTranslateX(x);
        projectiles.get(pos).setTranslateY(y);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
