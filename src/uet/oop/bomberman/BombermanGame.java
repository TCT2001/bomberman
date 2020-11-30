package uet.oop.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uet.oop.bomberman.gui.Frame;
import uet.oop.bomberman.sound.Sound;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int width = Game.WIDTH * Game.SCALE * 5/6;
    public static final int height = Game.HEIGHT * Game.SCALE * 5/6;


    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url =
                new File("src/uet/oop/bomberman/graphics/fxml/home.fxml")
                        .toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, width, height);
		primaryStage.setTitle("Dictionary");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
