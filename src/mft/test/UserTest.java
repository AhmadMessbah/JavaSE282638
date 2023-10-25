package mft.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mft.controller.UserController;
import mft.model.bl.UserBl;


public class UserTest extends Application {
//    public static void main(String[] args) throws Exception {
//        System.out.println(UserBl.findAll());
////        UserController.save("aaa","bbb");
////        launch();
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/UserView.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("User");
        primaryStage.show();
    }
}
