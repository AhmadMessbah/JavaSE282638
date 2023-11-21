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
import javafx.stage.Stage;
import mft.controller.BaseController;
import mft.controller.BorrowController;
import mft.controller.FormType;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Member;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class BorrowModifyViewController implements Initializable {

    @FXML
    private TextField idTxt, memberNameTxt, bookNameTxt, memberIdTxt, bookIdTxt, finalMemberIdTxt, finalBookIdTxt, finalMemberNameTxt, finalBookNameTxt;

    @FXML
    private DatePicker borrowDate, returnDate;

    @FXML
    private Button saveBtn, backBtn, exitBtn, memberBtn, bookBtn, findIdBtn, memberSetBtn, bookSetBtn;

    @FXML
    private TableView<Borrow> borrowTbl;

    @FXML
    private TextArea descriptionTxt;

    @FXML
    private ComboBox<String> deletedCmb;

    @FXML
    private ComboBox<Integer> borrowMinCmb, borrowHourCmb, returnHourCmb, returnMinCmb;

    @FXML
    private Label userLbl, idLbl, borrowDateLbl, returnDateLbl, deletedLbl, descriptionLbl, borrowHourLbl, returnHourLbl, borrowMinLbl, returnMinLbl, selectMemberBookLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        resetForm();
        borrowTbl.getColumns().clear();

        userLbl.setText("User : " + BaseController.user.getUserName());

        deletedCmb.getItems().addAll("Yes", "No");

        for (int i = 0; i <= 23; i++) {
            borrowHourCmb.getItems().add(i);
            returnHourCmb.getItems().add(i);
        }

        for (int i = 0; i <= 59; i++) {
            borrowMinCmb.getItems().add(i);
            returnMinCmb.getItems().add(i);
        }

        backBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Borrow");
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

        findIdBtn.setOnAction(event -> {
            Map<String, Borrow> result = BorrowController.findById(Integer.parseInt(idTxt.getText()));
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

        memberBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowMemberSelectView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Member Select");
                stage.show();
                memberBtn.getScene().getWindow().hide();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        bookBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowBookSelectView.fxml")));
                stage.setScene(scene);
                stage.setTitle("Book Select");
                stage.show();
                bookBtn.getScene().getWindow().hide();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        memberSetBtn.setOnAction(event -> {
            finalMemberIdTxt.setText(memberIdTxt.getText());
            finalMemberNameTxt.setText(memberNameTxt.getText());
        });

        bookSetBtn.setOnAction(event -> {
            finalBookIdTxt.setText(bookIdTxt.getText());
            finalBookNameTxt.setText(bookNameTxt.getText());
        });

        if (BaseController.formType.equals(FormType.newBorrow)) {

            if (BaseController.member != null){
                memberIdTxt.setText(String.valueOf(BaseController.member.getId()));
                memberNameTxt.setText(BaseController.member.getName()+" "+BaseController.member.getFamily());
            }

            if (BaseController.book != null){
                bookIdTxt.setText(String.valueOf(BaseController.book.getId()));
                bookNameTxt.setText(BaseController.book.getName());
            }

            saveBtn.setOnAction(event -> {
                Map<String, String> result = BorrowController.save(Integer.parseInt(memberIdTxt.getText()), Integer.parseInt(bookIdTxt.getText()), descriptionTxt.getText());
                if (result.get("status").equals("true")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
                    alert.show();
                    showDataOnTable(BorrowController.findAll().get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
                    alert.show();
                }
                resetForm();
            });
            idTxt.setVisible(false);
            deletedCmb.setVisible(false);
            borrowDate.setVisible(false);
            returnDate.setVisible(false);
            borrowHourCmb.setVisible(false);
            borrowMinCmb.setVisible(false);
            returnHourCmb.setVisible(false);
            returnMinCmb.setVisible(false);
            memberNameTxt.setEditable(false);
            bookNameTxt.setEditable(false);
            idLbl.setVisible(false);
            deletedLbl.setVisible(false);
            borrowDateLbl.setVisible(false);
            returnDateLbl.setVisible(false);
            borrowHourLbl.setVisible(false);
            borrowMinLbl.setVisible(false);
            returnHourLbl.setVisible(false);
            returnMinLbl.setVisible(false);
            findIdBtn.setVisible(false);
            memberSetBtn.setVisible(false);
            bookSetBtn.setVisible(false);
            finalBookIdTxt.setVisible(false);
            finalMemberIdTxt.setVisible(false);
            finalBookNameTxt.setVisible(false);
            finalMemberNameTxt.setVisible(false);
            selectMemberBookLbl.setVisible(false);
        } else if (BaseController.formType.equals(FormType.editBorrow)) {

            saveBtn.setText("Edit");

            if (BaseController.member != null){
                memberIdTxt.setText(String.valueOf(BaseController.member.getId()));
                memberNameTxt.setText(BaseController.member.getName()+" "+BaseController.member.getFamily());
            }

            if (BaseController.book != null){
                bookIdTxt.setText(String.valueOf(BaseController.book.getId()));
                bookNameTxt.setText(BaseController.book.getName());
            }

            showDataOnTable(BorrowController.findAll().get("ok"));
            setOnTableByMouseClick();
            saveBtn.setOnAction(event -> {
                boolean deleted = deletedCmb.getSelectionModel().getSelectedItem().equals("Yes");

                LocalDateTime returnTime = null;
                if (returnDate.getValue() != null){
                    returnTime = returnDate.getValue().atTime(returnHourCmb.getSelectionModel().getSelectedItem(), returnMinCmb.getSelectionModel().getSelectedItem());
                }

                Map<String, String> result = BorrowController.edit(Integer.parseInt(idTxt.getText()), Integer.parseInt(finalMemberIdTxt.getText()), Integer.parseInt(finalBookIdTxt.getText()), borrowDate.getValue().atTime(borrowHourCmb.getSelectionModel().getSelectedItem(), borrowMinCmb.getSelectionModel().getSelectedItem()), returnTime, descriptionTxt.getText(), deleted);
                if (result.get("status").equals("true")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
                    alert.show();
                    showDataOnTable(BorrowController.findAll().get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
                    alert.show();
                }
                resetForm();
            });
        } else if (BaseController.formType.equals(FormType.returnBorrow)) {
            saveBtn.setText("Return");
            showDataOnTable(BorrowController.findByReturnStatus(false).get("ok"));
            setOnTableByMouseClick();
            saveBtn.setOnAction(event -> {
                Map<String, String> result = BorrowController.returnBook(Integer.parseInt(idTxt.getText()));
                if (result.get("status").equals("true")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
                    alert.show();
                    showDataOnTable(BorrowController.findByReturnStatus(false).get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
                    alert.show();
                }
                resetForm();
            });
            deletedCmb.setVisible(false);
            borrowDate.setVisible(false);
            returnDate.setVisible(false);
            borrowHourCmb.setVisible(false);
            returnHourCmb.setVisible(false);
            borrowMinCmb.setVisible(false);
            returnMinCmb.setVisible(false);
            memberNameTxt.setVisible(false);
            bookNameTxt.setVisible(false);
            deletedLbl.setVisible(false);
            borrowDateLbl.setVisible(false);
            returnDateLbl.setVisible(false);
            borrowHourLbl.setVisible(false);
            returnHourLbl.setVisible(false);
            borrowMinLbl.setVisible(false);
            returnMinLbl.setVisible(false);
            memberBtn.setVisible(false);
            bookBtn.setVisible(false);
            descriptionLbl.setVisible(false);
            descriptionTxt.setVisible(false);
            memberIdTxt.setVisible(false);
            bookIdTxt.setVisible(false);
            memberSetBtn.setVisible(false);
            bookSetBtn.setVisible(false);
            finalBookIdTxt.setVisible(false);
            finalMemberIdTxt.setVisible(false);
            finalBookNameTxt.setVisible(false);
            finalMemberNameTxt.setVisible(false);
            selectMemberBookLbl.setVisible(false);
        } else if (BaseController.formType.equals(FormType.removeBorrow)) {
            saveBtn.setText("Remove");
            showDataOnTable(BorrowController.findByDeletedStatus(false).get("ok"));
            setOnTableByMouseClick();
            saveBtn.setOnAction(event -> {
                Map<String, String> result = BorrowController.remove(Integer.parseInt(idTxt.getText()));
                if (result.get("status").equals("true")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
                    alert.show();
                    showDataOnTable(BorrowController.findByDeletedStatus(false).get("ok"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
                    alert.show();
                }
                resetForm();
            });
            deletedCmb.setVisible(false);
            borrowDate.setVisible(false);
            returnDate.setVisible(false);
            borrowHourCmb.setVisible(false);
            borrowMinCmb.setVisible(false);
            returnHourCmb.setVisible(false);
            returnMinCmb.setVisible(false);
            memberNameTxt.setVisible(false);
            bookNameTxt.setVisible(false);
            deletedLbl.setVisible(false);
            borrowDateLbl.setVisible(false);
            returnDateLbl.setVisible(false);
            borrowHourLbl.setVisible(false);
            borrowMinLbl.setVisible(false);
            returnHourLbl.setVisible(false);
            returnMinLbl.setVisible(false);
            memberBtn.setVisible(false);
            bookBtn.setVisible(false);
            descriptionLbl.setVisible(false);
            descriptionTxt.setVisible(false);
            memberIdTxt.setVisible(false);
            bookIdTxt.setVisible(false);
            memberSetBtn.setVisible(false);
            bookSetBtn.setVisible(false);
            finalBookIdTxt.setVisible(false);
            finalMemberIdTxt.setVisible(false);
            finalBookNameTxt.setVisible(false);
            finalMemberNameTxt.setVisible(false);
            selectMemberBookLbl.setVisible(false);
        }


//        saveBtn.setOnAction(event -> {
//            Map<String, String> result = BorrowController.save(Integer.parseInt(memberIdTxt.getText()), Integer.parseInt(bookIdTxt.getText()), descriptionTxt.getText());
//            if (result.get("status").equals("true")) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
//                alert.show();
//                resetForm();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
//                alert.show();
//            }
//        });
//
//        editBtn.setOnAction(event -> {
//            boolean deleted = deletedCmb.getSelectionModel().getSelectedItem().equals("Yes");
//            Map<String, String> result = BorrowController.edit(Integer.parseInt(idTxt.getText()), Integer.parseInt(memberIdTxt.getText()), Integer.parseInt(bookIdTxt.getText()), borrowDate.getValue().atTime(8, 15), returnDate.getValue().atTime(8, 15), descriptionTxt.getText(), deleted);
//            if (result.get("status").equals("true")) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
//                alert.show();
//                resetForm();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
//                alert.show();
//            }
//        });
//
//        returnBtn.setOnAction(event -> {
//            Map<String, String> result = BorrowController.returnBook(Integer.parseInt(idTxt.getText()));
//            if (result.get("status").equals("true")) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
//                alert.show();
//                resetForm();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
//                alert.show();
//            }
//        });
//
//        removeBtn.setOnAction(event -> {
//            Map<String, String> result = BorrowController.remove(Integer.parseInt(idTxt.getText()));
//            if (result.get("status").equals("true")) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
//                alert.show();
//                resetForm();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
//                alert.show();
//            }
//        });
//

    }

    public void setOnTableByMouseClick(){
        borrowTbl.setOnMouseClicked((event) -> {
            Borrow borrow = borrowTbl.getSelectionModel().getSelectedItem();
            LocalDate returnTime =null;
            Integer returnHour = null;
            Integer returnMin = null;
            if (borrow.getReturnTimeStamp() != null){
                 returnTime = borrow.getReturnTimeStamp().toLocalDate();
                 returnHour = borrow.getReturnTimeStamp().getHour();
                 returnMin = borrow.getReturnTimeStamp().getMinute();
            }
            String deleted = "No";
            if (borrow.isDeleted()){
                deleted = "Yes";
            }
            idTxt.setText(String.valueOf(borrow.getId()));
            finalMemberIdTxt.setText(String.valueOf(borrow.getMember().getId()));
            finalMemberNameTxt.setText(borrow.getMember().getName()+" "+borrow.getMember().getFamily());
            finalBookNameTxt.setText(borrow.getBook().getName());
            finalBookIdTxt.setText(String.valueOf(borrow.getBook().getId()));
            borrowDate.setValue(borrow.getBorrowTimeStamp().toLocalDate());
            returnDate.setValue(returnTime);
            borrowHourCmb.setValue(borrow.getBorrowTimeStamp().getHour());
            borrowMinCmb.setValue(borrow.getBorrowTimeStamp().getMinute());
            returnHourCmb.setValue(returnHour);
            returnMinCmb.setValue(returnMin);
            deletedCmb.setValue(deleted);
            descriptionTxt.setText(borrow.getDescription());
        });
    }

    public void resetForm() {
        try {
            idTxt.clear();
            memberIdTxt.clear();
            bookIdTxt.clear();
            memberNameTxt.clear();
            bookNameTxt.clear();
            borrowDate.setValue(null);
            returnDate.setValue(null);
            borrowHourCmb.getSelectionModel().clearSelection();
            borrowMinCmb.getSelectionModel().clearSelection();
            returnHourCmb.getSelectionModel().clearSelection();
            returnMinCmb.getSelectionModel().clearSelection();
            deletedCmb.getSelectionModel().clearSelection();
            descriptionTxt.clear();
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
