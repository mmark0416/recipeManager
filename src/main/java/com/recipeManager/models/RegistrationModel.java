package com.recipeManager.models;

import com.recipeManager.JSONDB.JSON;
import com.recipeManager.utilities.Recipe;
import com.recipeManager.utilities.User;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class RegistrationModel {
    private JSON jsondb = new JSON();

    public boolean createUser(String username, String password) {
        if (!jsondb.isFileExists(username) && !password.isEmpty() && !username.isEmpty()) {
            User user = new User();
            List<Recipe> recipeList = new ArrayList<>();
            user.setUsername(username);
            user.setPassword(password);
            user.setRecipes(recipeList);
            jsondb.writeJSON(user);
            return true;
        } else {
            return false;
        }
    }
}
