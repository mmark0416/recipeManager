package com.recipeManager.utilities;

import java.util.List;

@lombok.Data
public class Recipe {
    private String name;
    private String description;
    private List<String> ingredients;
}
