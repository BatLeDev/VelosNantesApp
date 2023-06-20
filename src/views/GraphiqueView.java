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
import javafx.scene.control.ScrollPane;
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
    private DatePicker dateDebut;
    private DatePicker dateFin;
    private Button genererButton;
    private Button toutSelectionner;
    Button toutDeselectionner;
    private ArrayList<CheckBox> compteurCheckBoxes;

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

    public DatePicker getDateDebut() {
        return dateDebut;
    }

    public DatePicker getDateFin() {
        return dateFin;
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

    public ArrayList<String> getSelectionCompteurCheckBoxes() {
        ArrayList<String> ret = new ArrayList<String>();
        for (CheckBox checkBox : compteurCheckBoxes) {
            if (checkBox.isSelected()) {
                ret.add(checkBox.getText());
            }
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
        this.typeTempsComboBox.getItems().addAll("Jour de la Semaine", "Jour du Mois", "Mois", "Annee");
        this.typeTempsComboBox.setValue("Jour de la Semaine");

        ret.getChildren().addAll(affichage, this.typeSommeComboBox, desReleves, dateDebut, a, dateFin, par,
                this.typeTempsComboBox);
        return ret;
    }

    public void setCompteurCalquePane(ArrayList<String> contenu, boolean type) {
        
        this.compteurCheckBoxes = new ArrayList<CheckBox>();


        VBox ret = new VBox();
        ret.setPadding(new Insets(10));
        ret.setSpacing(10);
        ret.setStyle("-fx-background-color: pink;");

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
        
            compteurCheckBoxes.add(tmpCompteur);

        }
        VBox compteursVBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(compteursPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(compteursPane);
        compteursVBox.getChildren().add(scrollPane);

        tmp.getChildren().addAll(toutSelectionner, toutDeselectionner);
        ret.getChildren().addAll(tmp, compteursVBox);

        this.setLeft(ret);
    }

}
