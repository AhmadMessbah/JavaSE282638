package mft.view2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import mft.controller.BaseController;
import mft.controller.BookController;
import mft.model.entity.Book;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BookViewController implements Initializable {
    @FXML
    private Pane pane;

    @FXML
    private TextField idTxt, nameTxt, authorTxt, pagesTxt, publisherTxt, languageTxt, genreTxt, isbnTxt, descriptionTxt;
    @FXML
    private Button saveBtn, editBtn, removeBtn, clearBtn;
    @FXML
    private TableView<Book> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        switch (BaseController.formType.name()) {
            case "SaveBook":
                pane.setPrefWidth(330);
                editBtn.setVisible(false);
                removeBtn.setVisible(false);
                table.setVisible(false);
                break;
            case "EditBook":
                pane.prefWidth(330);
                saveBtn.setVisible(false);
                removeBtn.setVisible(false);
                break;
            case "RemoveBook":
                pane.prefWidth(330);
                saveBtn.setVisible(false);
                editBtn.setVisible(false);
                break;
            case "SelectBook":
                table.setLayoutX(15);
                table.setLayoutY(200);
                table.setPrefHeight(440);
                clearBtn.setLayoutY(480);
                pane.setPrefWidth(715);
                clearBtn.setText("SELECT BOOK");
                nameTxt.setOnKeyReleased((event)->{
                    showDataOnTable(BookController.findByName(nameTxt.getText()));
                });
                break;
        }

        resetForm();

        clearBtn.setOnAction((event) -> {
            if (clearBtn.getText().equals("SELECT BOOK")) {
                Book book = table.getSelectionModel().getSelectedItem();
                if (book != null) {
                    selectTableRow(book);
                    BaseController.book = book;
                }
            } else {
                resetForm();
            }
        });

        table.setOnKeyReleased((event) -> {
            if (event.getCode().equals(KeyCode.UP) || event.getCode().equals(KeyCode.DOWN)) {
                Book book = table.getSelectionModel().getSelectedItem();
                if (book != null) {
                    selectTableRow(book);
                }
            }
        });

        table.setOnMouseClicked((event) -> {
//            if (event.isPrimaryButtonDown()) {   todo : doesn't work
            Book book = table.getSelectionModel().getSelectedItem();
            if (book != null) {
                selectTableRow(book);
            }
//            }
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
        editBtn.setOnAction(event -> {
            Map<String, String> result = BookController.edit(Integer.parseInt(idTxt.getText()), nameTxt.getText(), authorTxt.getText(), Integer.parseInt(pagesTxt.getText()), publisherTxt.getText(), languageTxt.getText(), genreTxt.getText(), isbnTxt.getText(), descriptionTxt.getText());
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
            nameTxt.requestFocus();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void selectTableRow(Book book) {
        idTxt.setText(String.valueOf(book.getId()));
        nameTxt.setText(String.valueOf(book.getName()));
        authorTxt.setText(String.valueOf(book.getAuthor()));
        pagesTxt.setText(String.valueOf(book.getPages()));
        publisherTxt.setText(String.valueOf(book.getPublisher()));
        languageTxt.setText(String.valueOf(book.getLanguage()));
        genreTxt.setText(String.valueOf(book.getGenre()));
        isbnTxt.setText(String.valueOf(book.getIsbn()));
        descriptionTxt.setText(String.valueOf(book.getDescription()));
    }

    public void showDataOnTable(List<Book> bookList) {
        table.getColumns().clear();
        try {
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
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "There is no book");
            alert.show();
        }
    }
}
