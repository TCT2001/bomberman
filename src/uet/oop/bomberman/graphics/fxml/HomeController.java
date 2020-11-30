package uet.oop.bomberman.graphics.fxml;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;
import uet.oop.bomberman.gui.Frame;

import java.io.File;

public class HomeController {
    public void newGame(MouseEvent mouseEvent) {
        new Frame();
        Platform.exit();
    }

    public void hightCore(MouseEvent mouseEvent) {
        System.out.println("hight score");
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);

        //set max score
        Text text = new Text("hello world");
        text.setFont(Font.font("Arial"));
        dialogVbox.getChildren().add(text);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setTitle("Hight Score");
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void quit(MouseEvent mouseEvent) {
        System.out.println("quit");
    }

    public void setting(MouseEvent mouseEvent) {
        System.out.println("setting");
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);

        //set max score
        Button button = new Button("nghich");
        button.setOnMouseClicked(event -> {
            System.out.println("perfect");
        });
        dialogVbox.getChildren().add(button);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setTitle("Hight Score");
        dialog.setScene(dialogScene);
        dialog.show();

    }
}
