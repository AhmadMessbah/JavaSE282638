package mft.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mft.controller.BaseController;
import mft.controller.BorrowController;
import mft.controller.FormType;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BorrowViewController implements Initializable {

    @FXML
    private Button borrowBtn, editBtn, removeBtn, returnBtn, findIdBtn, findMemberIdBtn, findBookIdBtn, findStatusBtn, findTimeRangeBtn, findBookNameBtn, findMemberNameBtn, findAllBtn, backBtn, exitBtn;

    @FXML
    private Label userLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userLbl.setText("User : " + BaseController.user.getUserName());

        borrowBtn.setOnAction(event -> {
            BaseController.formType = FormType.newBorrow;
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowModifyView.fxml")));
                stage.setScene(scene);
                stage.setTitle("New Borrow");
                stage.show();
                borrowBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        editBtn.setOnAction(event -> {
            BaseController.formType = FormType.editBorrow;
            Map<String, List> result = BorrowController.findAll();
            try {
                if (result.containsKey("ok")) {
                    Stage stage = new Stage();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowModifyView.fxml")));
                    stage.setScene(scene);
                    stage.setTitle("Edit Borrow");
                    stage.show();
                    editBtn.getScene().getWindow().hide();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString().replace("=null",""), ButtonType.CANCEL);
                    alert.show();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        returnBtn.setOnAction(event -> {
            BaseController.formType = FormType.returnBorrow;
            Map<String, List> result = BorrowController.findByReturnStatus(false);
            try {
                if (result.containsKey("ok")) {
                    Stage stage = new Stage();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowModifyView.fxml")));
                    stage.setScene(scene);
                    stage.setTitle("Return Borrow");
                    stage.show();
                    returnBtn.getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString().replace("=null",""), ButtonType.CANCEL);
                    alert.show();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        removeBtn.setOnAction(event -> {
            BaseController.formType = FormType.removeBorrow;
            Map<String, List> result = BorrowController.findByDeletedStatus(false);
            try {
                if (result.containsKey("ok")) {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowModifyView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Remove Borrow");
                stage.show();
                removeBtn.getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString().replace("=null", ""), ButtonType.CANCEL);
                    alert.show();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        findAllBtn.setOnAction(event -> {
            BaseController.formType = FormType.findAllBorrow;
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowFindView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Find All");
                stage.show();
                findAllBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        findIdBtn.setOnAction(event -> {
            BaseController.formType = FormType.findByIdBorrow;
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowFindView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Find By ID");
                stage.show();
                findIdBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        findMemberIdBtn.setOnAction(event -> {
            BaseController.formType = FormType.findByMemberIdBorrow;
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowFindView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Find By Member's ID");
                stage.show();
                findMemberIdBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        findBookIdBtn.setOnAction(event -> {
            BaseController.formType = FormType.findByBookIdBorrow;
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowFindView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Find By Book's ID");
                stage.show();
                findBookIdBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        findMemberNameBtn.setOnAction(event -> {
            BaseController.formType = FormType.findByMemberNameAndFamily;
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowFindView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Find By Member's Name And Family");
                stage.show();
                findMemberNameBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        findBookNameBtn.setOnAction(event -> {
            BaseController.formType = FormType.findByBookName;
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowFindView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Find By Book's Name");
                stage.show();
                findBookNameBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        findStatusBtn.setOnAction(event -> {
            BaseController.formType = FormType.findByStatus;
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowFindView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Find By Return Status");
                stage.show();
                findStatusBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        findTimeRangeBtn.setOnAction(event -> {
            BaseController.formType = FormType.findByTimeRange;
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowFindView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Find By Borrow Time Range");
                stage.show();
                findTimeRangeBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        backBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainFrame.fxml")));
                stage.setScene(scene);
                stage.setTitle("Main");
                stage.show();
                backBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        exitBtn.setOnAction(event -> {
            Platform.exit();
        });
    }
}
