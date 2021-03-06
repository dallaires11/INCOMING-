package Final.Client.View;

import Final.Client.Controller.Emetteur;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class SceneVictoire {
    private Scene gagner;
    private Text taGagner;
    private Button continuer;
    private Group rootGagner;
    private Rectangle fond;
    private FadeTransition ft;
    private MediaPlayer musiqueVictoire,restart;
    private Emetteur emetteur;

    public SceneVictoire(Stage stage, Emetteur emetteur) {
        rootGagner = new Group();
        taGagner = new Text("Victoire");
        continuer = new Button("Continuer");
        fond = new Rectangle(6000,6000,Color.WHITE);
        musiqueVictoire =  new MediaPlayer(new Media(new File("src/Son/Victoire.mp3").toURI().toString()));
        restart = new MediaPlayer(new Media(new File("src/Son/Restart.mp3").toURI().toString()));
        this.emetteur = emetteur;

        ft = new FadeTransition(Duration.seconds(10),rootGagner);
        ft.setFromValue(0);
        ft.setToValue(1);

        WebView view = new WebView();
        view.setMinSize(500, 400);
        view.setPrefSize(500, 400);
        final WebEngine eng = view.getEngine();
        final String dir = System.getProperty("user.dir");
        eng.load("file:"+ dir + "\\src\\Image\\WalkingManSpriteSheet.svg");

        setAction(stage);
        setText();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(taGagner,view,continuer);
        setPosition(vBox);

        rootGagner.getChildren().addAll(fond,vBox);
        gagner = new Scene(rootGagner, Color.BLACK);
    }



    private void setAction(Stage stage) {
        continuer.setOnAction(event -> {
            musiqueVictoire.stop();
            emetteur.pret();
            restart.play();
            stage.setScene(SceneJeu.getScene());
            stage.setFullScreen(true);
        });
    }

    private void setText(){
        taGagner.setScaleX(15);
        taGagner.setScaleY(15);
        taGagner.setFill(Color.GOLD);
    }

    private void setPosition(VBox vbox){
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(135);
        vbox.setPadding(new Insets(100,600,0,600));
    }

    Scene getScene(){
        debutAnimation();
        return gagner;
    }

    private void debutAnimation(){
        ft.playFromStart();
        musiqueVictoire.play();
    }
}