package views;

// Java imports
import java.util.ArrayList;

// JavaFX imports 
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// Project imports
import controllers.ExporterController;

/**
 * This class represents the view of the Exporter page.
 * This page is a form to export data.
 */
public class ExporterView extends BorderPane {
    private ExporterController exporterController;

    private HBox choiceTable;
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
        this.exporterController = new ExporterController(this);

        // Line 1 : Title
        Label title = new Label("Générer un fichier csv");
        title.getStyleClass().add("title");

        // Line 2 : Choice of table / modele
        initialiseChoiceTable();

        VBox top = new VBox();
        top.setSpacing(20);
        top.getChildren().addAll(title, choiceTable);
        top.setAlignment(Pos.CENTER);

        setTop(top);

        this.enregistrer = new Button("Enregistrer");
        this.setBottom(enregistrer);
        this.enregistrer.setOnAction(this.exporterController::enregistrer);
    }

    private void initialiseChoiceTable() {
        this.choiceTable = new HBox();
        this.choiceTable.setAlignment(Pos.CENTER);
        this.choiceTable.setSpacing(25);

        this.toggleGroup = new ToggleGroup(); // Group of radio buttons
        toggleGroup.selectedToggleProperty().addListener(this.exporterController::selectionEnregistrer);

        // Creation of each radio button
        RadioButton jourButton = new RadioButton("Jour");
        RadioButton compteurButton = new RadioButton("Compteur");
        RadioButton quartierButton = new RadioButton("Quartier");
        RadioButton releveJournalierButton = new RadioButton("Releve Journalier");
        jourButton.setSelected(true);

        toggleGroup.getToggles().addAll(jourButton, compteurButton, quartierButton, releveJournalierButton);
        this.choiceTable.getChildren().addAll(jourButton, compteurButton, quartierButton, releveJournalierButton);
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

    public String getSelected() {
        return ((RadioButton) this.toggleGroup.getSelectedToggle()).getText();
    }
}