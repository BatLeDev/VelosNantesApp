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

import controllers.ExporterController;
import utilities.Rooter;

/**
 * This class represents the view of the Exporter page.
 * This page is a form to export data.
 */
public class ExporterView extends BorderPane {
    private ExporterController exporterController;

    private ToggleGroup toggleGroup;   

    private ArrayList<CheckBox> checkBoxes;
    private Button enregistrer;

    /**
     * Constructor
     * This method is called by the rooter to create the view
     * 
     * Initialise the elements of the view
     */
    public ExporterView(Rooter rooter) {
        this.exporterController = new ExporterController(rooter, this);
        
        Pane top = initialiseGenererPane();
        this.setTop(top);
        this.enregistrer = new Button("Enregistrer (csv)");
        this.setBottom(enregistrer);

        Pane bottom = new Pane();
        this.enregistrer = new Button("Enregistrer (csv)");
        this.enregistrer.setOnAction(this.exporterController::test);
        bottom.getChildren().add(enregistrer);

        this.setBottom(bottom);
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
        RadioButton quartierButton = new RadioButton("Quartier");
        toggleGroup.getToggles().add(quartierButton);

        choix.getChildren().addAll(jourButton, compteurButton, releveJournalierButton, quartierButton);

        ret.getChildren().addAll(source,choix);

        return ret;
    }

    public void setSelection(String[] ckBox) {
        FlowPane tmp = new FlowPane();
        this.checkBoxes = new ArrayList<CheckBox>();
        for (String s : ckBox) {
            CheckBox cb = new CheckBox(s);
            this.checkBoxes.add(cb);
            tmp.getChildren().add(cb);
        }
        this.setCenter(tmp);
    }

}
