package Final.Client.View;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class SceneConnect {
    private Group root;
    private Scene scene;
    private SceneJeu sceneJeu;
    private Socket socket;
    private String adresse;
    private TextField textField;
    private int joueurX,joueurY,ecran;
    private Text nomJeu,infoChoix;

    public SceneConnect(Stage primaryStage, Socket socket, SceneJeu sceneJeu){
        root = new Group();
        this.socket = socket;
        scene = new Scene(root);

        joueurX=-1;
        joueurY=-1;
        ecran=-2;

        nomJeu = new Text("INCOMING!!!");
        infoChoix = new Text("Quel écran souhaitez-vous être?");

        Button boutonConnect = new Button("Connect");
        Button boutonJoueur = new Button("Joueur");
        Button boutonCiel = new Button("Ciel");
        Button boutonObs =  new Button("Observateur");

        textField = new TextField("Adresse");

        HBox hBox1 = new HBox(boutonJoueur,boutonCiel,boutonObs);
        HBox hBox2 = new HBox(textField, boutonConnect);
        VBox vBox = new VBox(infoChoix,hBox1,hBox2);

        setAction(boutonJoueur,boutonCiel,boutonObs,boutonConnect,primaryStage, sceneJeu);

        root.getChildren().add(vBox);
    }

    public Scene getScene(){
        return scene;
    }

    private void setAction(Button boutonChoix1,Button boutonChoix2,Button boutonChoix3,
                           Button boutonConnect,Stage stage, SceneJeu  sceneJeu){
        boutonConnect.setOnAction(event -> {
            adresse = textField.getText();
            if(ecran==0||ecran==1||ecran==-1) {
                try {
                    socket = new Socket(InetAddress.getByName(adresse), 9000);
                    socket.getOutputStream().write(ecran);
                    joueurX=socket.getInputStream().read();
                    joueurY=ecran;
                    sceneJeu.setPositionClient(joueurX, joueurY);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                stage.setFullScreen(true);
                System.out.println("J"+joueurX+" "+joueurY);

                sceneJeu.create();
                stage.setScene(sceneJeu.getScene());

            }

        });
        boutonChoix1.setOnAction(event->{
            ecran=0;
        });
        boutonChoix2.setOnAction(event->{
            ecran=1;
        });
        boutonChoix3.setOnAction(event->{
            ecran=-1;
        });
    }
}