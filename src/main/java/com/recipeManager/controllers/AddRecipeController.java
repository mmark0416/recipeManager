package com.recipeManager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddRecipeController {
    @FXML
    public TextArea descriptionTextArea;
    @FXML
    public ListView ingredientListView;
    @FXML
    public TextField ingredientTextFld;
    @FXML
    public TextField recipeNameTextFld;
    @FXML
    public Label idRecipeNameErrorLbl;
    @FXML
    public Label idIngredientErrorLbl;

    @FXML
    public void clickSave(ActionEvent actionEvent) {
    }

    @FXML
    public void clickBack(ActionEvent actionEvent) {
    }

    @FXML
    public void clickAdd(ActionEvent actionEvent) {
    }

    @FXML
    public void clickDelete(ActionEvent actionEvent) {
    }
}
