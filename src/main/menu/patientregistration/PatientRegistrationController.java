package main.menu.patientregistration;

import data.JDBCCheckOutData;
import data.PatientData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import login.Main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class PatientRegistrationController {

    private Stage mainStage;
    private Map<String, Integer> map;
    private String[] errorMessageArray;
    @FXML
    TableView<PatientData> table;
    @FXML
    TableColumn<PatientData, String> col_name;
    @FXML
    TableColumn<PatientData, String> col_id;
    @FXML
    TableColumn<PatientData, String> col_surname;
    @FXML
    TableColumn<PatientData, String> col_phone;
    @FXML
    TableColumn<PatientData, String> col_room;
    @FXML
    TableColumn<PatientData, String> col_bed;
    @FXML
    TextField SearchBox;

    public void BackToMainMenu(ActionEvent actionEvent) throws Exception {
        mainStage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

        new Main().setFXML("../main/menu/MainMenu.fxml", mainStage);
        System.out.println("Back to MainMenu...");
    }

    public void NewPatientOnClicked(ActionEvent actionEvent) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        TextField name = new TextField();
        TextField surname = new TextField();
        TextField phone = new TextField();
        TextField room = new TextField();
        TextField bed = new TextField();

        name.setPromptText("Name");
        surname.setPromptText("Surname");
        phone.setPromptText("Phone");
        room.setPromptText("Room");
        bed.setPromptText("Bed");


        Button okButton = new Button("OK");

        TextField[] textFields = new TextField[]{name, surname, phone, room, bed};

        errorMessageArray = new String[]{
                "Patient name cannot be empty",
                "Patient surname cannot be empty",
                "Patient phone number cannot be empty",
                "Patient phone room cannot be empty",
                "Patient phone bed cannot be empty",
        };

        okButton.setOnAction(new EventHandler<ActionEvent>() {

            private PatientData patientData;
            String errorMessage = "";

            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < textFields.length; i++) {
                    if (textFields[i].getText().equals("")) {
                        errorMessage += errorMessageArray[i] + "\n";
                    }
                }

                System.out.println(errorMessage);
                if (errorMessage == "") {

                    try {
                        patientData = new PatientData(
                                name.getText(),
                                surname.getText(),
                                phone.getText(),
                                room.getText(),
                                bed.getText()
                        );

                        Integer.parseInt(patientData.getPhone());
                        new JDBCCheckOutData().InsertNewPatientData(patientData);
                        RefreashOnClicked(event);


                        System.out.println(patientData.toString());
                        window.close();
                    } catch (NumberFormatException e) {
                        System.out.println("NumberFormatException");
                        AlertDialog("Error Message", "Phone must be number");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    AlertDialog("Error Message", errorMessage);
                }
                errorMessage = "";
            }
        });

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                name.setText("");
                surname.setText("");
                phone.setText("");
                room.setText("");
                bed.setText("");
            }
        });

        Button cancelButton = new Button("Close");
        cancelButton.setOnAction(event -> window.close());


        VBox layout = new VBox(6);

        HBox layout2 = new HBox(3);
        layout2.setPadding(new Insets(10, 20, 10, 20));
        layout2.getChildren().addAll(okButton, clearButton, cancelButton);

        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(name, surname, phone, room, bed, layout2);
        window.setScene(new Scene(layout));
        window.showAndWait();

    }

    private void AlertDialog(String title, String header) {
        final Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle(title);
        alert3.setHeaderText(header);
        alert3.setContentText("Please retry.");
        alert3.showAndWait();
    }


    public void DeleteOnclicked(ActionEvent actionEvent) {
        Stage deleteWindow = new Stage();
        deleteWindow.initModality(Modality.APPLICATION_MODAL);

        TextField id = new TextField();
        id.setPromptText("ID");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> deleteWindow.close());
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (id.getText().equals("")) {
                    System.out.println("Patient ID cannot be empty");
                    AlertDialog("Delete Error Message", "Patient ID cannot be empty");
                }
                try {
                    boolean checkStatment = false;
                    ArrayList<PatientData> allPatients = new JDBCCheckOutData().PatientAllData();
                    for (PatientData p : allPatients) {
                        if (p.getId() == Integer.parseInt(id.getText())) {
                            checkStatment = true;
                        }
                    }
                    if (checkStatment) {
                        new JDBCCheckOutData().DeletePatientData(Integer.parseInt(id.getText()));
                        RefreashOnClicked(event);
                        deleteWindow.close();
                    } else {
                        AlertDialog("Delete Method Not Found", "Patient ID <<" + id.getText() + ">> not found");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    AlertDialog("Delete Error Message", "ID must be number");
                }

                //If patient ID is not empty search for this record on the database
                //If patient ID is not found in the database, display message: “Patient ID <<ID>> not found”

                //If patient is successfully found and excluded from the database, display message: “Patient ID <<ID>> deleted!”


            }
        });


        deleteWindow.setMinWidth(250);
        VBox layout = new VBox(2);
        layout.setPadding(new Insets(20, 20, 20, 20));

        HBox layout2 = new HBox(2);
        layout2.setPadding(new Insets(10, 20, 10, 20));

        layout2.getChildren().addAll(confirmButton, closeButton);
        layout.getChildren().addAll(id, layout2);

        deleteWindow.setScene(new Scene(layout));
        deleteWindow.showAndWait();
    }


    private ObservableList<PatientData> getPatient() {
        ObservableList<PatientData> patientData = FXCollections.observableArrayList();
        try {
            JDBCCheckOutData jdbcCheckOutData = new JDBCCheckOutData();
            ArrayList<PatientData> patientDataArrayList = jdbcCheckOutData.PatientAllData();

            for (int i = 0; i < patientDataArrayList.size(); i++) {
                patientData.add(new PatientData(
                        patientDataArrayList.get(i).getId(),
                        patientDataArrayList.get(i).getName(),
                        patientDataArrayList.get(i).getSurname(),
                        patientDataArrayList.get(i).getPhone(),
                        patientDataArrayList.get(i).getRoom(),
                        patientDataArrayList.get(i).getBed()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientData;
    }

    public void RefreashOnClicked(ActionEvent actionEvent) {
        col_id.setCellValueFactory(new PropertyValueFactory<PatientData, String>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<PatientData, String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<PatientData, String>("surname"));
        col_phone.setCellValueFactory(new PropertyValueFactory<PatientData, String>("phone"));
        col_room.setCellValueFactory(new PropertyValueFactory<PatientData, String>("room"));
        col_bed.setCellValueFactory(new PropertyValueFactory<PatientData, String>("bed"));
        table.setItems(getPatient());
    }

    public void SearchPatient(KeyEvent keyEvent) {
        FilteredList<PatientData> filterData = new FilteredList<>(getPatient(), patientData -> true);

        SearchBox.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filterData.setPredicate(pers -> {
                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();

                if (pers.getName().indexOf(typedText) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<PatientData> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);
        });

    }


}
