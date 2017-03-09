package Protoype.Client;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainPr extends Application implements RooterPr {
    Group root=new Group();
    ArrayList<Circle> projectiles=new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        ScenePrototypePr scene = new ScenePrototypePr(root, 1200, 600);

        Button bouton = new Button("connect");

        root.getChildren().add(bouton);

        bouton.setTranslateY(40);
        bouton.setTranslateX(15);

        ReceptionPr reception = new ReceptionPr();
        reception.setInterface(this);

        ControllerPr c = new ControllerPr(root);


        bouton.setOnAction(event -> {
            c.connect(scene.getAdresse());
            reception.setSocket(c.getSocket());

            root.getChildren().clear();

            reception.start();
        });




        primaryStage.setTitle("Scene");
        primaryStage.setScene(scene.getScene());
        primaryStage.show();

        scene.getScene().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.SPACE) {
                c.lancer();
            }

        });
        scene.getScene().setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.SPACE) {
                c.sendLancer();
            }
        });
    }

    public void received(double x,double y,int pos){
        if (pos>=projectiles.size()){
            ProjectilePr tmp=new ProjectilePr();
            projectiles.add(tmp);
            root.getChildren().add(tmp);
        }

        projectiles.get(pos).setTranslateX(x);
        projectiles.get(pos).setTranslateY(y);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
