package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mft.model.entity.Member;
import java.net.URL;

import java.util.ResourceBundle;

public class MemberViewController implements Initializable {
    @FXML
    private TextField idTxt,nameTxt,familyTxt;
    @FXML
    private Button saveBtn,editeBtn,removeBtn;
    @FXML
    private TableView<Member> table;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveBtn.setOnAction(event -> {
            Member member=
                    Member
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .name(nameTxt.getText())
                            .family(familyTxt.getText())
                            .build();
            Alert alert=new Alert(Alert.AlertType.INFORMATION,
                    member.toString()+" Saved",
                    ButtonType.OK);
            alert.show();
        });
        editeBtn.setOnAction(event -> {
            Member member=
                    Member
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .name(nameTxt.getText())
                            .family(familyTxt.getText())
                            .build();
            Alert alert=new Alert(Alert.AlertType.INFORMATION,
                    member.toString()+" Edited",
                    ButtonType.OK);
            alert.show();
        });
        removeBtn.setOnAction(event -> {
            Member member=
                    Member
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .build();
            Alert alert=new Alert(Alert.AlertType.INFORMATION,
                    member.toString()+" Removed",
                    ButtonType.OK);
            alert.show();
        });
    }
}