package Final.Client;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Reception;
import Final.Client.View.SceneConnect;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Client extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        int positionClient;
        Emetteur emetteur = new Emetteur();
        Reception reception = new Reception();
        SceneConnect sceneConnect;
        Group root = new Group();

        sceneConnect = new SceneConnect(root, emetteur, reception, primaryStage);

        primaryStage.setScene(sceneConnect.getScene());
        positionClient = reception.getPositionClient();
        System.out.println("position client  = " + positionClient);



    }
}
