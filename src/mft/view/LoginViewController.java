package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mft.controller.BaseController;
import mft.controller.UserController;
import mft.model.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button loginBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBtn.setOnAction(event -> {
            try {
                User user = UserController.login(usernameTxt.getText(), passwordTxt.getText());
                System.out.println(user);
                if (user != null) {
                    BaseController.user = user;
                    Stage stage = new Stage();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainFrame.fxml")));
                    stage.setScene(scene);
                    stage.setTitle("Main");
                    stage.show();
                    loginBtn.getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Access Denied", ButtonType.OK);
                    alert.show();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });
    }
}
