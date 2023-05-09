package com.recipeManager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RecipeController {
    @FXML
    public ListView ingredientListView;
    @FXML
    public Label idRecipeName;
    @FXML
    public Label idDescription;

    @FXML
    public void clickBack(ActionEvent actionEvent) {
    }
}
