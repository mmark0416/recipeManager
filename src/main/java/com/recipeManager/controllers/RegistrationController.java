package com.recipeManager.controllers;

import com.recipeManager.models.RegistrationModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private TextField usernameTextFld;
    @FXML
    private Label errorTextLbl;
    @FXML
    private PasswordField passwordFld;
    private RegistrationModel model = new RegistrationModel();

    @FXML
    private void clickRegistration(ActionEvent actionEvent) throws IOException {
        if (model.createUser(usernameTextFld.getText(), passwordFld.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Recept manager");
            alert.setContentText("Most már bejelentkezhetsz!");
            alert.setHeaderText("Sikeres regisztráció!");
            alert.show();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            errorTextLbl.setText("Ilyen felhasználónév már létezik");
        }
    }

    @FXML
    public void clickBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
