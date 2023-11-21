package mft.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import mft.controller.BaseController;
import mft.controller.BorrowController;
import mft.controller.FormType;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Member;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class BorrowFindViewController implements Initializable {

    @FXML
    private TextField findIdTxt, findMemberIdTxt, findBookIdTxt, findBookNameTxt, findMemberNameTxt, findMemberFamilyTxt;

    @FXML
    private DatePicker startDate, endDate;

    @FXML
    private Button findAllBtn, backBtn, exitBtn;

    @FXML
    private ToggleGroup statusGroup;

    @FXML
    private TableView<Borrow> borrowTbl;

    @FXML
    private Label userLbl, idLbl, borrowRangeLbl, fromLbl, untilLbl, bookNameLbl, memberNameLbl, memberFamilyLbl, bookIdLbl, memberIdLbl;

    @FXML
    private Line lineLne;

    @FXML
    private RadioButton returnedRdb, notReturnedRdb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        resetForm();
        borrowTbl.getColumns().clear();

        userLbl.setText("User : " + BaseController.user.getUserName());

        backBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowView.fxml")));
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


        if (BaseController.formType.equals(FormType.findAllBorrow)) {
            findAllBtn.setOnAction(event -> {
                Map<String, List> result = BorrowController.findAll();
                if (result.containsKey("ok")) {
                    showDataOnTable(result.get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString(), ButtonType.CANCEL);
                    alert.show();
                    borrowTbl.getColumns().clear();
                }
            });
            findIdTxt.setVisible(false);
            findBookIdTxt.setVisible(false);
            findMemberIdTxt.setVisible(false);
            findBookNameTxt.setVisible(false);
            findMemberNameTxt.setVisible(false);
            findMemberFamilyTxt.setVisible(false);
            idLbl.setVisible(false);
            bookIdLbl.setVisible(false);
            memberIdLbl.setVisible(false);
            bookNameLbl.setVisible(false);
            memberFamilyLbl.setVisible(false);
            memberNameLbl.setVisible(false);
            borrowRangeLbl.setVisible(false);
            fromLbl.setVisible(false);
            untilLbl.setVisible(false);
            startDate.setVisible(false);
            endDate.setVisible(false);
            lineLne.setVisible(false);
            returnedRdb.setVisible(false);
            notReturnedRdb.setVisible(false);
        } else if (BaseController.formType.equals(FormType.findByIdBorrow)) {
            findAllBtn.setText("Find By Borrow ID");
            findAllBtn.setOnAction(event -> {
                Map<String, Borrow> result = BorrowController.findById(Integer.parseInt(findIdTxt.getText()));
                if (result.containsKey("ok")) {
                    List<Borrow> borrowList = new ArrayList<>();
                    borrowList.add(result.get("ok"));
                    showDataOnTable(borrowList);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString(), ButtonType.CANCEL);
                    alert.show();
                    borrowTbl.getColumns().clear();
                }
                resetForm();
            });
            findBookIdTxt.setVisible(false);
            findMemberIdTxt.setVisible(false);
            findBookNameTxt.setVisible(false);
            findMemberNameTxt.setVisible(false);
            findMemberFamilyTxt.setVisible(false);
            bookIdLbl.setVisible(false);
            memberIdLbl.setVisible(false);
            bookNameLbl.setVisible(false);
            memberFamilyLbl.setVisible(false);
            memberNameLbl.setVisible(false);
            borrowRangeLbl.setVisible(false);
            fromLbl.setVisible(false);
            untilLbl.setVisible(false);
            startDate.setVisible(false);
            endDate.setVisible(false);
            lineLne.setVisible(false);
            returnedRdb.setVisible(false);
            notReturnedRdb.setVisible(false);
        } else if (BaseController.formType.equals(FormType.findByMemberIdBorrow)) {
            findAllBtn.setText("Find By Member ID");
            findAllBtn.setOnAction(event -> {
                Map<String, List> result = BorrowController.findByMemberId(Integer.parseInt(findMemberIdTxt.getText()));
                if (result.containsKey("ok")) {
                    showDataOnTable(result.get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString(), ButtonType.CANCEL);
                    alert.show();
                    borrowTbl.getColumns().clear();
                }
                resetForm();
            });
            findIdTxt.setVisible(false);
            findBookIdTxt.setVisible(false);
            findBookNameTxt.setVisible(false);
            findMemberNameTxt.setVisible(false);
            findMemberFamilyTxt.setVisible(false);
            idLbl.setVisible(false);
            bookIdLbl.setVisible(false);
            bookNameLbl.setVisible(false);
            memberFamilyLbl.setVisible(false);
            memberNameLbl.setVisible(false);
            borrowRangeLbl.setVisible(false);
            fromLbl.setVisible(false);
            untilLbl.setVisible(false);
            startDate.setVisible(false);
            endDate.setVisible(false);
            lineLne.setVisible(false);
            returnedRdb.setVisible(false);
            notReturnedRdb.setVisible(false);
        } else if (BaseController.formType.equals(FormType.findByBookIdBorrow)) {
            findAllBtn.setText("Find By Book ID");
            findAllBtn.setOnAction(event -> {
                Map<String, List> result = BorrowController.findByBookId(Integer.parseInt(findBookIdTxt.getText()));
                if (result.containsKey("ok")) {
                    showDataOnTable(result.get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString(), ButtonType.CANCEL);
                    alert.show();
                    borrowTbl.getColumns().clear();
                }
                resetForm();
            });
            findIdTxt.setVisible(false);
            findMemberIdTxt.setVisible(false);
            findBookNameTxt.setVisible(false);
            findMemberNameTxt.setVisible(false);
            findMemberFamilyTxt.setVisible(false);
            idLbl.setVisible(false);
            memberIdLbl.setVisible(false);
            bookNameLbl.setVisible(false);
            memberFamilyLbl.setVisible(false);
            memberNameLbl.setVisible(false);
            borrowRangeLbl.setVisible(false);
            fromLbl.setVisible(false);
            untilLbl.setVisible(false);
            startDate.setVisible(false);
            endDate.setVisible(false);
            lineLne.setVisible(false);
            returnedRdb.setVisible(false);
            notReturnedRdb.setVisible(false);
        } else if (BaseController.formType.equals(FormType.findByTimeRange)) {
            findAllBtn.setText("Find By Borrow Time Range");
            findAllBtn.setOnAction(event -> {
                Map<String, List> result = BorrowController.findByBorrowTimeStampRange(startDate.getValue().atTime(8, 0), endDate.getValue().atTime(21, 0));
                if (result.containsKey("ok")) {
                    showDataOnTable(result.get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString(), ButtonType.CANCEL);
                    alert.show();
                    borrowTbl.getColumns().clear();
                }
                resetForm();
            });
            findIdTxt.setVisible(false);
            findBookIdTxt.setVisible(false);
            findMemberIdTxt.setVisible(false);
            findBookNameTxt.setVisible(false);
            findMemberNameTxt.setVisible(false);
            findMemberFamilyTxt.setVisible(false);
            idLbl.setVisible(false);
            bookIdLbl.setVisible(false);
            memberIdLbl.setVisible(false);
            bookNameLbl.setVisible(false);
            memberFamilyLbl.setVisible(false);
            memberNameLbl.setVisible(false);
            returnedRdb.setVisible(false);
            notReturnedRdb.setVisible(false);
        } else if (BaseController.formType.equals(FormType.findByBookName)) {
            findAllBtn.setText("Find By Book's Name");
            findAllBtn.setOnAction(event -> {
                Map<String, List> result = BorrowController.findByBookName(findBookNameTxt.getText());
                if (result.containsKey("ok")) {
                    showDataOnTable(result.get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString(), ButtonType.CANCEL);
                    alert.show();
                    borrowTbl.getColumns().clear();
                }
                resetForm();
            });
            findIdTxt.setVisible(false);
            findBookIdTxt.setVisible(false);
            findMemberIdTxt.setVisible(false);
            findMemberNameTxt.setVisible(false);
            findMemberFamilyTxt.setVisible(false);
            idLbl.setVisible(false);
            bookIdLbl.setVisible(false);
            memberIdLbl.setVisible(false);
            memberFamilyLbl.setVisible(false);
            memberNameLbl.setVisible(false);
            borrowRangeLbl.setVisible(false);
            fromLbl.setVisible(false);
            untilLbl.setVisible(false);
            startDate.setVisible(false);
            endDate.setVisible(false);
            lineLne.setVisible(false);
            returnedRdb.setVisible(false);
            notReturnedRdb.setVisible(false);
        } else if (BaseController.formType.equals(FormType.findByMemberNameAndFamily)) {
            findAllBtn.setText("Find By Member's Name");
            findAllBtn.setOnAction(event -> {
                Map<String, List> result = BorrowController.findByMemberNameAndFamily(findMemberNameTxt.getText(), findMemberFamilyTxt.getText());
                if (result.containsKey("ok")) {
                    showDataOnTable(result.get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString(), ButtonType.CANCEL);
                    alert.show();
                    borrowTbl.getColumns().clear();
                }
                resetForm();
            });
            findIdTxt.setVisible(false);
            findBookIdTxt.setVisible(false);
            findMemberIdTxt.setVisible(false);
            findBookNameTxt.setVisible(false);
            idLbl.setVisible(false);
            bookIdLbl.setVisible(false);
            memberIdLbl.setVisible(false);
            bookNameLbl.setVisible(false);
            borrowRangeLbl.setVisible(false);
            fromLbl.setVisible(false);
            untilLbl.setVisible(false);
            startDate.setVisible(false);
            endDate.setVisible(false);
            lineLne.setVisible(false);
            returnedRdb.setVisible(false);
            notReturnedRdb.setVisible(false);
        } else if (BaseController.formType.equals(FormType.findByStatus)) {
            findAllBtn.setText("Find By Status");
            findAllBtn.setOnAction(event -> {
                boolean returnStatus = true;
                RadioButton returnStatusRdb = (RadioButton) statusGroup.getSelectedToggle();
                if (Objects.equals(returnStatusRdb.getText(), "Not Returned")) {
                    returnStatus = false;
                }
                Map<String, List> result = BorrowController.findByReturnStatus(returnStatus);
                if (result.containsKey("ok")) {
                    showDataOnTable(result.get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.toString(), ButtonType.CANCEL);
                    alert.show();
                    borrowTbl.getColumns().clear();
                }
            });
            findIdTxt.setVisible(false);
            findBookIdTxt.setVisible(false);
            findMemberIdTxt.setVisible(false);
            findBookNameTxt.setVisible(false);
            findMemberNameTxt.setVisible(false);
            findMemberFamilyTxt.setVisible(false);
            idLbl.setVisible(false);
            bookIdLbl.setVisible(false);
            memberIdLbl.setVisible(false);
            bookNameLbl.setVisible(false);
            memberFamilyLbl.setVisible(false);
            memberNameLbl.setVisible(false);
            borrowRangeLbl.setVisible(false);
            fromLbl.setVisible(false);
            untilLbl.setVisible(false);
            startDate.setVisible(false);
            endDate.setVisible(false);
            lineLne.setVisible(false);
        }
    }

    public void resetForm() {
        try {
            findIdTxt.clear();
            findMemberIdTxt.clear();
            findBookIdTxt.clear();
            findMemberNameTxt.clear();
            findMemberFamilyTxt.clear();
            findBookNameTxt.clear();
        } catch (Exception e) {

        }
    }

    public void showDataOnTable(List<Borrow> borrowList) {
        borrowTbl.getColumns().clear();
        ObservableList<Borrow> borrows = FXCollections.observableList(borrowList);

        TableColumn<Borrow, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Borrow, Member> memberCol = new TableColumn<>("Member");
        memberCol.setCellValueFactory(new PropertyValueFactory<>("member"));

        TableColumn<Borrow, Book> bookCol = new TableColumn<>("Book");
        bookCol.setCellValueFactory(new PropertyValueFactory<>("book"));

        TableColumn<Borrow, LocalDateTime> borrowTimeCol = new TableColumn<>("Borrow Time");
        borrowTimeCol.setCellValueFactory(new PropertyValueFactory<>("borrowTimeStamp"));

        TableColumn<Borrow, LocalDateTime> returnTimeCol = new TableColumn<>("Return Time");
        returnTimeCol.setCellValueFactory(new PropertyValueFactory<>("returnTimeStamp"));

        TableColumn<Borrow, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Borrow, Integer> deletedCol = new TableColumn<>("Deleted");
        deletedCol.setCellValueFactory(new PropertyValueFactory<>("deleted"));


        borrowTbl.getColumns().addAll(idCol, memberCol, bookCol, borrowTimeCol, returnTimeCol, descriptionCol, deletedCol);
        borrowTbl.setItems(borrows);
    }
}
