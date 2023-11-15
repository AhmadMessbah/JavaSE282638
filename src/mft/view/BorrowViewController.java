package mft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.controller.BorrowController;
import mft.controller.MemberController;
import mft.model.entity.Borrow;
import mft.model.entity.Member;

import java.net.URL;
import java.util.List;
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

    @FXML
    private TableView<Borrow> borrowTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        saveBtn.setOnAction(event -> {
        Borrow borrow = BorrowController.save(Integer.parseInt(memberIdTxt.getText()), Integer.parseInt(bookIdTxt.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION, borrow.toString(), ButtonType.OK);
        alert.show();
        resetForm();
        });

        editBtn.setOnAction(event -> {
            Borrow borrow = BorrowController.edit(Integer.parseInt(idTxt.getText()), Integer.parseInt(memberIdTxt.getText()), Integer.parseInt(bookIdTxt.getText()), borrowDate.getValue().atTime(8,15), returnDate.getValue().atTime(8,15));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, borrow.toString(), ButtonType.OK);
            alert.show();
        });

        removeBtn.setOnAction(event -> {
            Borrow borrow = BorrowController.remove(Integer.parseInt(idTxt.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, borrow.toString(), ButtonType.OK);
            alert.show();
        });

        findIdBtn.setOnAction(event -> {
            Borrow borrow = BorrowController.findById(Integer.parseInt(findIdTxt.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, borrow.toString() ,ButtonType.OK);
            alert.show();
        });

        findMemberIdBtn.setOnAction(event -> {
            List<Borrow> borrowList = BorrowController.findByMemberId(Integer.parseInt(findMemberIdTxt.getText()));

        });

        findBookIdBtn.setOnAction(event -> {
            List<Borrow> borrowList = BorrowController.findByBookId(Integer.parseInt(findBookIdTxt.getText()));

        });

        findTimeRangeBtn.setOnAction(event -> {
            List<Borrow> borrowList = BorrowController.findByBorrowTimeStampRange(startDate.getValue().atTime(8,0),endDate.getValue().atTime(21,0));

        });

        findBookNameBtn.setOnAction(event -> {
            List<Borrow> borrowList = BorrowController.findByBookName(findBookNameTxt.getText());

        });

        findMemberNameFamilyBtn.setOnAction(event -> {
            List<Borrow> borrowList = BorrowController.findByMemberNameAndFamily(findMemberNameTxt.getText(),findMemberFamilyTxt.getText());

        });

        findAllBtn.setOnAction(event -> {
            showDataOnTable(BorrowController.findAll());
        });

        findByStatusBtn.setOnAction(event -> {
            boolean returnStatus = true;
            RadioButton returnStatusRdb =(RadioButton) statusGroup.getSelectedToggle();
            if (Objects.equals(returnStatusRdb.getText(), "Not Returned")) {
                returnStatus = false;
            }
            List<Borrow> borrowList = BorrowController.findByReturnStatus(returnStatus);
        });
    }

    public void resetForm(){
        try{
            idTxt.clear();
            memberIdTxt.clear();
            bookIdTxt.clear();
            showDataOnTable(BorrowController.findAll());
        }catch (Exception e){

        }
    }

    public void showDataOnTable(List<Borrow> borrowList){
        borrowTbl.getColumns().clear();
        ObservableList<Borrow> borrows = FXCollections.observableList(borrowList);

        TableColumn<Borrow, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Borrow, String> memberCol = new TableColumn<>("MEMBER");
        idCol.setCellValueFactory(new PropertyValueFactory<>("member"));

        TableColumn<Borrow, String> bookCol = new TableColumn<>("BOOK");
        idCol.setCellValueFactory(new PropertyValueFactory<>("book"));



        borrowTbl.getColumns().add(idCol);
        borrowTbl.getColumns().add(memberCol);
        borrowTbl.getColumns().add(bookCol);
        borrowTbl.setItems(borrows);
    }
}
