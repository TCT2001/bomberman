package uet.oop.bomberman.graphics.fxml;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uet.oop.bomberman.file.FileUltis;
import uet.oop.bomberman.gui.Frame;


public class HomeController {
    private static final String PATH_SETTING = "src/uet/oop/bomberman/graphics/fxml/setting.fxml";

    public void newGame(MouseEvent mouseEvent) {
        new Frame();
    }

    public void hightCore(MouseEvent mouseEvent) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);

        //get hight score
        String score = FileUltis.getHighScore();


        //set max score
        Text text = new Text(score);
        //CSS
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        text.setEffect(ds);
        text.setCache(true);
        text.setX(10.0f);
        text.setY(270.0f);
        text.setFill(Color.RED);
        text.setFont(Font.font(null, FontWeight.BOLD, 40));

        dialogVbox.getChildren().add(text);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setTitle("High Score");
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void quit(MouseEvent mouseEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void setting(MouseEvent mouseEvent) {
        FxHelper.setScene(mouseEvent, PATH_SETTING);

    }
}
