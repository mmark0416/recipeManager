package com.recipeManager.models;

import com.recipeManager.JSONDB.JSON;

@lombok.Data
public class LoginModel {
    private JSON jsondb = new JSON();

    public boolean checkLogin(String username, String password) {
        if (jsondb.isFileExists(username) && jsondb.readJSON(username).getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
