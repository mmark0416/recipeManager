module com.example.recipeManager {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires lombok;

    exports com.recipeManager.main;
    opens com.recipeManager.main to javafx.fxml;
    exports com.recipeManager.controllers;
    opens com.recipeManager.controllers to javafx.fxml;
    exports com.recipeManager.utilities;
    opens com.recipeManager.utilities to javafx.fxml;
    exports com.recipeManager;
    opens com.recipeManager to javafx.fxml;
}