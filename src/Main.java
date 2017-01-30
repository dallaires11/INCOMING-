/**
 * Created by Chroon on 2017-01-20.
 */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Button jesus=new Button("Jesus");
        jesus.setTranslateX(10);
        jesus.setTranslateY(30);

        root.getChildren().add(jesus);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
            launch(args);
        }

}