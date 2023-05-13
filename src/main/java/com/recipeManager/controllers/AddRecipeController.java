package com.recipeManager.controllers;

import com.recipeManager.models.AddRecipeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRecipeController implements Initializable {
    @FXML
    public Label idRecipeNameErrorLbl;
    @FXML
    public Label idIngredientErrorLbl;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private ListView ingredientListView;
    @FXML
    private TextField ingredientTextFld;
    @FXML
    private TextField recipeNameTextFld;
    private AddRecipeModel model = new AddRecipeModel();
    private String username;
    private String selectedRecipe;
    private String selectedIngredient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ingredientListView.setItems(model.getIngredientLst());
        ingredientListView.setOnMouseClicked(mouseEvent -> {
            String selected = ingredientListView.getSelectionModel().getSelectedItems().toString();
            this.selectedIngredient = selected.substring(1, selected.length() - 1);
        });
    }

    @FXML
    private void clickSave(ActionEvent actionEvent) throws IOException {
        if (selectedRecipe != null) {
            model.deleteOldRecipe(username, selectedRecipe);
        }
        if ((!model.isRecipeName(username, recipeNameTextFld.getText())) || selectedRecipe != null) {
            model.saveRecipe(username, descriptionTextArea.getText(), recipeNameTextFld.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/app.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<AppController>getController().initUsername(username);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            idRecipeNameErrorLbl.setText("Ilyen receptnév már van!");
        }
    }

    @FXML
    private void clickBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/app.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<AppController>getController().initUsername(username);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void clickAdd(ActionEvent actionEvent) {
        if (ingredientTextFld.getText().isEmpty()) {
            idIngredientErrorLbl.setText("Űres mező!");
        } else {
            model.addToIngredients(ingredientTextFld.getText());
            ingredientTextFld.clear();
        }
    }

    @FXML
    private void clickDelete(ActionEvent actionEvent) {
        if (ingredientTextFld.getText().isEmpty()) {
            model.deleteFromIngredients(selectedIngredient);
        } else {
            model.deleteFromIngredients(ingredientTextFld.getText());
            ingredientTextFld.clear();
        }
    }

    public void initUsername(String username) {
        this.username = username;
    }

    public void iniData(String username, String selectedRecipe) {
        this.username = username;
        this.selectedRecipe = selectedRecipe;
        String description = model.getDescrition(username, selectedRecipe);
        descriptionTextArea.setText(description);
        recipeNameTextFld.setText(selectedRecipe);
        model.getIngredients(username, selectedRecipe);

    }
}
