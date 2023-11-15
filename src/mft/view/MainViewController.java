package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mft.controller.BaseController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private Button memberBtn, userBtn, bookBtn, borrowBtn;

    @FXML
    private Label userLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLbl.setText("Welcome, "+ BaseController.user.getUserName());
        memberBtn.setOnAction((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MemberView.fxml")));
                stage.setTitle("Member");
                stage.setScene(scene);
                stage.show();
                memberBtn.getScene().getWindow().hide();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        userBtn.setOnAction((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserView.fxml")));
                stage.setTitle("User");
                stage.setScene(scene);
                stage.show();
                userBtn.getScene().getWindow().hide();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        bookBtn.setOnAction((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BookView.fxml")));
                stage.setTitle("Book");
                stage.setScene(scene);
                stage.show();
                bookBtn.getScene().getWindow().hide();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        borrowBtn.setOnAction((event) -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowView.fxml")));
                stage.setTitle("Borrow");
                stage.setScene(scene);
                stage.show();
                borrowBtn.getScene().getWindow().hide();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

    }
}
