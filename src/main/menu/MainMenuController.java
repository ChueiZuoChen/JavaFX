package main.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.Main;


import java.io.IOException;

public class MainMenuController {

    private Parent swapToPatientRegistration;
    private Stage mainStage;
    private Scene newScene;

    public void PatientRegistrationOnClicked(ActionEvent actionEvent) {

        try {
            swapToPatientRegistration = FXMLLoader.load(getClass().getResource("patientregistration/PatientRegistration.fxml"));
        } catch (IOException e) {
            System.out.println("Swap to PatientRegistration.fxml...");
        }
        mainStage = (Stage) ((Node)(actionEvent.getSource())).getScene().getWindow();
        newScene = new Scene(swapToPatientRegistration);
        mainStage.setScene(newScene);

        mainStage.show();
        System.out.println("Swap to PatientRegistration...");

    }

    public void ConfigurationOnClicked(ActionEvent actionEvent) {
        try {
            swapToPatientRegistration = FXMLLoader.load(getClass().getResource("configuration/Personalization.fxml"));
        } catch (IOException e) {
            System.out.println("Swap to Personalization.fxml...");
        }
        mainStage = (Stage) ((Node)(actionEvent.getSource())).getScene().getWindow();
        newScene = new Scene(swapToPatientRegistration);
        mainStage.setScene(newScene);
        mainStage.show();
        System.out.println("Swap to Configuration.fxml...");
    }

    public void LogoutOnclicked(ActionEvent actionEvent) {
        mainStage = (Stage) ((Node)(actionEvent.getSource())).getScene().getWindow();
        new Main().setFXML("../login/Login.fxml",mainStage);
        System.out.println("Logout...");
    }
}
