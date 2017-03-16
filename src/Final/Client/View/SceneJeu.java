package Final.Client.View;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Passeur;

import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SceneJeu implements Passeur {
    int positionClient;
    Scene sceneFull;
    Scene sceneLocal;
    Group root;
    Group groupeProjectiles;
    ParallelCamera camera;
    ArrayList<ProjectileView> projectiles;
    Emetteur emetteur;


    public SceneJeu(int positionClientX, int positionClientY){
        this.positionClient = positionClient;
        groupeProjectiles = new Group();
        root.getChildren().add(groupeProjectiles);

        sceneLocal = this.createScene(positionClientX, positionClientY);


    }

    public void passe(int positionProjectile, double x, double y){
        if (positionProjectile >= projectiles.size()){
            ProjectileView temp = new ProjectileView();
            projectiles.add(temp);
            groupeProjectiles.getChildren().add(temp);
        }

        projectiles.get(positionProjectile).setPosition(x, y);
    }

    private Scene createScene(int positionClientX, int positionClientY){
        Scene temp = new Scene(root, 5760, 2160);

        Rectangle sol = new Rectangle(0, 1960, 5760, 200);
        Rectangle ciel = new Rectangle(0, 0, 5760, 1960);
        sol.setFill(Color.GREEN);
        ciel.setFill(Color.AZURE);

        root.getChildren().addAll(sol, ciel);

        temp.getCamera().resize(1920, 1080);

        temp.getCamera().setTranslateX(1080 * positionClientX);
        temp.getCamera().setTranslateY(1920 * positionClientY);

        if ( (positionClientX == 0 || positionClientX == 2 ) && positionClientY != 1){
            sceneFull.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.SPACE){
                    emetteur.chargerLancer();
                }
            });

            sceneFull.setOnKeyReleased(e -> {
                if (e.getCode() == KeyCode.SPACE){
                    emetteur.sendLancer();
                }
            });

            sceneFull.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.LEFT){
                    emetteur.mouvement(-1);
                }
            });

            sceneFull.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.RIGHT){
                    emetteur.mouvement(1);
                }
            });
        }

        return temp;
    }


}
