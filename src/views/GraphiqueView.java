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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
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
    private ComboBox<String> typeSommeComboBox;
    private ComboBox<String> typeTempsComboBox;
    private ComboBox<String> typeGraphiqueComboBox;
    private DatePicker dateDebut;
    private DatePicker dateFin;
    private Button genererButton;
    private ToggleGroup calqueCompteursGroup;
    private Button toutSelectionner;
    Button toutDeselectionner;
    private ArrayList<CheckBox> compteurCheckBoxes;
    private ArrayList<CheckBox> calqueCheckBoxes;

    /**
     * Constructor
     * This method is called by the rooter to create the view
     * 
     * Initialise the elements of the view
     */
    public GraphiqueView() {
        // Creation of the elements of the view
        compteurCheckBoxes = new ArrayList<CheckBox>();

        Pane graphiquePane = this.initialiseRequetePane();
        this.setTop(graphiquePane);

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

    public Button getToutSelectionner() {
        return toutSelectionner;
    }

    public Button getToutDeselectionner() {
        return toutDeselectionner;
    }

    public void toutSelectionner() {
        for (CheckBox checkBox : compteurCheckBoxes) {
            checkBox.setSelected(true);
        }
    }

    public void toutDeselectionner() {
        for (CheckBox checkBox : compteurCheckBoxes) {
            checkBox.setSelected(false);
        }
    }


    public Toggle getCalqueRadioButton(){
        return calqueCompteursGroup.getToggles().get(0);
    }

    public Toggle getCompteurRadioButton(){
        return calqueCompteursGroup.getToggles().get(1);
    }

    public ArrayList<String> getSelectionCompteurCheckBoxes() {
        ArrayList<String> ret = new ArrayList<String>();
        for (CheckBox checkBox : compteurCheckBoxes) {
            if (checkBox.isSelected()) {
                ret.add(checkBox.getText());
            }
        }
        return ret;
    }

    public ArrayList<String> getSelectionCalqueCheckBoxes() {
        ArrayList<String> ret = new ArrayList<String>();
        for (CheckBox checkBox : calqueCheckBoxes) {
            if (checkBox.isSelected()) {
                ret.add(checkBox.getText());
            }
        }
        return ret;
    }

    public String getSelection(){
        String ret = "";
        if (getCalqueRadioButton().isSelected()) {
            ret = "Calque";
        } else if (getCompteurRadioButton().isSelected()) {
            ret = "Compteur";
        }
        return ret;
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
        dateDebut.setValue(dateDebut.getConverter().fromString("2020-01-01"));

        Label a = new Label("  à  ");

        dateFin = new DatePicker();
        dateFin.setConverter(new DatePickerConverter(formatter));
        dateFin.setValue(dateDebut.getConverter().fromString("2023-01-01"));


        Label par = new Label("  par  ");

        this.typeTempsComboBox = new ComboBox<String>();
        this.typeTempsComboBox.getItems().addAll("Tout", "Jour","Mois","Annee");
        this.typeTempsComboBox.setValue("Tout");

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
        RadioButton calqueRadioButton = new RadioButton("Calques");
        calqueRadioButton.setToggleGroup(this.calqueCompteursGroup);

        RadioButton compteurRadioButton = new RadioButton("Compteurs");
        compteurRadioButton.setToggleGroup(this.calqueCompteursGroup);

        ret.getChildren().addAll(calqueRadioButton, compteurRadioButton);

        return ret;
    }


    public void setCompteurCalquePane(ArrayList<String> contenu, boolean type) {
        if (type){
            compteurCheckBoxes = new ArrayList<CheckBox>();
        } else {
            calqueCheckBoxes = new ArrayList<CheckBox>();
        }

        VBox ret = new VBox();
        ret.setPadding(new Insets(10));
        ret.setSpacing(10);
        ret.setStyle("-fx-background-color: pink;");
    
        Pane calqueCompteurPane = initialiseCalqueCompteursPane();
    
        HBox tmp = new HBox();
        tmp.setSpacing(10);
        toutSelectionner = new Button("Tout selectionner");
        toutDeselectionner = new Button("Tout déselectionner");

        GridPane compteursPane = new GridPane();
        compteursPane.setPadding(new Insets(10));
        compteursPane.setVgap(10);
        int i = 0;
        int j = 0;
        for (String string : contenu) {
            CheckBox tmpCompteur = new CheckBox(string);
            tmpCompteur.setSelected(true);
            compteursPane.add(tmpCompteur, i, j);
            i++;
            if (i == 2) {
                i = 0;
                j++;
            }
            if (type){
                compteurCheckBoxes.add(tmpCompteur);
            } else {
                compteurCheckBoxes.add(tmpCompteur);
            }
        }
        ScrollPane scrollPane = new ScrollPane(compteursPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(375);
        scrollPane.setPrefWidth(250);
        scrollPane.setContent(compteursPane);

        tmp.getChildren().addAll(toutSelectionner, toutDeselectionner);
        ret.getChildren().addAll(calqueCompteurPane, tmp, scrollPane);
        
        this.setLeft(ret);
    }


}

