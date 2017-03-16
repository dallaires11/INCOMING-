package Final.Client.View;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Passeur;
import Final.Client.Controller.Recepteur;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class SceneConnect {
    Emetteur emetteur;
    Group root;
    Scene scene;
    Passeur passeur;
    Socket socket;

    String adresse;

    TextField textField;

    public SceneConnect(Emetteur emetteur, Stage primaryStage, Socket socket ,Scene menu){
        root = new Group();
        this.emetteur = emetteur;
        this.socket = socket;
        scene = new Scene(root,200,200);
        Button boutonConnect = new Button("Connect");
        textField = new TextField("Adresse");

        HBox hbox = new HBox(40, textField, boutonConnect);

        setAction(boutonConnect,primaryStage,menu);

        root.getChildren().add(hbox);
    }

    public Scene getScene(){
        return scene;
    }

    private void setAction(Button bouton,Stage stage,Scene menu){
        bouton.setOnAction(event -> {
            adresse = textField.getText();
            try {
                socket = new Socket(InetAddress.getByName(adresse),9000);
            } catch (IOException e) {
                e.printStackTrace();
            }

            stage.setScene(menu);

        });
    }

}
