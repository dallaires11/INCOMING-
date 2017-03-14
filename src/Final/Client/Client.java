package Final.Client;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Reception;
import Final.Client.View.SceneJeu;
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
        SceneJeu sceneJeu;
        Group root = new Group();

        sceneJeu = new SceneJeu(root, emetteur, reception);

        primaryStage.setScene(sceneJeu.getScene());
        positionClient = reception.getPositionClient();
        System.out.println("position client  = " + positionClient);

    }
}
