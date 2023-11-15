package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mft.model.entity.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BookViewController implements Initializable {
    @FXML
    private TextField idTxt,nameTxt,authorTxt;
    @FXML
    private Button saveBtn,editeBtn,removeBtn;
    @FXML
    private TableView<Book> table;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveBtn.setOnAction(event -> {
            Book book=
                    Book
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .name(nameTxt.getText())
                            .author(authorTxt.getText())
                            .build();
            Alert alert=new Alert(Alert.AlertType.INFORMATION,
                    book.toString()+" Saved",
                    ButtonType.OK);
            alert.show();
        });
        editeBtn.setOnAction(event -> {
            Book book=
                    Book
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .name(nameTxt.getText())
                            .author(authorTxt.getText())
                            .build();
            Alert alert=new Alert(Alert.AlertType.INFORMATION,
                    book.toString()+" Edited",
                    ButtonType.OK);
            alert.show();
        });
        removeBtn.setOnAction(event -> {
            Book book=
                    Book
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .build();
            Alert alert=new Alert(Alert.AlertType.INFORMATION,
                    book.toString()+" Removed",
                    ButtonType.OK);
            alert.show();
        });
        table.setOnMouseClicked((event) -> {
            Book book=table.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(book.getId()));
            nameTxt.setText(String.valueOf(book.getName()));
            authorTxt.setText(String.valueOf(book.getAuthor()));
        });
    }
}
