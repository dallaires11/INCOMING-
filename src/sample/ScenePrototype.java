package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Created by Vincent on 2017-02-23.
 */
public class ScenePrototype{

    private Scene scene;
    Label adresse;
    TextField adresseField;

    HBox hBox;

    Boolean connect = false;
    public ScenePrototype(Group root, int x, int y){
        scene = new Scene (root, x, y);

        adresse = new Label("Adresse : ");
        adresseField = new TextField();

        hBox = new HBox(adresse, adresseField);

        hBox.setTranslateX(15);
        hBox.setTranslateY(15);

        root.getChildren().addAll(adresse, adresseField);
    }



    public Scene getScene(){
        return scene;
    }

    public String getAdresse(){
        return adresseField.getText();
    }

    public Boolean getConnect(){
        return connect;
    }

}
