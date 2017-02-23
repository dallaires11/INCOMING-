package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.DatagramPacket;
import java.util.ArrayList;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Group proj = new Group();
        ArrayList<Circle> projectiles = new ArrayList<>(10);
        ScenePrototype scene = new ScenePrototype(root, 1200, 600);

        Button bouton = new Button("connect");

        root.getChildren().add(bouton);

        bouton.setTranslateY(40);
        bouton.setTranslateX(15);

        Controller c = new Controller (projectiles, proj);

        bouton.setOnAction(event -> {
            root.getChildren().clear();
            root.getChildren().add(proj);

            c.run(scene.getAdresse());
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


    public static void main(String[] args) {
        launch(args);
    }
}
