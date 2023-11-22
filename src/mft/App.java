package mft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mft.controller.MemberController;
import mft.controller.UserController;
import mft.model.bl.MemberBl;
import mft.model.entity.Member;

import java.time.LocalDate;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        Member member = MemberBl.save(Member.builder().name("admin").family("admin").birthDate(LocalDate.now()) .build());
//        UserController.save(member.getId(), "admin","admin","admin",null);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("view/LoginView.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}
