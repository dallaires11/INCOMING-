package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.DatagramPacket;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Circle cercle = new Circle(10);
        root.getChildren().add(cercle);
        ArrayList<Circle> projectiles = new ArrayList<>(10);
        Scene scene = new Scene(root, 1800, 1000);

        projectiles.add(cercle);
        Label x = new Label("X : ");
        Label y = new Label("Y : ");

        primaryStage.setTitle("Scene");
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println("testing 321");

        Thread t = new Thread(new Controller(projectiles, root));
        t.start();

        System.out.println("testing 123");

    }


    public static void main(String[] args) {
        launch(args);
    }
}
