package uet.oop.bomberman.graphics.fxml;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
        text.setFont(Font.font("Arial"));
        dialogVbox.getChildren().add(text);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setTitle("Hight Score");
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
