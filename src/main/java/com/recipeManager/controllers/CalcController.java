package com.recipeManager.controllers;

import com.recipeManager.models.CalcModel;
import com.recipeManager.utilities.Calorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalcController implements Initializable{
    @FXML
    public TableView<Calorie> IdCaloriesTblView;
    @FXML
    public Label idDivideByZero;
    @FXML
    public Label idProteinLbl;
    @FXML
    public Label idCarbLbl;
    @FXML
    public Label idFatLbl;
    @FXML
    private TableColumn<Calorie, String> IdProductTblCol;
    @FXML
    private TableColumn<Calorie, String> IdProteinTblCol;
    @FXML
    private TableColumn<Calorie, String> IdCarbsTblCol;
    @FXML
    private TableColumn<Calorie, String> IdFatTblCol;
    @FXML
    private Pane titlePane;
    @FXML
    private Label idResultLbl;
    private String username;
    private double x, y;
    private int num1 = 0;
    private String operator = "+";
    private CalcModel model = new CalcModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IdProductTblCol.setCellValueFactory(new PropertyValueFactory<Calorie, String>("prodact"));
        IdProteinTblCol.setCellValueFactory(new PropertyValueFactory<Calorie, String>("protein"));
        IdCarbsTblCol.setCellValueFactory(new PropertyValueFactory<Calorie, String>("carb"));
        IdFatTblCol.setCellValueFactory(new PropertyValueFactory<Calorie, String>("fat"));
        IdCaloriesTblView.setItems(model.getCalories());
    }

    @FXML
    void clickNumber(MouseEvent event) {
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("id",""));
        idResultLbl.setText(Integer.parseInt(idResultLbl.getText())==0?String.valueOf(value):String.valueOf(Integer.parseInt(idResultLbl.getText())*10+value));
    }

    @FXML
    void clickSymbol(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn","");
        if(symbol.equals("Equals")) {
            int num2 = Integer.parseInt(idResultLbl.getText());
            idResultLbl.setText(model.calculate(operator, num1, num2) + "");
            operator = ".";
        }
        else if(symbol.equals("Clear")) {
            idResultLbl.setText(String.valueOf(0));
            operator = ".";
        }
        else {
            operator = model.choiceSymbol(symbol);
            num1 = Integer.parseInt(idResultLbl.getText());
            idResultLbl.setText(String.valueOf(0));
        }
    }

    @FXML
    public void clickBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/app.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<AppController>getController().initUsername(username);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void clickAddProtein(ActionEvent event) {
        int value = Integer.parseInt(idResultLbl.getText()) + Integer.parseInt(idProteinLbl.getText());
        idProteinLbl.setText(String.valueOf(value));
        idResultLbl.setText("0");
    }

    @FXML
    public void clickAddCarb(ActionEvent actionEvent) {
        int value = Integer.parseInt(idResultLbl.getText()) + Integer.parseInt(idCarbLbl.getText());
        idCarbLbl.setText(String.valueOf(value));
        idResultLbl.setText("0");
    }

    @FXML
    public void clickAddFat(ActionEvent actionEvent) {
        int value = Integer.parseInt(idResultLbl.getText()) + Integer.parseInt(idFatLbl.getText());
        idFatLbl.setText(String.valueOf(value));
        idResultLbl.setText("0");
    }

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });
    }

    public void initUsername(String username) {
        this.username = username;
    }

}
