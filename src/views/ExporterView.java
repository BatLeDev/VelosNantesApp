package views;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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

    public RadioButton getSelectedRadioButton() {
        return (RadioButton) this.toggleGroup.getSelectedToggle();
    }



    private Pane initialiseGenererPane(){
        VBox ret = new VBox();
        ret.setPadding(new Insets(10));
        ret.setSpacing(10);
        ret.setStyle("-fx-background-color: beige;");

        Label source = new Label("Generer un fichier (a partir de donnees brut)");

        this.toggleGroup = new ToggleGroup();
        FlowPane choix = new FlowPane();
        RadioButton jourButton = new RadioButton("Jour");
        jourButton.setSelected(true);
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

        FlowPane tmp = new FlowPane();
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

    private Pane initialiseSelectionCompteurPane() {
        VBox ret = new VBox();
        ret.setPadding(new Insets(10));

        FlowPane tmp = new FlowPane();
        CheckBox numero = new CheckBox("Numero"); 
        CheckBox libelle = new CheckBox("Libelle");
        CheckBox direction = new CheckBox("Direction");
        CheckBox observations = new CheckBox("Observations");
        CheckBox longitude = new CheckBox("Longitude");
        CheckBox latitude = new CheckBox("Latitude");
        CheckBox leQuartier = new CheckBox("Le Quartier");

        enregistrer = new Button("Enregistrer (csv)");

        this.checkBoxes = new ArrayList<CheckBox>();
        this.checkBoxes.add(numero);
        this.checkBoxes.add(libelle);
        this.checkBoxes.add(direction);
        this.checkBoxes.add(observations);
        this.checkBoxes.add(longitude);
        this.checkBoxes.add(latitude);
        this.checkBoxes.add(leQuartier);
        tmp.getChildren().addAll(numero, libelle, direction, observations, longitude, latitude, leQuartier);        
        ret.getChildren().addAll(tmp, enregistrer);

        return ret;
    }


    private Pane initialiseSelectionReleveJournalierPane() {
        VBox ret = new VBox();
        ret.setPadding(new Insets(10));

        FlowPane tmp = new FlowPane();
        CheckBox leCompteur = new CheckBox("Compteur");
        CheckBox leJour = new CheckBox("Jour");
        CheckBox probabiliteAnomalie = new CheckBox("Probabilite d'anomalie");
        CheckBox total = new CheckBox("Total des releves");
        CheckBox heureMax = new CheckBox("Heure la plus frequentee");
        CheckBox freqHeureMax = new CheckBox("Passage maximum");

        enregistrer = new Button("Enregistrer (csv)");

        this.checkBoxes = new ArrayList<CheckBox>();
        this.checkBoxes.add(leCompteur);
        this.checkBoxes.add(leJour);
        this.checkBoxes.add(probabiliteAnomalie);
        this.checkBoxes.add(total);
        this.checkBoxes.add(heureMax);
        this.checkBoxes.add(freqHeureMax);
        tmp.getChildren().addAll(leCompteur, leJour, probabiliteAnomalie, total, heureMax, freqHeureMax);
        ret.getChildren().addAll(tmp, enregistrer);

        return ret;
    }

    public void setSelectionJour () {
        this.setCenter(initialiseSelectionJourPane());
    }

    public void setSelectionCompteur () {
        this.setCenter(initialiseSelectionCompteurPane());
    }

    public void setSelectionReleveJournalier () {
        this.setCenter(initialiseSelectionReleveJournalierPane());
    }
}
