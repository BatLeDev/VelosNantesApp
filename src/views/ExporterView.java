package views;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This class represents the view of the Exporter page.
 * This page is a form to export data.
 */
public class ExporterView extends BorderPane {

    private ToggleGroup toggleGroup;   

    private ArrayList<CheckBox> checkBoxes;
    private Button enregistrer;

    /**
     * Constructor
     * This method is called by the rooter to create the view
     * 
     * Initialise the elements of the view
     */
    public ExporterView() {
        
        Pane top = initialiseGenererPane();
        this.setTop(top);
        Pane center = initialiseSelectionJourPane();
        this.setCenter(center);

    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public Button getEnregistrer() {
        return enregistrer;
    }

    public ArrayList<CheckBox> getSelectedCheckBoxes() {
        ArrayList<CheckBox> ret = new ArrayList<CheckBox>();
        for (CheckBox cb : this.checkBoxes) {
            if (cb.isSelected()) {
                ret.add(cb);
            }
        }
        return ret;
    }



    private Pane initialiseGenererPane(){
        VBox ret = new VBox();
        ret.setPadding(new Insets(10));
        ret.setSpacing(10);
        ret.setStyle("-fx-background-color: beige;");

        Label source = new Label("Generer un fichier (a partir de donnees brut)");

        this.toggleGroup = new ToggleGroup();
        HBox choix = new HBox();
        RadioButton jourButton = new RadioButton("Jour");
        toggleGroup.getToggles().add(jourButton);
        RadioButton compteurButton = new RadioButton("Compteur");
        toggleGroup.getToggles().add(compteurButton);
        RadioButton releveJournalierButton = new RadioButton("Releve Journalier");
        toggleGroup.getToggles().add(releveJournalierButton);

        choix.getChildren().addAll(jourButton, compteurButton, releveJournalierButton);

        ret.getChildren().addAll(source,choix);

        return ret;
    }
    
    private Pane initialiseSelectionJourPane() {
        VBox ret = new VBox();
        ret.setPadding(new Insets(10));

        HBox tmp = new HBox();
        CheckBox date = new CheckBox("Date"); 
        CheckBox jourSemaine = new CheckBox("Jour de la semaine");
        CheckBox temp = new CheckBox("Temperature");

        enregistrer = new Button("Enregistrer (csv)");

        this.checkBoxes = new ArrayList<CheckBox>();
        this.checkBoxes.add(date);
        this.checkBoxes.add(jourSemaine);
        this.checkBoxes.add(temp);
        tmp.getChildren().addAll(date, jourSemaine, temp);
        ret.getChildren().addAll(tmp, enregistrer);

        return ret;
    }

}
