package Final.Client.View;

import Final.Client.Controller.Emetteur;
import Final.Client.Controller.Passeur;
import Final.Client.Model.*;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class SceneJeu implements Passeur {
    private int positionClientX, positionClientY;
    private int joueur;
    private static Scene sceneLocal;
    private Group rootSceneJeu,rootDynamique;
    private Group groupeProjectiles;
    private HBox groupeLabels;
    private ParallelCamera camera;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Catapulte> catapultes;
    private ArrayList<Infos> infos;
    private Stage stage;
    private Emetteur emetteur;
    private SceneVictoire sceneVictoire;
    private ScenePerdu scenePerdu;
    private MediaPlayer lancer;


    public SceneJeu(Stage stage, Emetteur emetteur, SceneVictoire victoire, ScenePerdu perdu) {
        this.emetteur = emetteur;
        this.sceneVictoire = victoire;
        this.scenePerdu = perdu;
        catapultes = new ArrayList<>(2);
        projectiles = new ArrayList<>(20);
        //infos = new ArrayList<>(20);
        camera = new ParallelCamera();
        this.stage = stage;
        lancer = new MediaPlayer(new Media(new File("src/Son/Lancer.mp3").toURI().toString()));

        catapultes.add(new Catapulte(1));
        catapultes.add(new Catapulte(2));

        rootSceneJeu = new Group();
        rootDynamique = new Group();
        groupeProjectiles = new Group();
        groupeLabels = new HBox();
    }

    void create(int positionClientX, int positionClientY) {
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
        setTitre();

        stage.show();
    }

    public void passe(int positionProjectile, double x, double y, float vitX, float vitY, double masse, double taille) {
        if (positionProjectile >= projectiles.size()) {
            Projectile temp = new Projectile(masse, taille);
            //Infos label = new Infos(masse);
            System.out.println("creationP-> Masse =  " + masse + " type  = " + taille);

            //infos.add(label);
            //groupeLabels.getChildren().add(label);
            projectiles.add(temp);
            groupeProjectiles.getChildren().add(temp);
        }

        projectiles.get(positionProjectile).setPosition(x, y, vitX, vitY);
        //infos.get(positionProjectile).setLabels(x, y, vitX, vitY);
    }

    public void mouvement(int position, int x, int y) {
        catapultes.get(position).setTranslateX(x);
        catapultes.get(position).setTranslateY(y);
    }

    private void createGroups() {

        Rectangle sol = new Rectangle(0, 1960, 5760, 200);
        Rectangle ciel = new Rectangle(0, 0, 5760, 1960);
        sol.setFill(Color.GREEN);
        ciel.setFill(Color.CORNFLOWERBLUE);

        Path montagnes = new Path();
        montagnes.getElements().addAll(new MoveTo(0.0, 500.0),
                new LineTo(400.0, 100.0),
                new LineTo(600.0, 400.0),
                new LineTo(900.0, 600.0),
                new LineTo(1500.0, 800.0),
                new LineTo(1600.0, 400.0),
                new LineTo(2000.0, 600.0),
                new LineTo(2300.0, 800.0),
                new LineTo(2600.0, 930.0),
                new LineTo(3000.0, 300.0),
                new LineTo(3200.0, 400.0),
                new LineTo(3500.0, 700.0),
                new LineTo(3700.0, 600.0),
                new LineTo(5760.0, 400.0),
                new LineTo(5760.0, 960.0),
                new LineTo(0.0, 960.0),
                new LineTo(0.0, 500.0));

        montagnes.setFill(Color.RED);
        montagnes.setStroke(Color.BLUE);

        rootDynamique.getChildren().add(catapultes.get(0).getView());
        rootDynamique.getChildren().add(catapultes.get(1).getView());
        rootDynamique.getChildren().add(groupeProjectiles);
        rootDynamique.getChildren().add(groupeLabels);
        rootSceneJeu.getChildren().addAll(sol, ciel, montagnes, rootDynamique);
    }

    private void createScene() {
        sceneLocal = new Scene(rootSceneJeu,Color.BLACK);

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
                emetteur.mouvement(joueur, -1);
            } else if (e.getCode() == KeyCode.RIGHT) {
                emetteur.mouvement(joueur, 1);
            } else if (e.getCode() == KeyCode.UP) {
                catapultes.get(joueur).rotation(1);
            } else if (e.getCode() == KeyCode.DOWN) {
                catapultes.get(joueur).rotation(-1);
            }
        });

        sceneLocal.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                emetteur.sendLancer(catapultes.get(joueur));
                lancer.play();
            } else if (e.getCode() == KeyCode.LEFT) {
                emetteur.mouvement(joueur, 0);
            } else if (e.getCode() == KeyCode.RIGHT) {
                    emetteur.mouvement(joueur, 0);
            } else if (e.getCode() == KeyCode.UP) {
                catapultes.get(joueur).rotation(0);
            } else if (e.getCode() == KeyCode.DOWN) {
                catapultes.get(joueur).rotation(0);
            }
        });
    }

    private void setTitre(){
        if (positionClientY==0)
            stage.setTitle("Ciel "+(positionClientX+1));
        else if (positionClientY==10)
            stage.setTitle("Observateur");
        else if (positionClientY==1&&(positionClientX==0||positionClientX==2))
            stage.setTitle("Joueur "+(joueur+1));
        else if (positionClientY==1&&positionClientX==1)
            stage.setTitle("Champs de bataille");
    }

    static Scene getScene() {
        return sceneLocal;
    }

    public SceneJeu getThis() {
        return this;
    }

    public void setToBlack(int perdant){
        Platform.runLater(()->{
            groupeProjectiles.getChildren().clear();
            projectiles.clear();
            rootDynamique.getChildren().clear();

            if((positionClientX == 0 || positionClientX == 2) && positionClientY == 1){
                if(joueur==perdant) {
                    stage.setScene(scenePerdu.getScene());
                    stage.setFullScreen(true);
                }
                else{
                    stage.setScene(sceneVictoire.getScene());
                    stage.setFullScreen(true);
                }
            }
            else {
                rootSceneJeu.setVisible(false);
            }
        });
    }

    public void setToGame(){
        this.create(positionClientX, positionClientY);
        rootSceneJeu.setVisible(true);
    }
}