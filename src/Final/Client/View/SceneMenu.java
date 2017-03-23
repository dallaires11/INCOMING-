package Final.Client.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SceneMenu {
    private static Scene menu;
    private Button commencer;
    private Group root;

    public SceneMenu(Stage stage, SceneJeu sceneJeu){
        root = new Group();
        menu  = new Scene(root, 100,100);
        commencer = new Button("Commencer");
        setAction(stage,sceneJeu);

        root.getChildren().add(commencer);
    }

    private void setAction(Stage stage,SceneJeu sceneJeu){
        commencer.setOnAction(event ->{
            sceneJeu.create();
            stage.setScene(sceneJeu.getScene());
        });
    }



    public static Scene getSceneMenu(){
        return  menu;
    }
}