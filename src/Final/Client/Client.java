package Final.Client;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Recepteur;
import Final.Client.View.*;

import javafx.application.Application;
import javafx.stage.Stage;

import java.net.Socket;

public class Client extends Application {
    private Emetteur emetteur;
    private Recepteur recepteur;
    private SceneConnect sceneConnect;
    private SceneJeu sceneJeu;
    private ScenePerdu scenePerdu;
    private SceneVictoire sceneVictoire;
    private Socket socket;

    public void start(Stage primaryStage) {
        emetteur = new Emetteur();
        recepteur = new Recepteur();
        socket = null;
        recepteur.start();


        scenePerdu = new ScenePerdu(primaryStage);
        sceneVictoire = new SceneVictoire(primaryStage, emetteur);
        sceneJeu = new SceneJeu(primaryStage, emetteur, sceneVictoire, scenePerdu);
        sceneConnect = new SceneConnect(primaryStage, socket, sceneJeu);

        recepteur.setInterface(sceneJeu.getThis());

        primaryStage.setScene(scenePerdu.getScene());

        //primaryStage.setScene(sceneConnect.getScene());
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}