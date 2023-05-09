package com.recipeManager.controllers;

import com.recipeManager.models.LoginModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField usernameTextFld;
    @FXML
    public Label loginErrorLbl;
    @FXML
    public PasswordField passwordFld;
    private LoginModel model = new LoginModel();

    @FXML
    public void clickLogin(ActionEvent actionEvent) throws IOException {
        if (model.checkLogin(usernameTextFld.getText(), passwordFld.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/app.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<AppController>getController().initUsername(usernameTextFld.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            loginErrorLbl.setText("Hibás felhasználónév, vagy jelszó");
        }
    }

    public void clickRegistration(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/registration.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickClose(ActionEvent actionEvent) {
        Platform.exit();
    }
}
