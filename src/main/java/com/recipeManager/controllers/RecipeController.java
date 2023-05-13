package com.recipeManager.controllers;

import com.recipeManager.utilities.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class RecipeController {
    public Label idRecipeName;
    @FXML
    private Label idDescription;
    @FXML
    private ListView ingredientListView;
    private ObservableList<String> ingredients = FXCollections.observableArrayList();
    private String username;

    @FXML
    private void clickBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/app.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<AppController>getController().initUsername(username);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void initRecipe(Recipe recipe, String username) {
        ingredients.addAll(recipe.getIngredients());
        ingredientListView.setItems(ingredients);
        idDescription.setText(recipe.getDescription());
        idRecipeName.setText(recipe.getName());
        this.username = username;
    }
}
