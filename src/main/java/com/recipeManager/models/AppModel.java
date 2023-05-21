package com.recipeManager.models;

import com.recipeManager.JSONDB.JSON;
import com.recipeManager.utilities.Recipe;
import com.recipeManager.utilities.User;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class AppModel {
    private JSON jsondb = new JSON();

    public List<String> getRecipeNames(String username) {
        User user = jsondb.readJSON(username);
        List<String> recipeNames = new ArrayList<>();
        for (Recipe recipe : user.getRecipes()) {
            recipeNames.add(recipe.getName());
        }
        return recipeNames;
    }

    public Recipe getUser(String username, String recipeName) {
        User user = jsondb.readJSON(username);
        Recipe recipe = new Recipe();
        for (Recipe r : user.getRecipes()) {
            if (r.getName().equals(recipeName)) {
                recipe = r;
            }
        }
        return recipe;
    }

    public void deleteRecipe(String username, String selectedRecipe) {
        User user = jsondb.readJSON(username);
        List<Recipe> recipes = user.getRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equals(selectedRecipe)) {
                recipes.remove(i);
            }
        }
        user.setRecipes(recipes);
        jsondb.writeJSON(user);
    }
    public void deleteFromJSONDB(String username){
        jsondb.deleteJSON(username);
    }
}
