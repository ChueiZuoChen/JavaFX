package login;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {

    private Parent root;

    @Override
    public void start(Stage primaryStage) {
        setFXML("Login.fxml", primaryStage);
//        primaryStage.setScene(new Scene(root, 450, 300));
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    public void setFXML(String fxmlResource, Stage stage) {
        try {

            root = FXMLLoader.load(getClass().getResource(fxmlResource));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
