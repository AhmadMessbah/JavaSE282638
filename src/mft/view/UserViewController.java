package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mft.controller.UserController;


import java.net.URL;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {
    @FXML
    private TextField idTxt, usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button signupBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signupBtn.setOnAction(event -> {
            String message = UserController.save(1,usernameTxt.getText(),passwordTxt.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.show();
        });
    }
}
