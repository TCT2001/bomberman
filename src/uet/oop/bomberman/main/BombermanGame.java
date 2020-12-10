package uet.oop.bomberman.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uet.oop.bomberman.game.Game;
import uet.oop.bomberman.sound.Sound;

import java.io.File;
import java.net.URL;


public class BombermanGame extends Application {

    public static final int width = Game.WIDTH * Game.SCALE;
    public static final int height = Game.HEIGHT * Game.SCALE;
    private static final String PATH_HOME = "src/uet/oop/bomberman/graphics/fxml/home.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File(PATH_HOME).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, width, height - 15);
        primaryStage.onCloseRequestProperty();
        primaryStage.setTitle("Bomberman Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


        Sound.playBackGround();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
