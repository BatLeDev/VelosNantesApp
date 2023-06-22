package views;

import java.util.ArrayList;

import controllers.ModificationController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import models.IModels;

/**
 * This class represents the view of the Exporter page.
 * This page is a form to export data.
 */
public class ModificationView extends BorderPane {
    private ModificationController modificationController;

    private ToggleGroup selectionGroup;
    private TableView table;
    private Label message;
    private ArrayList<TextField> textFields;
    private Button button;

    public ModificationView(ModificationController modificationController) {
        this.modificationController = modificationController;
        
        this.textFields = new ArrayList<TextField>();

        this.selectionGroup = new ToggleGroup();
        RadioButton compteurRadioButton = new RadioButton("Compteur");
        compteurRadioButton.setSelected(true);

        RadioButton quartierRadioButton = new RadioButton("Quartier");

        RadioButton jourRadioButton = new RadioButton("Jour");

        RadioButton releveRadioButton = new RadioButton("Releve Journalier");

        compteurRadioButton.setToggleGroup(this.selectionGroup);
        quartierRadioButton.setToggleGroup(this.selectionGroup);
        jourRadioButton.setToggleGroup(this.selectionGroup);
        releveRadioButton.setToggleGroup(this.selectionGroup);

        HBox selectionBox = new HBox(compteurRadioButton, quartierRadioButton, jourRadioButton, releveRadioButton);
        selectionBox.setSpacing(10);
        selectionBox.setAlignment(javafx.geometry.Pos.CENTER);

        this.table = new TableView();

        this.button = new Button("Ajouter");

        this.message = new Label("> ");
        this.message.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");

        this.setPadding(new Insets(15, 10, 10, 10));
        this.setTop(selectionBox);
        this.setBottom(this.message);
        this.message.setPrefHeight(50);

        this.modificationController.setView(this);
        this.selectionGroup.selectedToggleProperty().addListener(this.modificationController::changerTable);
        button.setOnAction(this.modificationController::ajouter);
        this.modificationController.changerTable(null, null, null);

    }

    public TableView getTable() {
        return this.table;
    }

    public void setTable(TableView<IModels> table) {
        this.table = table;

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(this.table);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        for (TextField textField : this.textFields) {
            hBox.getChildren().add(textField);
        }
        hBox.getChildren().add(this.button);
        borderPane.setBottom(hBox);

        this.setCenter(borderPane);
    }

    public String getSelection() {
        return ((RadioButton) this.selectionGroup.getSelectedToggle()).getText();
    }

    public void setMessage(String message) {
        this.message.setText("> " + message);
    }

    public void setTextFields(String ... textFields) {
        this.textFields = new ArrayList<>();
        for (String textField : textFields) {
            TextField textField1 = new TextField();
            textField1.setPrefWidth(100);
            textField1.setPromptText(textField);
            this.textFields.add(textField1);
        }
    }

    public ArrayList<String> getTextFields() {
        ArrayList<String> textFields = new ArrayList<>();
        for (TextField textField : this.textFields) {
            textFields.add(textField.getText());
        }
        return textFields;
    }

    public void clearTextFields() {
        for (TextField textField : this.textFields) {
            textField.clear();
        }
    }



}
