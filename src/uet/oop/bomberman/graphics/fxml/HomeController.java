package uet.oop.bomberman.graphics.fxml;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.gui.Frame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class HomeController {
    public void newGame(MouseEvent mouseEvent) {
        new Frame();
    }

    public void hightCore(MouseEvent mouseEvent) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);

        //read file hight score
        String score = "0";
        try {
            File myObj = new File("res/game/hightscore");
            Scanner myReader = new Scanner(myObj);
            score = myReader.nextLine();
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


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
        System.out.println("setting");
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            URL url =
                    new File("src/uet/oop/bomberman/graphics/fxml/setting.fxml")
                            .toURI().toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, BombermanGame.width, BombermanGame.height);
        stage.setScene(scene);
    }
}
