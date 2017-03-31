package Final.Client.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class ScenePerdu {
    private Scene perdu;
    private Text taPerdu;
    private Button continuer;
    private Group rootPerdu;
    private MediaPlayer musiqueDefaite;

    public ScenePerdu(Stage stage) {
        rootPerdu = new Group();
        taPerdu = new Text("MORT");
        continuer = new Button("Continuer");
        musiqueDefaite = new MediaPlayer(new Media(new File("src/Son/Defaite.mp3").toURI().toString()));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(taPerdu, continuer);

        setText();
        setPosition(vbox);
        setAction(stage);

        rootPerdu.getChildren().add(vbox);

        perdu = new Scene(rootPerdu, Color.BLACK);
    }

    private void setAction(Stage stage) {
        continuer.setOnAction(event -> {
            musiqueDefaite.stop();
            stage.setScene(SceneMenu.getSceneMenu());
        });
    }

    private void setText() {
        taPerdu.setScaleX(15);
        taPerdu.setScaleY(15);
        taPerdu.setFill(Color.RED);
    }

    private void setPosition(VBox vBox) {
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(150);
        vBox.setPadding(new Insets(400,980, 0, 825));
    }

    public Scene getScene() {
        debutAnimation();
        return perdu;
    }

    public void debutAnimation(){
        musiqueDefaite.play();
    }
}
