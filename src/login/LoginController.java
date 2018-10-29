package login;

import data.JDBCCheckOutData;
import data.UsersData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {

    @FXML
    TextField inputAccountName;

    @FXML
    TextField inputPassword;
    private Parent swapToMainMenu;
    private Stage mainStage;
    private Scene newScene;
    private boolean loginStatment;

    public void LoginButtonOnClicked(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        String inputAc = inputAccountName.getText();
        String inputPw = inputPassword.getText();

//        System.out.println(inputAccountName.getText());
////        System.out.println(inputPassword.getText());

        JDBCCheckOutData jdbcCheckOutData = new JDBCCheckOutData();
        ArrayList<UsersData> usersDatas = jdbcCheckOutData.UsersAllData();

//        for (UsersData usersData : usersDatas) {
//            System.out.println(usersData.getUserName());
//        }

        loginStatment = false;
        for (int i = 0; i < usersDatas.size(); i++) {

            if (inputAc.equals(usersDatas.get(i).getUserName()) && inputPw.equals(usersDatas.get(i).getUserPassword())) {
                loginStatment = true;
            }
        }
        if (loginStatment) {
            ValidLogin(actionEvent, inputAc, inputPw);
        } else {
            System.out.println("Login failed...");
            final Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Can't found Username or Password");
            alert.setContentText("Please retry.");
            alert.showAndWait();

        }

    }

    private void ValidLogin(ActionEvent actionEvent, String inputAc, String inputPw) throws IOException {
        swapToMainMenu = FXMLLoader.load(getClass().getResource("../main/menu/MainMenu.fxml"));
        mainStage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        newScene = new Scene(swapToMainMenu);
        mainStage.setScene(newScene);
        mainStage.show();
        System.out.println("Login Successed...\nUser: " + inputAc + "\nPassword: " + inputPw);
        return;
    }

    public void QuitButtonOnClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        stage.close();
        System.out.println("Quit Program...");
    }
}
