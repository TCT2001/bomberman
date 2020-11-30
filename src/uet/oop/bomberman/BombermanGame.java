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

    private final int width = 786;
    private final int height = 486;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/uet/oop/bomberman/graphics/fxml/home.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
//		Parent root = FXMLLoader.load(getClass().getResource("src/uet/oop/bomberman/view/test.fxml"));
		Scene scene = new Scene(root, 240*3, 208*3);
		primaryStage.setTitle("Dictionary");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

//        File file = new File("src/uet/oop/bomberman/view/test2");
//        try {
//            Scanner myReader = new Scanner(file);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                System.out.println(data);
//            }
//            myReader.close();
//        } catch (
//                FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
