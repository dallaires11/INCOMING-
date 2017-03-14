package Final.Client.View;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Passeur;
import Final.Client.Controller.Reception;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class SceneConnect {
    Emetteur emetteur;
    Group root;
    Scene scene;
    Passeur passeur;

    String adresse;

    TextField textField;

    public SceneConnect(Group root, Emetteur emetteur, Reception reception, Stage primaryStage){
        this.root = root;
        this.emetteur = emetteur;

        scene = new Scene(root);
        Button boutonConnect = new Button("Connect");
        textField = new TextField("Adresse");
        HBox hbox = new HBox(40, textField, boutonConnect);

        boutonConnect.setOnAction(event -> {
            adresse = textField.getText();
            reception.connect(adresse);
            this.emetteur.setSocket(reception.getSocket());
            this.emetteur.setMulticastSocket(reception.getMulticastSocket());

            root.getChildren().clear();

        });

        root.getChildren().add(hbox);
    }

    public Scene getScene(){
        return scene;
    }

    public void clearGroup(){
        root.getChildren().clear();
    }

}
