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
import mft.controller.BookController;
import mft.controller.FormType;
import mft.model.entity.Book;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowBookSelectViewController implements Initializable {

    @FXML
    private TextField idTxt, nameTxt, authorTxt, publisherTxt, idShowTxt, languageShowTxt, nameShowTxt, genreShowTxt, authorShowTxt, isbnShowTxt, publisherShowTxt, pagesShowTxt, deletedShowTxt;

    @FXML
    private TextArea  descriptionShowTxt;

    @FXML
    private Button findByNameBtn, findByIdBtn, findByAuthorBtn, findByPublisherBtn, selectBtn;

    @FXML
    private TableView<Book> bookSelectTbl;

    @FXML
    private Label userLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userLbl.setText(BaseController.user.getUserName());

        showDataOnTable(BookController.findAll());

        bookSelectTbl.setOnMouseClicked((event) -> {
            Book book = bookSelectTbl.getSelectionModel().getSelectedItem();
            idShowTxt.setText(String.valueOf(book.getId()));
            nameShowTxt.setText(book.getName());
            authorShowTxt.setText(book.getAuthor());
            publisherShowTxt.setText(book.getPublisher());
            languageShowTxt.setText(book.getLanguage());
            genreShowTxt.setText(book.getGenre());
            isbnShowTxt.setText(book.getIsbn());
            descriptionShowTxt.setText(book.getDescription());
            pagesShowTxt.setText(String.valueOf(book.getPages()));
            deletedShowTxt.setText(String.valueOf(book.isDeleted()));
        });

        selectBtn.setOnAction(event -> {
            String description = null;
            if (descriptionShowTxt.getText() != null){
                description = descriptionShowTxt.getText();
            }
            Book book =
                    Book
                            .builder()
                            .id(Integer.parseInt(idShowTxt.getText()))
                            .name(nameShowTxt.getText())
                            .author(authorShowTxt.getText())
                            .pages(Integer.parseInt(pagesShowTxt.getText()))
                            .publisher(publisherShowTxt.getText())
                            .language(languageShowTxt.getText())
                            .genre(genreShowTxt.getText())
                            .isbn(isbnShowTxt.getText())
                            .description(description)
                            .deleted(Boolean.parseBoolean(deletedShowTxt.getText()))
                            .build();

            BaseController.book = book;

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
            Book book = BookController.findById(Integer.parseInt(idTxt.getText()));
            List<Book> bookList = new ArrayList<>();
            bookList.add(book);
            showDataOnTable(bookList);
            resetForm();
        });

        findByNameBtn.setOnAction(event -> {
            Book book = BookController.findByName(nameTxt.getText());
            List<Book> bookList = new ArrayList<>();
            bookList.add(book);
            showDataOnTable(bookList);
            resetForm();
        });

        findByAuthorBtn.setOnAction(event -> {
            Book book = BookController.findByAuthor(authorTxt.getText());
            List<Book> bookList = new ArrayList<>();
            bookList.add(book);
            showDataOnTable(bookList);
            resetForm();
        });

        findByPublisherBtn.setOnAction(event -> {
            List<Book> bookList = BookController.findByPublisher(publisherTxt.getText());
            showDataOnTable(bookList);
            resetForm();
        });

    }

    public void resetForm(){
        idTxt.clear();
        nameTxt.clear();
        authorTxt.clear();
        publisherTxt.clear();
    }

    public void showDataOnTable(List<Book> bookList){
        bookSelectTbl.getColumns().clear();
        ObservableList<Book> books = FXCollections.observableList(bookList);

        TableColumn<Book, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, Integer> pagesCol = new TableColumn<>("Pages");
        pagesCol.setCellValueFactory(new PropertyValueFactory<>("pages"));

        TableColumn<Book, String> publisherCol = new TableColumn<>("Publisher");
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        TableColumn<Book, String> languageCol = new TableColumn<>("Language");
        languageCol.setCellValueFactory(new PropertyValueFactory<>("language"));

        TableColumn<Book, String> genreCol = new TableColumn<>("Genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<Book, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Book, Boolean> deletedCol = new TableColumn<>("Deleted");
        deletedCol.setCellValueFactory(new PropertyValueFactory<>("deleted"));

        bookSelectTbl.getColumns().addAll(idCol, nameCol, authorCol, pagesCol, publisherCol, languageCol, genreCol, isbnCol, descriptionCol, deletedCol);
        bookSelectTbl.setItems(books);
    }

}
