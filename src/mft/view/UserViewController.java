package mft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mft.controller.MemberController;
import mft.controller.UserController;
import mft.model.entity.Member;
import mft.model.entity.User;


import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {
    @FXML
    private TextField idTxt, usernameTxt, nicknameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button saveBtn, editBtn, removeBtn;

    @FXML
    private TableView<User> userTbl;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        userTbl.setOnMouseClicked((event) -> {
            User user = userTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(user.getId()));
            usernameTxt.setText(user.getUserName());
            passwordTxt.setText(user.getPassword());
            nicknameTxt.setText(user.getNickName());
        });

        saveBtn.setOnAction(event -> {
            Map<String, String> result = UserController.save(1, usernameTxt.getText(), passwordTxt.getText(), nicknameTxt.getText(), String.valueOf(imageView.getImage()));
            if (result.get("status").equals("true")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
                alert.show();
                resetForm();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
                alert.show();
            }
        });
        editBtn.setOnAction(event -> {
            Map<String, String> result = UserController.edit(Integer.parseInt(idTxt.getText()), usernameTxt.getText(), passwordTxt.getText());
            if (result.get("status").equals("true")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
                alert.show();
                resetForm();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
                alert.show();
            }
        });
        removeBtn.setOnAction(event -> {
            Map<String, String> result = UserController.remove(Integer.parseInt(idTxt.getText()));
            if (result.get("status").equals("true")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
                alert.show();
                resetForm();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
                alert.show();
            }
        });


    }

    public void resetForm() {
        try {
            idTxt.clear();
            usernameTxt.clear();
            passwordTxt.clear();
            nicknameTxt.clear();
            showDataOnTable(UserController.findAll());
        } catch (Exception e) {

        }
    }

    public void showDataOnTable(List<User> userList) {
        userTbl.getColumns().clear();
        ObservableList<User> users = FXCollections.observableList(userList);

        TableColumn<User, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("UserName"));

        TableColumn<User, String> nicknameCol = new TableColumn<>("NICKNAME");
        nicknameCol.setCellValueFactory(new PropertyValueFactory<>("Nickname"));

        TableColumn<User, String> imageView = new TableColumn<>("IMAGEVIEW");
        imageView.setCellValueFactory(new PropertyValueFactory<>("Imageview"));


        userTbl.getColumns().addAll(idCol, usernameCol, nicknameCol, imageView);
        userTbl.setItems(users);
    }
}