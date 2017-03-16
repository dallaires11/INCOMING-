package Final.Client.View;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Passeur;
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
    private Socket socket;
    private String adresse;
    private TextField textField;
    private int joueurX,joueurY,nbJoueurX,nbJoueurY;

    public SceneConnect(Stage primaryStage, Socket socket ,Scene menu){
        root = new Group();
        this.socket = socket;
        scene = new Scene(root);

        joueurX=0;
        joueurY=0;
        nbJoueurX=0;
        nbJoueurY=0;

        Text infoChoix = new Text("Quel écran souhaitez-vous être?");

        Button boutonConnect = new Button("Connect");
        Button boutonJoeur = new Button("Joueur");
        Button boutonCiel = new Button("Ciel");
        Button boutonObs =  new Button("Observateur");

        textField = new TextField("Adresse");

        HBox hBox1 = new HBox(boutonJoeur,boutonCiel,boutonObs);
        HBox hBox2 = new HBox(textField, boutonConnect);
        VBox vBox = new VBox(infoChoix,hBox1,hBox2);

        setAction(boutonJoeur,boutonCiel,boutonObs,boutonConnect,primaryStage,menu);

        root.getChildren().add(vBox);
    }

    public Scene getScene(){
        return scene;
    }

    private void setAction(Button boutonChoix1,Button boutonChoix2,Button boutonChoix3,
                           Button boutonConnect,Stage stage,Scene menu){
        boutonConnect.setOnAction(event -> {
            adresse = textField.getText();
            if(joueurY==1)
                nbJoueurY++;
            else if(joueurY==0)
                nbJoueurX++;
            /*try {
                socket = new Socket(InetAddress.getByName(adresse),9000);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            stage.setFullScreen(true);
            //stage.setScene(menu);

        });
        boutonChoix1.setOnAction(event->{
            joueurX=nbJoueurX;
            joueurY=0;
        });
        boutonChoix1.setOnAction(event->{
            joueurX=nbJoueurY;
            joueurY=1;
        });
        boutonChoix1.setOnAction(event->{
            joueurX=-1;
            joueurX=-1;
        });
    }
}