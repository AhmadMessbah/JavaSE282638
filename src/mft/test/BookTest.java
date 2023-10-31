package mft.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mft.model.entity.Book;

public class BookTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene=new Scene(FXMLLoader.load(getClass().getResource("../view/BookView.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book");
        primaryStage.show();
    }
}
