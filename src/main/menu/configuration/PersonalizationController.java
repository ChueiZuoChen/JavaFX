package main.menu.configuration;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import login.Main;

import java.awt.*;

public class PersonalizationController {

    private Stage mainStage;

    public void BackToMainMenu(ActionEvent actionEvent) {
        mainStage = (Stage) ((Node)(actionEvent.getSource())).getScene().getWindow();
        new Main().setFXML("../main/menu/MainMenu.fxml", mainStage);
        System.out.println("Back to MainMenu");
    }


}
