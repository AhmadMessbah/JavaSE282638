package mft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.controller.MemberController;
import mft.model.entity.Member;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;

public class MemberViewController implements Initializable {
    @FXML
    public TableColumn idCol, nameCol, familyCol, fatherCol, nationalCol, birthDateCol, memberShipDateCol;
    @FXML
    private TextField idTxt, nameTxt, familyTxt, fatherTxt, nationalCodeTxt, birthDateTxt, memberShipDateTxt;
    @FXML
    private Button saveBtn, editBtn, removeBtn;
    @FXML
    private TableView<Member> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();
        table.setOnMouseClicked((event) -> {
            Member member = table.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(member.getId()));
            nameTxt.setText(String.valueOf(member.getName()));
            familyTxt.setText(String.valueOf(member.getFamily()));
            fatherTxt.setText(String.valueOf(member.getFather()));
            nationalCodeTxt.setText(String.valueOf(member.getNationalCode()));
            birthDateTxt.setText(Date.valueOf(member.getBirthDate()));
            memberShipDateTxt.setText(Date.valueOf(member.getBirthDate()));
        });

//         todo : الگو
        saveBtn.setOnAction(event -> {
            Map<String, String> result = MemberController.save(nameTxt.getText(), familyTxt.getText(), fatherTxt.getText(),nationalCodeTxt.getText(), birthDateTxt.getText(), memberShipDateTxt.getText());
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
            Map<String, String> result = MemberController.edit(Integer.parseInt(idTxt.getText()),nameTxt.getText(),familyTxt.getText(), fatherTxt.getText(),nationalCodeTxt.getText(), birthDateTxt.getText(), memberShipDateTxt.getText());
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
            Map<String, String> result = MemberController.remove(Integer.parseInt(idTxt.getText()));
            if (result.get("status").equals("true")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
                alert.show();
                resetForm();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
                alert.show();
    }

    public void resetForm() {
        try {
            idTxt.clear();
            nameTxt.clear();
            familyTxt.clear();
            fatherTxt.clear();
            nationalCodeTxt.clear();
            birthDateTxt.clear();
            memberShipDateTxt.clear();
            showDataOnTable(MemberController.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void showDataOnTable(List<Member> memberList) {
        table.getColumns().clear();
        ObservableList<Member> members = FXCollections.observableList(memberList);

        TableColumn<Member, String> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Member, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Member, String> familyCol = new TableColumn<>("Family");
        familyCol.setCellValueFactory(new PropertyValueFactory<>("family"));

        TableColumn<Member, String> fatherCol = new TableColumn<>("Father");
        fatherCol.setCellValueFactory(new PropertyValueFactory<>("father"));

        TableColumn<Member, String> nationalCol = new TableColumn<>("NationalCode");
        nationalCol.setCellValueFactory(new PropertyValueFactory<>("nationalCode"));

        TableColumn<Member, String> birthDateCol = new TableColumn<>("BirthDate");
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        TableColumn<Member, String> memberShipDateCol = new TableColumn<>("MemberShipDate");
        memberShipDateCol.setCellValueFactory(new PropertyValueFactory<>("memberShipDate"));

        table.getColumns().addAll(idCol, nameCol, familyCol, fatherCol, nationalCol, birthDateCol, memberShipDateCol);
        table.setItems(members);
    }
}
