package mft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.controller.MemberController;
import mft.controller.UserController;
import mft.model.entity.Member;
import mft.model.entity.User;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {
    @FXML
    private TextField idTxt, usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button saveBtn, editBtn, removeBtn;

    @FXML
    private TableView userTbl;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveBtn.setOnAction(event -> {
            User user = User.builder()
                    .id(Integer.parseInt(idTxt.getText()))
                    .userName(usernameTxt.getText())
                    .password(passwordTxt.getText())
                    .build();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, user.toString(), ButtonType.OK);
            alert.show();
        });
        editBtn.setOnAction(event -> {
            User user = User.builder()
                    .userName(usernameTxt.getText())
                    .build();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, user.toString(), ButtonType.OK);
            alert.show();
        });
        removeBtn.setOnAction(event -> {
            User user = User.builder()
                    .id(Integer.parseInt(idTxt.getText()))
                    .build();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, user.toString(), ButtonType.OK);
            alert.show();
        });


    }

    public void resetForm() {
        try {
            idTxt.clear();
            usernameTxt.clear();
            passwordTxt.clear();
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
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));


        userTbl.getColumns().addAll(idCol);
        userTbl.setItems(users);
    }
}