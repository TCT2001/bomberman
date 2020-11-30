package uet.oop.bomberman.graphics.fxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SettingController implements Initializable {
    private String s[] = new String[5];
    private int i = 0;

    @FXML
    Button btnUp;
    @FXML
    Button btnLeft;
    @FXML
    Button btnDown;
    @FXML
    Button btnRight;
    @FXML
    Button btnBoom;
    @FXML
    Button btnSave;

    Button button;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button = new Button();
        String data;
        try {
            File myObj = new File("res/game/setting");
            Scanner myReader = new Scanner(myObj);
            for (int i = 0; i < 5; i++) {
                s[i] = myReader.nextLine();
            }
            myReader.close();

            btnUp.setText(s[0]);
            btnLeft.setText(s[1]);
            btnDown.setText(s[2]);
            btnRight.setText(s[3]);
            btnBoom.setText(s[4]);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void save(MouseEvent mouseEvent) {
        System.out.println("?");
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            URL url =
                    new File("src/uet/oop/bomberman/graphics/fxml/home.fxml")
                            .toURI().toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, BombermanGame.width, BombermanGame.height);
        stage.setScene(scene);
        //save
        try {
            FileWriter myWriter = new FileWriter("res/game/setting");
            for (String s1 : s) {
                myWriter.write(s1 + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void up(MouseEvent mouseEvent) {
        i = 0;
        button = btnUp;
        getKey();
    }

    public void left(MouseEvent mouseEvent) {
        i = 1;
        button = btnLeft;
        getKey();
    }

    public void right(MouseEvent mouseEvent) {
        i = 3;
        button = btnRight;
        getKey();
    }

    public void down(MouseEvent mouseEvent) {
        i = 2;
        button = btnDown;
        getKey();
    }

    public void boom(MouseEvent mouseEvent) {
        i = 4;
        button = btnBoom;
        getKey();
    }

    public void getKey() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        //set max score
        Text text = new Text("press any key\n\t....");
        text.setFont(Font.font("Arial"));
        dialogVbox.getChildren().add(text);
        Scene dialogScene = new Scene(dialogVbox, 200, 100);
        dialogScene.setOnKeyPressed(keyEvent -> {
            dialog.hide();
            s[i] = keyEvent.getCode().toString();
            button.setText(s[i]);

        });
        dialog.setTitle("Setting");
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
