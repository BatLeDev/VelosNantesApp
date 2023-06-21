package views;

import controllers.ModificationController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
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

    public ModificationView(ModificationController modificationController) {
        this.modificationController = modificationController;
        
        this.selectionGroup = new ToggleGroup();
        RadioButton compteurRadioButton = new RadioButton("Compteur");

        RadioButton quartierRadioButton = new RadioButton("Quartier");
        compteurRadioButton.setSelected(true);

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

        this.message = new Label("> ");
        this.message.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");

        this.setPadding(new Insets(15, 10, 10, 10));
        this.setTop(selectionBox);
        this.setCenter(this.table);
        this.setBottom(this.message);
        this.message.setPrefHeight(50);

        this.modificationController.setView(this);
        this.selectionGroup.selectedToggleProperty().addListener(this.modificationController::changerTable);
        this.modificationController.changerTable(null, null, null);
    }

    public TableView getTable() {
        return this.table;
    }

    public void setTable(TableView<IModels> table) {
        this.table = table;
        this.table.setStyle("-fx-font-size: 15px;"); 
        this.setCenter(this.table);
    }

    public String getSelection() {
        return ((RadioButton) this.selectionGroup.getSelectedToggle()).getText();
    }

    public void setMessage(String message) {
        this.message.setText("> " + message);
    }



}
