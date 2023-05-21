package com.recipeManager.controllers;

import com.recipeManager.models.AppModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    public Label idSelectedRecipeErrLbl;
    @FXML
    private ListView idListView;
    @FXML
    private Label idWelcomeLbl;
    private String username;
    private String selectedRecipe;
    private ObservableList<String> recipeNames = FXCollections.observableArrayList();
    private AppModel model = new AppModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idListView.setItems(recipeNames);
        idListView.setOnMouseClicked(mouseEvent -> {
            String selected = idListView.getSelectionModel().getSelectedItems().toString();
            this.selectedRecipe = selected.substring(1, selected.length() - 1);
        });
        idWelcomeLbl.setText("Szia " + username);
    }

    @FXML
    private void clickLogout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void clickEdit(ActionEvent actionEvent) throws IOException {
        if (selectedRecipe != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addRecipe.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<AddRecipeController>getController().iniData(username, selectedRecipe);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            idSelectedRecipeErrLbl.setText("Válasz ki egy receptet!");
        }
    }

    @FXML
    private void clickDelete(ActionEvent actionEvent) throws IOException {
        if (selectedRecipe != null) {
            model.deleteRecipe(username, selectedRecipe);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/app.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<AppController>getController().initUsername(username);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            idSelectedRecipeErrLbl.setText("Válasz ki egy receptet!");
        }
    }

    @FXML
    private void clickAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addRecipe.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<AddRecipeController>getController().initUsername(username);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void clickOpen(ActionEvent actionEvent) throws IOException {
        if (selectedRecipe != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/recipe.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<RecipeController>getController().initRecipe(model.getUser(username, selectedRecipe), username);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            idSelectedRecipeErrLbl.setText("Válasz ki egy receptet!");
        }
    }

    @FXML
    public void clickCalc(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/calc.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<CalcController>getController().initUsername(username);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void initUsername(String username) {
        this.username = username;
        recipeNames.addAll(model.getRecipeNames(username));
    }

    public void clickUserDelete(ActionEvent actionEvent) throws IOException {
        ButtonType yes = new ButtonType("Igen", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Nem", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Biztos törölni szeretnéd az adatokat?",
                yes,
                no);
        alert.setHeaderText(username + " nevű felhasználó törlése!");

        alert.setTitle("Recept manager");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            model.deleteFromJSONDB(username);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Recept manager");
            alert.setContentText(username + " nevű felhasználót töröltük!");
            alert.setHeaderText("Viszlát");
            alert.show();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

}
