package uet.oop.bomberman.graphics.fxml;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FxHelper {
    public static void setScene(MouseEvent event ,String path) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            URL url =
                    new File(path)
                            .toURI().toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, BombermanGame.width, BombermanGame.height);
        stage.setScene(scene);
    }

}
