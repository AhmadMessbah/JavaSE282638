package mft.view2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mft.controller.BaseController;
import mft.controller.FormType;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        BaseController.formType = FormType.SelectBook;
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ImageView.fxml")));
        primaryStage.setScene(scene);
//        primaryStage.setTitle( BaseController.formType.toString());
        primaryStage.show();
    }
}
