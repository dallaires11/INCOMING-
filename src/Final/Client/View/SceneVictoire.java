package Final.Client.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneVictoire {
    private Scene gagner;
    private Text taGagner;
    private Button continuer;
    private Group rootGagner;

    public SceneVictoire(Stage stage/*, Scene control*/){
        rootGagner =  new Group();
        taGagner = new Text("Perdant");
        continuer = new Button("Continuer");

        setAction(stage);

        gagner = new Scene(rootGagner, Color.WHITE);
    }

    private void setAction(Stage stage){
        continuer.setOnAction(event -> stage.setScene(SceneMenu.getSceneMenu()));
    }
}