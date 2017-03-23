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
        setAtction(stage,sceneJeu);
    }

    private void setAtction(Stage stage,SceneJeu sceneJeu){
        commencer.setOnAction(event ->{
            sceneJeu.create();
            stage.setScene(sceneJeu.getScene());
        });
    }



    public static Scene getSceneMenu(){
        return  menu;
    }
}