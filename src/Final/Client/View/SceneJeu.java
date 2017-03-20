package Final.Client.View;

import Final.Client.Controller.Emetteur;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SceneJeu {
    int positionClientX, positionClientY;
    Scene sceneFull;
    Scene sceneLocal;
    Group rootSceneJeu;
    Group groupeProjectiles;
    ParallelCamera camera;
    ArrayList<ProjectileView> projectiles;
    Emetteur emetteur;


    public SceneJeu(int positionClientX, int positionClientY){
        this.positionClientX = positionClientX;
        this.positionClientY = positionClientY;
        rootSceneJeu = new Group();
        groupeProjectiles = new Group();
        rootSceneJeu.getChildren().add(groupeProjectiles);

        rootSceneJeu = this.createGroup();

        this.createScene(rootSceneJeu);
    }

    public void passe(int positionProjectile, double x, double y){
        if (positionProjectile >= projectiles.size()){
            ProjectileView temp = new ProjectileView();
            projectiles.add(temp);
            groupeProjectiles.getChildren().add(temp);
        }

        projectiles.get(positionProjectile).setPosition(x, y);
    }

    public Group createGroup(){

        Rectangle sol = new Rectangle(0, 1960, 5760, 200);
        Rectangle ciel = new Rectangle(0, 0, 5760, 1960);
        sol.setFill(Color.GREEN);
        ciel.setFill(Color.AZURE);

        rootSceneJeu.getChildren().addAll(sol, ciel);



        return rootSceneJeu;
    }

    private void createScene(Group root){
        sceneLocal = new Scene(rootSceneJeu, 5760, 2160);
        ParallelCamera camera = new ParallelCamera();

        camera.resize(1920, 1080);

        camera.setTranslateX(1080 * positionClientX);
        camera.setTranslateY(1920 * positionClientY);

        sceneLocal.setCamera(camera);

        if ( (positionClientX == 0 || positionClientX == 2 ) && positionClientY != 1){
            sceneLocal.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.SPACE){
                    emetteur.chargerLancer();
                }
            });

            sceneLocal.setOnKeyReleased(e -> {
                if (e.getCode() == KeyCode.SPACE){
                    emetteur.sendLancer();
                }
            });

            sceneLocal.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.LEFT){
                    emetteur.mouvement(-1);
                }
            });

            sceneLocal.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.RIGHT){
                    emetteur.mouvement(1);
                }
            });
        }

    }



}
