package com.recipeManager.models;

import com.recipeManager.JSONDB.JSONDB;
import com.recipeManager.utilities.Recipe;
import com.recipeManager.utilities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

@lombok.Data
public class AddRecipeModel {
    private ObservableList<String> ingredientLst = FXCollections.observableArrayList();
    private JSONDB jsondb = new JSONDB();

    public void deleteFromIngredients(String ingredient) {
        ingredientLst.remove(ingredient);
    }

    public void addToIngredients(String ingredient) {
        ingredientLst.add(ingredient);
    }

    public void saveRecipe(String username, String description, String recipeName) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        recipe.setDescription(description);
        recipe.setIngredients(ingredientLst);
        User user = jsondb.readJSON(username);
        user.getRecipes().add(recipe);
        jsondb.writeJSON(user);
    }

    public String getDescrition(String username, String selectedRecipe) {
        User user = jsondb.readJSON(username);
        List<Recipe> recipes = user.getRecipes();
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(selectedRecipe)) {
                return recipe.getDescription();
            }
        }
        return "";
    }

    public void getIngredients(String username, String selectedRecipe) {
        User user = jsondb.readJSON(username);
        List<Recipe> recipes = user.getRecipes();
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(selectedRecipe)) {
                ingredientLst.addAll(recipe.getIngredients());
            }
        }
    }

    public boolean isRecipeName(String username, String recipeName) {
        User user = jsondb.readJSON(username);
        for (Recipe recipe : user.getRecipes()) {
            if (recipe.getName().equals(recipeName)) {
                return true;
            }
        }
        return false;
    }

    public void deleteOldRecipe(String username, String recipeName) {
        User user = jsondb.readJSON(username);
        List<Recipe> recipes = user.getRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equals(recipeName)) {
                recipes.remove(i);
            }
        }
        user.setRecipes(recipes);
        jsondb.writeJSON(user);
    }
}
