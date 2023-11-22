package mft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.controller.BookController;
import mft.model.entity.Book;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BookViewController implements Initializable {
    public TableColumn idCol,  nameCol, authorCol, pagesCol, publisherCol, languageCol, genreCol, isbnCol, descriptionCol;
    @FXML
    private TextField idTxt, nameTxt, authorTxt, pagesTxt, publisherTxt, languageTxt, genreTxt, isbnTxt, descriptionTxt;
    @FXML
    private Button saveBtn, editeBtn, removeBtn;
    @FXML
    private TableView<Book> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();
        table.setOnMouseClicked((event) -> {
            Book book = table.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(book.getId()));
            nameTxt.setText(String.valueOf(book.getName()));
            authorTxt.setText(String.valueOf(book.getAuthor()));
            pagesTxt.setText(String.valueOf(book.getPages()));
            publisherTxt.setText(String.valueOf(book.getPublisher()));
            languageTxt.setText(String.valueOf(book.getLanguage()));
            genreTxt.setText(String.valueOf(book.getGenre()));
            isbnTxt.setText(String.valueOf(book.getIsbn()));
            descriptionTxt.setText(String.valueOf(book.getDescription()));
        });
        saveBtn.setOnAction(event -> {
            Map<String, String> result = BookController.save(nameTxt.getText(), authorTxt.getText(), Integer.parseInt(pagesTxt.getText()), publisherTxt.getText(), languageTxt.getText(), genreTxt.getText(), isbnTxt.getText(), descriptionTxt.getText());
            if (result.get("status").equals("true")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
                alert.show();
                resetForm();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
                alert.show();
            }
        });
        editeBtn.setOnAction(event -> {
            Map<String, String> result = BookController.edit(nameTxt.getText(), authorTxt.getText(), Integer.parseInt(pagesTxt.getText()), publisherTxt.getText(), languageTxt.getText(), genreTxt.getText(), isbnTxt.getText(), descriptionTxt.getText());
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
            Map<String, String> result = BookController.remove(Integer.parseInt(idTxt.getText()));
            if (result.get("status").equals("true")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, result.get("message"), ButtonType.OK);
                alert.show();
                resetForm();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, result.get("message"), ButtonType.CANCEL);
                alert.show();
            }
        });

    }

    public void resetForm() {
        try {
            idTxt.clear();
            nameTxt.clear();
            authorTxt.clear();
            pagesTxt.clear();
            publisherTxt.clear();
            languageTxt.clear();
            genreTxt.clear();
            isbnTxt.clear();
            descriptionTxt.clear();
            showDataOnTable(BookController.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void showDataOnTable(List<Book> bookList) {
        table.getColumns().clear();
        ObservableList<Book> books = FXCollections.observableList(bookList);

        TableColumn<Book, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Book, String> authorCol = new TableColumn<>("AUTHOR");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> pagesCol = new TableColumn<>("PAGES");
        pagesCol.setCellValueFactory(new PropertyValueFactory<>("pages"));

        TableColumn<Book, String> publisherCol = new TableColumn<>("PUBLISHER");
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        TableColumn<Book, String> languageCol = new TableColumn<>("LANGUAGE");
        languageCol.setCellValueFactory(new PropertyValueFactory<>("language"));

        TableColumn<Book, String> genreCol = new TableColumn<>("GENRE");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<Book, String> descriptionCol = new TableColumn<>("DESCRIPTION");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.getColumns().addAll(idCol, nameCol, authorCol, pagesCol, publisherCol, languageCol, genreCol, isbnCol, descriptionCol);
        table.setItems(books);
    }
}
