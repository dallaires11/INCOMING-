package Final.Client.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class SceneConnect {
    private Group root,boutons;
    private Scene scene;
    private Socket socket;
    private String adresse;
    private TextField textField;
    private int joueurX, joueurY, ecran;
    private Text infoChoix;
    private SceneJeu sceneJeu;
    private MediaPlayer intromusicL,introMusicC;
    private ImageView fond;
    private Rectangle effetSpecial;

    public SceneConnect(Stage primaryStage, Socket socket, SceneJeu sceneJeu) {
        root = new Group();
        boutons = new Group();
        this.socket = socket;
        this.sceneJeu=sceneJeu;
        effetSpecial = new Rectangle(2000,2000, Color.TAN);
        scene = new Scene(root);

        fond = new ImageView(new Image("src/Image/sunset.png"));

        introMusicC = new MediaPlayer(new Media(new File("src/Son/Introcourt.mp3").toURI().toString()));
        intromusicL = new MediaPlayer(new Media(new File("src/Son/Introlong.mp3").toURI().toString()));

        joueurX = -1;
        joueurY = -1;
        ecran = -1;

        infoChoix = new Text("Quel écran souhaitez-vous être?");

        Button boutonConnect = new Button("Connect");
        Button boutonJoueur = new Button("Joueur");
        Button boutonCiel = new Button("Ciel");
        Button boutonObs = new Button("Observateur");

        textField = new TextField();
        textField.setPromptText("Adresse");

        HBox hBox1 = new HBox(boutonJoueur, boutonCiel, boutonObs);
        HBox hBox2 = new HBox(textField, boutonConnect);
        VBox vBox = new VBox(infoChoix, hBox1, hBox2);

        setAction(boutonJoueur, boutonCiel, boutonObs, boutonConnect, primaryStage);

        boutons.getChildren().add(vBox);
        root.getChildren().addAll(fond,effetSpecial,boutons);
    }

    public Scene getScene() {
        return scene;
    }

    private void setAction(Button boutonChoix1, Button boutonChoix2, Button boutonChoix3,
                           Button boutonConnect, Stage stage) {
        boutonConnect.setOnAction(event -> {
            adresse = textField.getText();
            System.out.println(adresse);
            if (adresse.compareTo("") == 0)
                adresse = "localhost";

            System.out.println("Connection à " + adresse);

            if (ecran == 0 || ecran == 1 || ecran == 10) {
                try {

                    System.out.println("test");
                    socket = new Socket(InetAddress.getByName(adresse), 9000);
                    System.out.println("test2");
                    socket.getOutputStream().write(ecran);
                    socket.getOutputStream().flush();
                    System.out.println("ecran = " + ecran);
                    System.out.println("test3");
                    joueurX = socket.getInputStream().read();
                    joueurY = ecran;
                    System.out.println("test4");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("J" + joueurX + " " + joueurY);
                sceneJeu.create(joueurX,joueurY);
                stage.setScene(SceneJeu.getScene());
                stage.setFullScreen(true);
            }

        });
        boutonChoix1.setOnAction(event -> {
            ecran = 1;
        });
        boutonChoix2.setOnAction(event -> {
            ecran = 0;
        });
        boutonChoix3.setOnAction(event -> {
            ecran = 10;
        });

        scene.setOnKeyPressed(event ->{
            if(event.getCode() == KeyCode.SPACE){

            }
        });
    }
}