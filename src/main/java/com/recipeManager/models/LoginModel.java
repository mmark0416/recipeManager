package com.recipeManager.models;

import com.recipeManager.JSONDB.JSONDB;

@lombok.Data
public class LoginModel {
    private JSONDB jsondb = new JSONDB();

    public boolean checkLogin(String username, String password) {
        if (jsondb.isFileExists(username) && jsondb.readJSON(username).getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
