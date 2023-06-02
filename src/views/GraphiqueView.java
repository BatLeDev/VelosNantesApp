package views;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * This class represents the view of the Exporter page.
 * This page is a form to export data.
 */
public class GraphiqueView extends BorderPane {

    // Elements of the view
    private Button exempleButton;
    
    private ComboBox<String> typeSommeComboBox;
    private ComboBox<String> typeTempsComboBox;
    private ComboBox<String> typeGraphiqueComboBox;
    

    /**
     * Constructor
     * This method is called by the rooter to create the view
     * 
     * Initialise the elements of the view
     */
    public GraphiqueView() {
        // Creation of the elements of the view
        exempleButton = new Button("Exemple");


        Pane graphiquePane = this.initialiseRequetePane();
        this.setTop(graphiquePane);

        Pane calquePane = this.initialiseCalquePane();
        this.setLeft(calquePane);

        setCenter(exempleButton);

        setAlignment(graphiquePane, Pos.CENTER);
        setAlignment(exempleButton, Pos.CENTER);

        setMargin(exempleButton, new Insets(10));
    }

    public Button getExempleButton() {
        return exempleButton;
    }




    private Pane initialiseRequetePane() {
        FlowPane ret = new FlowPane();
        ret.setStyle("-fx-background-color: beige;");
    
        Label affichage = new Label("Affichage de la  ");

        this.typeSommeComboBox = new ComboBox<String>();
        this.typeSommeComboBox.getItems().addAll("Somme", "Moyenne");
        this.typeSommeComboBox.setValue("Somme");

        Label par = new Label("  par  ");

        this.typeTempsComboBox = new ComboBox<String>();
        this.typeTempsComboBox.getItems().addAll("Heure","Jour","Mois","Annee");
        this.typeTempsComboBox.setValue("Heure");

        Label en = new Label("  en  ");

        this.typeGraphiqueComboBox = new ComboBox<String>();
        this.typeGraphiqueComboBox.getItems().setAll("Histogramme", "Courbe");
        this.typeGraphiqueComboBox.setValue("Histogramme");

        
        ret.getChildren().addAll(affichage, typeSommeComboBox, par, typeTempsComboBox, en, typeGraphiqueComboBox);

        return ret;
    }


    private Pane initialiseCalquePane() {
        GridPane ret = new GridPane();
        ret.setStyle("-fx-background-color: pink;");

        Label calque = new Label("Calque");
        
        ret.add(calque,0,0);

        return ret;
    }

}

