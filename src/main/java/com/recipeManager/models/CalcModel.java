package com.recipeManager.models;

import com.recipeManager.JSONDB.JSON;
import com.recipeManager.utilities.Calorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


@lombok.Data
public class CalcModel {
    private JSON json = new JSON();

    public ObservableList<Calorie> getCalories() {
        ObservableList<Calorie> calories = FXCollections.observableArrayList();
        calories.addAll(json.readCalories());
        return calories;
    }

    public String calculate(String operator, int num1, int num2) {
        String value = "";
        try {
            switch (operator) {
                case "+":
                    value = String.valueOf(num1 + num2);
                    break;
                case "-":
                    value = String.valueOf(num1 - num2);
                    break;
                case "*":
                    value = String.valueOf(num1 * num2);
                    break;
                case "/":
                    value = String.valueOf(num1 / num2);
                    break;
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        return value;
    }

    public String choiceSymbol(String symbol) {
        String operator = "";
        switch (symbol) {
            case "Plus" -> operator = "+";
            case "Minus" -> operator = "-";
            case "Multiply" -> operator = "*";
            case "Divide" -> operator = "/";
        }
        return operator;
    }
}
