package uet.oop.bomberman.graphics.fxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uet.oop.bomberman.file.FileUltis;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    private static final String PATH_HOME = "src/uet/oop/bomberman/graphics/fxml/home.fxml";
    private String []s = new String[5];
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
    @FXML
    Button btnReturn;

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

    public void up() {
        i = 0;
        button = btnUp;
        getKey();
    }

    public void left() {
        i = 1;
        button = btnLeft;
        getKey();
    }

    public void right() {
        i = 3;
        button = btnRight;
        getKey();
    }

    public void down() {
        i = 2;
        button = btnDown;
        getKey();
    }

    public void boom() {
        i = 4;
        button = btnBoom;
        getKey();
    }

    public void setBtnReturn(MouseEvent mouseEvent) {
        FxHelper.setScene(mouseEvent, PATH_HOME);
    }


    public void getKey() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);

        //set max score
        Text text = new Text("Press any key\n\t");

        //CSS text
        FxHelper.textCss(text,32);

        dialogVbox.getChildren().add(text);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
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
