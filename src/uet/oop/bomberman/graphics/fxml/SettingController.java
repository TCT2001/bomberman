package uet.oop.bomberman.graphics.fxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uet.oop.bomberman.file.FileUltis;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    private static final String PATH_HOME = "src/uet/oop/bomberman/graphics/fxml/home.fxml";
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
        s = FileUltis.getKeyNav();
        btnUp.setText(s[0]);
        btnLeft.setText(s[1]);
        btnDown.setText(s[2]);
        btnRight.setText(s[3]);
        btnBoom.setText(s[4]);
    }

    public void save(MouseEvent mouseEvent) {
        //nav home
        FxHelper.setScene(mouseEvent, PATH_HOME);
        //save in file
        FileUltis.setKeyNav(s);
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
