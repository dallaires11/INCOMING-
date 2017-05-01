package Final.Client.View;

import Final.Client.Controller.Stoppeur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class ScenePerdu implements Stoppeur{
    private Scene perdu;
    private Text taPerdu;
    private Group rootPerdu;
    private MediaPlayer musiqueDefaite;
    private Stage stage;

    public ScenePerdu(Stage stage) {
        rootPerdu = new Group();
        taPerdu = new Text("MORT");
        musiqueDefaite = new MediaPlayer(new Media(new File("src/Son/Defaite.mp3").toURI().toString()));
        this.stage = stage;

        VBox vbox = new VBox();
        vbox.getChildren().addAll(taPerdu);

        setText();
        setPosition(vbox);

        rootPerdu.getChildren().add(vbox);

        perdu = new Scene(rootPerdu, Color.BLACK);
    }

    private void setText() {
        taPerdu.setScaleX(15);
        taPerdu.setScaleY(15);
        taPerdu.setFill(Color.RED);
    }

    private void setPosition(VBox vBox) {
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(400,980, 0, 825));
    }

    Scene getScene() {
        debutAnimation();
        return perdu;
    }

    private void debutAnimation(){
        musiqueDefaite.play();
    }

    public void stop(){
        musiqueDefaite.stop();
        stage.setScene(SceneJeu.getScene());
    }
}