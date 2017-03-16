package Final.Client;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Recepteur;
import Final.Client.View.*;

import javafx.application.Application;
import javafx.stage.Stage;

import java.net.Socket;

public class Client extends Application {
    Emetteur emetteur;
    Recepteur recepteur;
    SceneConnect sceneConnect;
    SceneMenu sceneMenu;
    SceneControl sceneControl;
    SceneJeu sceneJeu;
    ScenePerdu scenePerdu;
    SceneVictoire sceneVictoire;
    Socket socket;



    public void start(Stage primaryStage){
        emetteur = new Emetteur();
        recepteur = new Recepteur();
        SceneConnect sceneConnect;
        socket=null;

        sceneMenu = new SceneMenu();

        sceneConnect = new SceneConnect(primaryStage,socket,sceneMenu.getScene());

        primaryStage.setScene(sceneConnect.getScene());
        primaryStage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}