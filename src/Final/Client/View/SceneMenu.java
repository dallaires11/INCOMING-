package Final.Client.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SceneMenu {
    static int positionClientX, positionClientY;
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
            sceneJeu.create(positionClientX, positionClientY);
            stage.setScene(sceneJeu.getScene());
        });
    }

    public static void setPositionClient(int x, int y){
        positionClientX = x;
        positionClientY = y;
    }



    public static Scene getSceneMenu(){
        return  menu;
    }
}