package views;

import javafx.geometry.Pos;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utilities.DatePickerConverter;

/**
 * This class represents the view of the Exporter page.
 * This page is a form to export data.
 */
public class GraphiqueView extends BorderPane {

    public static final String DATE_FORMAT = "yyyy-MM-dd";


    // Elements of the view
    private Button exempleButton;
    
    private ComboBox<String> typeSommeComboBox;
    private ComboBox<String> typeTempsComboBox;
    private ComboBox<String> typeGraphiqueComboBox;
    private DatePicker dateDebut;
    private DatePicker dateFin;
    private Button genererButton;
    private ToggleGroup calqueCompteursGroup;
    private ArrayList<String> compteurs;

    /**
     * Constructor
     * This method is called by the rooter to create the view
     * 
     * Initialise the elements of the view
     */
    public GraphiqueView() {
        // Creation of the elements of the view
        Pane graphiquePane = this.initialiseRequetePane();
        this.setTop(graphiquePane);

        setCompteurPane();

        this.genererButton = new Button("Générer");
        setCenter(this.genererButton);

        setAlignment(graphiquePane, Pos.CENTER);
    }

    public Button getGenererButton() {
        return genererButton;
    }

    public ComboBox<String> getTypeSommeComboBox() {
        return typeSommeComboBox;
    }

    public ComboBox<String> getTypeTempsComboBox() {
        return typeTempsComboBox;
    }

    public ComboBox<String> getTypeGraphiqueComboBox() {
        return typeGraphiqueComboBox;
    }

    public DatePicker getDateDebut() {
        return dateDebut;
    }

    public DatePicker getDateFin() {
        return dateFin;
    }

    public ToggleGroup getCalqueCompteursGroup() {
        return calqueCompteursGroup;
    }

    public void setExempleButton(Button exempleButton) {
        this.exempleButton = exempleButton;
    }

    public void setTypeSommeComboBox(ComboBox<String> typeSommeComboBox) {
        this.typeSommeComboBox = typeSommeComboBox;
    }

    public void setTypeTempsComboBox(ComboBox<String> typeTempsComboBox) {
        this.typeTempsComboBox = typeTempsComboBox;
    }

    public void setTypeGraphiqueComboBox(ComboBox<String> typeGraphiqueComboBox) {
        this.typeGraphiqueComboBox = typeGraphiqueComboBox;
    }




    private Pane initialiseRequetePane() {
        FlowPane ret = new FlowPane();
        ret.setStyle("-fx-background-color: beige;");
    
        Label affichage = new Label("Affichage de la  ");

        this.typeSommeComboBox = new ComboBox<String>();
        this.typeSommeComboBox.getItems().addAll("Somme", "Moyenne");
        this.typeSommeComboBox.setValue("Somme");

        Label desReleves = new Label("  des releves, de  ");

        dateDebut = new DatePicker();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        dateDebut.setConverter(new DatePickerConverter(formatter));

        Label a = new Label("  à  ");

        dateFin = new DatePicker();
        dateFin.setConverter(new DatePickerConverter(formatter));

        Label par = new Label("  par  ");

        this.typeTempsComboBox = new ComboBox<String>();
        this.typeTempsComboBox.getItems().addAll("Heure","Jour","Mois","Annee");
        this.typeTempsComboBox.setValue("Heure");

        Label en = new Label("  en  ");

        this.typeGraphiqueComboBox = new ComboBox<String>();
        this.typeGraphiqueComboBox.getItems().setAll("Histogramme", "Courbe");
        this.typeGraphiqueComboBox.setValue("Histogramme");

        ret.getChildren().addAll(affichage, this.typeSommeComboBox, desReleves, dateDebut, a, dateFin, par, this.typeTempsComboBox, en, this.typeGraphiqueComboBox);
        return ret;
    }


    private Pane initialiseCalqueCompteursPane() {
        FlowPane ret = new FlowPane();
        ret.setStyle("-fx-background-color: pink;");
    
    
        this.calqueCompteursGroup = new ToggleGroup();
        RadioButton calqueRadioButton = new RadioButton("Calque");
        calqueRadioButton.setToggleGroup(this.calqueCompteursGroup);

        RadioButton compteurRadioButton = new RadioButton("Compteur");
        compteurRadioButton.setToggleGroup(this.calqueCompteursGroup);
        compteurRadioButton.setSelected(true);

        ret.getChildren().addAll(calqueRadioButton, compteurRadioButton);

        return ret;
    }


    public void setCompteurPane() {
        VBox ret = new VBox();
        ret.setPadding(new Insets(10));
        ret.setSpacing(10);
        ret.setStyle("-fx-background-color: pink;");
    
        Pane calqueCompteurPane = initialiseCalqueCompteursPane();
    
        CheckBox compteurs1 = new CheckBox("Compteur 1");
        compteurs1.setSelected(true);
        CheckBox compteurs2 = new CheckBox("Compteur 2");
        compteurs2.setSelected(true);
        CheckBox compteurs3 = new CheckBox("Compteur 3");
        compteurs3.setSelected(true);
        CheckBox compteurs4 = new CheckBox("Compteur 4");
        compteurs4.setSelected(true);
        CheckBox compteurs5 = new CheckBox("Compteur 5");
        compteurs5.setSelected(true);

        HBox tmp = new HBox();
        Button toutSelectionner = new Button("Tout selectionner");
        Button toutDeselectionner = new Button("Tout déselectionner");

        tmp.getChildren().addAll(toutSelectionner, toutDeselectionner);

        ret.getChildren().addAll(calqueCompteurPane, compteurs1, compteurs2, compteurs3, compteurs4, compteurs5, tmp);

        this.setLeft(ret);
    }

}

