package Final.Client.View;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Passeur;

import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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


    public SceneJeu(int positionClient){
        this.positionClient = positionClient;
        groupeProjectiles = new Group();
        root.getChildren().add(groupeProjectiles);

        camera.setTranslateX(1080 * positionClient);
        camera.setTranslateY(1920 * positionClient);

        sceneFull = new Scene(root);
        sceneLocal = sceneFull;
        sceneLocal.setCamera(camera);

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

    public void passe(int positionProjectile, double x, double y){
        if (positionProjectile >= projectiles.size()){
            ProjectileView temp = new ProjectileView();
            projectiles.add(temp);
            groupeProjectiles.getChildren().add(temp);
        }

        projectiles.get(positionProjectile).setPosition(x, y);
    }


}
