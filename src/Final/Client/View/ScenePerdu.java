package Final.Client.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScenePerdu {
    private Scene perdu;
    private Text taPerdu;
    private Button continuer;
    private Group rootPerdu;

    public ScenePerdu(Stage stage,Scene control){
        rootPerdu =  new Group();
        taPerdu = new Text("Perdant");
        continuer = new Button("Continuer");

        setAction(continuer,control,stage);

        perdu = new Scene(rootPerdu, Color.BLACK);
    }

    private void setAction(Button boutton,Scene control,Stage stage){
        boutton.setOnAction(event -> stage.setScene(control));
    }
}
