package Final.Client.View;

import Final.Client.Controller.Emetteur;


import Final.Client.Controller.Passeur;
import Final.Client.Model.Catapulte;
import Final.Client.Model.Projectile;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SceneJeu implements Passeur {
    private int positionClientX, positionClientY;
    private int joueur;
    private Scene sceneLocal;
    private Group rootSceneJeu;
    private Group groupeProjectiles;
    private ParallelCamera camera;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Catapulte> catapultes;
    private Stage stage;
    private Emetteur emetteur;


    public SceneJeu(Stage stage, Emetteur emetteur, SceneVictoire victoire, ScenePerdu perdu) {
        this.emetteur = emetteur;
        catapultes = new ArrayList<>(2);
        projectiles = new ArrayList<>(10);
        camera = new ParallelCamera();
        this.stage = stage;

        catapultes.add(new Catapulte(1));
        catapultes.add(new Catapulte(2));

        rootSceneJeu = new Group();
    }

    public void create(int positionClientX, int positionClientY) {
        if (positionClientX == 0) {
            joueur = 0;
        } else if (positionClientX == 2) {
            joueur = 1;
        }
        this.positionClientX = positionClientX;
        this.positionClientY = positionClientY;

        System.out.println(".create Scenejeu -> Position client X : " + positionClientX + " | Position client Y : " + positionClientY + " | Joueur : " + joueur);
        createGroups();
        createScene();

        stage.show();
    }

    public void passe(int positionProjectile, double x, double y, int masse, int type) {
        if (positionProjectile >= projectiles.size()) {
            Projectile temp = new Projectile(masse, type);
            System.out.println("creationP-> Masse =  " + masse + " type  = " + type);
            projectiles.add(temp);
            rootSceneJeu.getChildren().add(temp);
        }

        projectiles.get(positionProjectile).setPosition(x, y);
    }

    public void mouvement(int position, int x, int y) {
        catapultes.get(position).setTranslateX(x);
        catapultes.get(position).setTranslateY(y);
    }

    private void createGroups() {

        Rectangle sol = new Rectangle(0, 1960, 5760, 200);
        Rectangle ciel = new Rectangle(0, 0, 5760, 1960);
        sol.setFill(Color.GREEN);
        ciel.setFill(Color.AZURE);

        rootSceneJeu.getChildren().addAll(sol, ciel);
        rootSceneJeu.getChildren().add(catapultes.get(0).getView());
        rootSceneJeu.getChildren().add(catapultes.get(1).getView());

    }

    private void createScene() {
        sceneLocal = new Scene(rootSceneJeu);

        if (positionClientY == 10 && positionClientX == 10) {
            camera.resize(1920, 1080);
            camera.setScaleX(3);
            camera.setScaleY(2);

            sceneLocal.setCamera(camera);
        } else {
            camera.resize(1920, 1080);

            camera.setTranslateX(1920 * positionClientX);
            camera.setTranslateY(1080 * positionClientY);

            System.out.println(camera.getTranslateX());
            System.out.println(camera.getTranslateY());

            sceneLocal.setCamera(camera);
        }

        if ((positionClientX == 0 || positionClientX == 2) && positionClientY == 1)
            setAction();

    }

    private void setAction() {

        sceneLocal.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                emetteur.chargerLancer();
            } else if (e.getCode() == KeyCode.LEFT) {
                emetteur.mouvement(-1);
            } else if (e.getCode() == KeyCode.RIGHT) {
                emetteur.mouvement(1);
            } else if (e.getCode() == KeyCode.UP) {
                catapultes.get(joueur).rotation(1);
            } else if (e.getCode() == KeyCode.DOWN) {
                catapultes.get(joueur).rotation(-1);
            }
        });

        sceneLocal.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                emetteur.sendLancer(joueur, catapultes.get(joueur).getAngleDeTir());
            }
        });
    }

    public Scene getScene() {
        return sceneLocal;
    }

    public SceneJeu getThis() {
        return this;
    }

    public void setFullscreen() {
        stage.setFullScreen(true);
    }
}