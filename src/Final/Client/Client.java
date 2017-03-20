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
    private SceneControl sceneControl;
    private SceneJeu sceneJeu;
    private ScenePerdu scenePerdu;
    private SceneVictoire sceneVictoire;
    private Socket socket;


    public void start(Stage primaryStage){
        emetteur = new Emetteur();
        recepteur = new Recepteur();
        socket=null;

        scenePerdu =  new ScenePerdu(primaryStage);
        sceneVictoire = new SceneVictoire(primaryStage);
        //sceneJeu = new SceneJeu();
        sceneControl =  new SceneControl();
        sceneConnect = new SceneConnect(primaryStage,socket,sceneJeu);

        primaryStage.setScene(sceneConnect.getScene());
        primaryStage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}