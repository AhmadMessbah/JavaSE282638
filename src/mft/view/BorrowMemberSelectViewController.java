package mft.view;

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
import mft.controller.FormType;
import mft.controller.MemberController;
import mft.model.entity.Member;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowMemberSelectViewController implements Initializable {

    @FXML
    private TextField idTxt, nameTxt, familyTxt, nationalCodeTxt, idShowTxt, nameShowTxt, familyShowTxt, codeShowTxt, fatherShowTxt, birthDateTxt, memberShipDateTxt, deletedShowTxt;

    @FXML
    private Button findByIdBtn, findByNameBtn, findByCodeBtn, selectBtn;

    @FXML
    private TableView<Member> memberSelectTbl;

    @FXML
    private Label userLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userLbl.setText("User : " + BaseController.user.getUserName());

        showDataOnTable(MemberController.findAll());

        memberSelectTbl.setOnMouseClicked((event) -> {
            Member member = memberSelectTbl.getSelectionModel().getSelectedItem();
            idShowTxt.setText(String.valueOf(member.getId()));
            nameShowTxt.setText(member.getName());
            familyShowTxt.setText(member.getFamily());
            fatherShowTxt.setText(member.getFather());
            codeShowTxt.setText(member.getNationalCode());
            birthDateTxt.setText(String.valueOf(member.getBirthDate()));
            memberShipDateTxt.setText(String.valueOf(member.getMemberShipDate()));
            deletedShowTxt.setText(String.valueOf(member.isDeleted()));
        });

        selectBtn.setOnAction(event -> {
            Member member =
                    Member
                            .builder()
                            .id(Integer.parseInt(idShowTxt.getText()))
                            .name(nameShowTxt.getText())
                            .family(familyShowTxt.getText())
                            .father(fatherShowTxt.getText())
                            .nationalCode(codeShowTxt.getText())
                            .birthDate(LocalDate.parse(birthDateTxt.getText()))
                            .memberShipDate(LocalDate.parse(memberShipDateTxt.getText()))
                            .deleted(Boolean.parseBoolean(deletedShowTxt.getText()))
                            .build();

            BaseController.member = member;

            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BorrowModifyView.fxml")));
                stage.setScene(scene);
                if (BaseController.formType.equals(FormType.newBorrow)){
                    stage.setTitle("New Borrow");
                } else if (BaseController.formType.equals(FormType.editBorrow)) {
                    stage.setTitle("Edit Borrow");
                }
                stage.show();
                selectBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        });

        findByIdBtn.setOnAction(event -> {
            Member member = MemberController.findById(Integer.parseInt(idTxt.getText()));
            List<Member> memberList = new ArrayList<>();
            memberList.add(member);
            showDataOnTable(memberList);
            resetForm();
        });

        findByNameBtn.setOnAction(event -> {
            Member member = MemberController.findByNameAndFamily(nameTxt.getText(), familyTxt.getText());
            List<Member> memberList = new ArrayList<>();
            memberList.add(member);
            showDataOnTable(memberList);
            resetForm();
        });

        findByCodeBtn.setOnAction(event -> {
            Member member = MemberController.findByNationalCode(nationalCodeTxt.getText());
            List<Member> memberList = new ArrayList<>();
            memberList.add(member);
            showDataOnTable(memberList);
            resetForm();
        });

    }

    public void resetForm(){
        idTxt.clear();
        nameTxt.clear();
        familyTxt.clear();
        nationalCodeTxt.clear();
    }

    public void showDataOnTable(List<Member> memberList) {
        memberSelectTbl.getColumns().clear();
        ObservableList<Member> members = FXCollections.observableList(memberList);

        TableColumn<Member, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Member, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Member, String> familyCol = new TableColumn<>("Family");
        familyCol.setCellValueFactory(new PropertyValueFactory<>("family"));

        TableColumn<Member, String> fatherCol = new TableColumn<>("Father");
        fatherCol.setCellValueFactory(new PropertyValueFactory<>("father"));

        TableColumn<Member, String> nationalCodeCol = new TableColumn<>("National Code");
        nationalCodeCol.setCellValueFactory(new PropertyValueFactory<>("nationalCode"));

        TableColumn<Member, LocalDate> birthDateCol = new TableColumn<>("Birthdate");
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        TableColumn<Member, LocalDate> memberShipDateCol = new TableColumn<>("Membership Date");
        memberShipDateCol.setCellValueFactory(new PropertyValueFactory<>("memberShipDate"));

        TableColumn<Member, Integer> deletedCol = new TableColumn<>("Deleted");
        deletedCol.setCellValueFactory(new PropertyValueFactory<>("deleted"));

        memberSelectTbl.getColumns().addAll(idCol, nameCol, familyCol, fatherCol, nationalCodeCol, birthDateCol, memberShipDateCol,deletedCol);
        memberSelectTbl.setItems(members);
    }
}
