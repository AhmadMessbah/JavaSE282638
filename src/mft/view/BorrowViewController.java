package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mft.controller.BorrowController;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BorrowViewController implements Initializable {

    @FXML
    private TextField idTxt, memberIdTxt, bookIdTxt, findIdTxt, findMemberIdTxt, findBookIdTxt, findBookNameTxt, findMemberNameTxt, findMemberFamilyTxt;

    @FXML
    private DatePicker borrowDate, returnDate, startDate, endDate;

    @FXML
    private Button saveBtn, editBtn, removeBtn, findIdBtn, findMemberIdBtn, findBookIdBtn, findByStatusBtn, findTimeRangeBtn, findBookNameBtn, findMemberNameFamilyBtn, findAllBtn ;

    @FXML
    private ToggleGroup statusGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saveBtn.setOnAction(event -> {
        String message = BorrowController.save(Integer.parseInt(memberIdTxt.getText()), Integer.parseInt(bookIdTxt.getText()), borrowDate.getValue().atTime(10,20), returnDate.getValue().atTime(10,20));
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.show();
        });

        editBtn.setOnAction(event -> {
            String message = BorrowController.edit(Integer.parseInt(idTxt.getText()), Integer.parseInt(memberIdTxt.getText()), Integer.parseInt(bookIdTxt.getText()), borrowDate.getValue().atTime(8,15), returnDate.getValue().atTime(8,15));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.show();
        });

        removeBtn.setOnAction(event -> {
            String message = BorrowController.remove(Integer.parseInt(idTxt.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.show();
        });

        findIdBtn.setOnAction(event -> {
            String message = BorrowController.findById(Integer.parseInt(findIdTxt.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message ,ButtonType.OK);
            alert.show();
        });

        findMemberIdBtn.setOnAction(event -> {
            String message = BorrowController.findByMemberId(Integer.parseInt(findMemberIdTxt.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.show();
        });

        findBookIdBtn.setOnAction(event -> {
            String message = BorrowController.findByBookId(Integer.parseInt(findBookIdTxt.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.show();
        });

        findTimeRangeBtn.setOnAction(event -> {
            String message = BorrowController.findByBorrowTimeStampRange(startDate.getValue().atTime(8,0),endDate.getValue().atTime(21,0));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.show();
        });

        findBookNameBtn.setOnAction(event -> {
            String message = BorrowController.findByBookName(findBookNameTxt.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.show();
        });

        findMemberNameFamilyBtn.setOnAction(event -> {
            String message = BorrowController.findByMemberNameAndFamily(findMemberNameTxt.getText(),findMemberFamilyTxt.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.show();
        });

        findAllBtn.setOnAction(event -> {
            String message = BorrowController.findAll();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.show();
        });

        findByStatusBtn.setOnAction(event -> {
            boolean returnStatus = true;
            RadioButton returnStatusRdb =(RadioButton) statusGroup.getSelectedToggle();
            if (Objects.equals(returnStatusRdb.getText(), "Not Returned")) {
                returnStatus = false;
            }
            String message = BorrowController.findByReturnStatus(returnStatus);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.show();
        });

    }
}
