package com.recipeManager.utilities;

import java.util.List;

@lombok.Data
public class User {
    private String username;
    private String password;
    private List<Recipe> recipes;

}
