package uet.oop.bomberman.graphics.fxml;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.main.BombermanGame;

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
        assert root != null;
        Scene scene = new Scene(root, 640, 505);
        stage.setScene(scene);
    }
    public static void textCss(Text text, int size) {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        text.setEffect(ds);
        text.setCache(true);
        text.setX(10.0f);
        text.setY(270.0f);
        text.setFill(Color.RED);
        text.setFont(Font.font(null, FontWeight.BOLD, size));
    }
}
