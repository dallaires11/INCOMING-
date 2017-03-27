package Final.Client.View;

import Final.Client.Controller.Emetteur;


import Final.Client.Controller.Passeur;
import Final.Client.Model.Catapulte;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SceneJeu implements Passeur{
    private int positionClientX, positionClientY;
    private Scene sceneLocal;
    private Group rootSceneJeu;
    private Group groupeProjectiles;
    private ParallelCamera camera;
    private ArrayList<ProjectileView> projectiles;
    private ArrayList<Catapulte> catapultes;
    private Stage stage;
    private Emetteur emetteur;


    public SceneJeu(Stage stage,Emetteur emetteur){
        this.emetteur=emetteur;
        catapultes = new ArrayList<>(2);
        projectiles = new ArrayList<>(10);
        camera = new ParallelCamera();
        this.stage=stage;

        rootSceneJeu = new Group();
        groupeProjectiles = new Group();
    }

    public void create(){
        createScene();
        createGroups();
    }

    public void setPositionClient(int positionClientX, int positionClientY){
        this.positionClientX = positionClientX;
        this.positionClientY = positionClientY;
    }

    public void passe(int positionProjectile, double x, double y){
        if (positionProjectile >= projectiles.size()){
            ProjectileView temp = new ProjectileView();
            System.out.println("creationP");
            projectiles.add(temp);
            groupeProjectiles.getChildren().add(temp);
        }

        projectiles.get(positionProjectile).setPosition(x, y);
    }

    public void mouvement(int position, int x, int y){
        catapultes.get(position).setPosition(x, y);
    }

    private void createGroups(){

        Rectangle sol = new Rectangle(0, 1960, 5760, 200);
        Rectangle ciel = new Rectangle(0, 0, 5760, 1960);
        sol.setFill(Color.GREEN);
        ciel.setFill(Color.ROYALBLUE);

        rootSceneJeu.getChildren().addAll(sol, ciel);
        rootSceneJeu.getChildren().add(groupeProjectiles);
    }

    private void createScene(){
        sceneLocal = new Scene(rootSceneJeu/*, 5760, 2160*/);

        if (positionClientY == 10 && positionClientX == 10){
            camera.resize(1920, 1080);
            camera.setScaleX(3);
            camera.setScaleY(2);

            sceneLocal.setCamera(camera);
        }
        else {
            camera.resize(1920, 1080);

            camera.setTranslateX(1920 * positionClientX);
            camera.setTranslateY(1080 * positionClientY);

            System.out.println(camera.getTranslateX());
            System.out.println(camera.getTranslateY());

            sceneLocal.setCamera(camera);
        }

        if ((positionClientX == 0 || positionClientX == 2) && positionClientY != 1)
            setAction();

        stage.setFullScreen(true);
    }

    private void setAction(){
    sceneLocal.setOnKeyPressed(e -> {
        if (e.getCode() == KeyCode.SPACE) {
            emetteur.chargerLancer();
        } else if (e.getCode() == KeyCode.LEFT) {
            emetteur.mouvement(-1);
        } else if (e.getCode() == KeyCode.RIGHT) {
            emetteur.mouvement(1);
        } else if (e.getCode() == KeyCode.UP){
            catapultes.get(positionClientX).rotation(1);
        } else if (e.getCode() == KeyCode.DOWN){
            catapultes.get(positionClientX).rotation(-1);
        }
    });

        sceneLocal.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                emetteur.sendLancer();
            }
        });
    }

    public Scene getScene(){
        return sceneLocal;
    }
}