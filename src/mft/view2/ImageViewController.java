package mft.view2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class ImageViewController implements Initializable {
    @FXML
    private ImageView imageViewer;

    @FXML
    private Button openBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openBtn.setOnAction((event) -> {
            try {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Open File");
                chooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files",
                                "*.bmp", "*.png", "*.jpg", "*.gif"));
                File file = chooser.showOpenDialog(new Stage());

                Path copied = Paths.get("src/mft/view2/img/" + file.getName());
                Path originalPath = file.toPath();
                Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);

                if (file != null) {
                    String imagePath = file.toURI().toURL().toString();
                    Image image = new Image(imagePath);
                    imageViewer.setImage(image);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Please Select a File");
                    /*alert.setContentText("You didn't select a file!");*/
                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.out.println("ERROR : " + e.getMessage());
            }
        });
    }
}
