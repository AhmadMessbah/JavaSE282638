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
import java.util.List;

import java.util.ResourceBundle;

public class MemberViewController implements Initializable {
    @FXML
    public TableColumn idCol, nameCol, familyCol;
    @FXML
    private TextField idTxt, nameTxt, familyTxt;
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
        });

        saveBtn.setOnAction(event -> {
            Member member = MemberController.save(nameTxt.getText(),familyTxt.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, member.toString() + " Saved", ButtonType.OK);
            alert.show();
            resetForm();
        });
        editBtn.setOnAction(event -> {
            Member member = Member.builder().id(Integer.parseInt(idTxt.getText())).name(nameTxt.getText()).family(familyTxt.getText()).build();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, member.toString() + " Edited", ButtonType.OK);
            alert.show();
            resetForm();
        });
        removeBtn.setOnAction(event -> {
            Member member = Member.builder().id(Integer.parseInt(idTxt.getText())).build();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, member.toString() + " Removed", ButtonType.OK);
            alert.show();
        });
    }

    public void resetForm() {
        try {
            idTxt.clear();
            nameTxt.clear();
            familyTxt.clear();
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


        table.getColumns().addAll(idCol,nameCol,familyCol);
        table.setItems(members);
    }
}
