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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// Project imports
import models.Jour;
import controllers.ExporterController;

/**
 * This class represents the view of the Exporter page. 
 * <p>It's a form constituted of radio buttons and check boxes.
 * Generated a csv file and request where to save it.
 */
public class ExporterView extends VBox {
    private ExporterController exporterController;

    // Part "Jour, Compteur, Quartier, Releve Journalier"
    private HBox choiceTable;
    private ToggleGroup toggleGroup;   

    private ArrayList<CheckBox> checkBoxes; // Selection of the columns to export
    private HBox choicesColums; // Line of check boxes
    private Button enregistrer; // Button to save the csv file

    /**
     * This method is called by the rooter to create the view
     * <p>Initialise the elements of the view: title, radio buttons, check boxes and button.
     * and set the action of the button.
     */
    public ExporterView() {
        this.exporterController = new ExporterController(this);
        setSpacing(20);
        setAlignment(Pos.CENTER);

        // Line 1 : Title
        Label title = new Label("Générer un fichier csv");
        title.getStyleClass().add("title");

        // Line 2 : Choice of table / modele
        initialiseChoiceTable();

        getChildren().addAll(title, choiceTable);

        // Line 3 & 4
        this.enregistrer = new Button("Enregistrer");
        this.enregistrer.setOnAction(this.exporterController::enregistrer);
        setChoicesColums(Jour.getColumns());
    }

    /**
     * This method initialise radio buttons and his group.
     */
    private void initialiseChoiceTable() {
        // Creation of each radio button
        RadioButton jourButton = new RadioButton("Jour");
        RadioButton compteurButton = new RadioButton("Compteur");
        RadioButton quartierButton = new RadioButton("Quartier");
        RadioButton releveJournalierButton = new RadioButton("Releve Journalier");
        jourButton.setSelected(true);

        // Creation of the group of radio buttons
        this.toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(jourButton, compteurButton, quartierButton, releveJournalierButton);
        toggleGroup.selectedToggleProperty().addListener(this.exporterController::updateChoiceColums);

        // Creation of the line of radio buttons
        this.choiceTable = new HBox();
        this.choiceTable.setAlignment(Pos.CENTER);
        this.choiceTable.setSpacing(25);
        this.choiceTable.getChildren().addAll(jourButton, compteurButton, quartierButton, releveJournalierButton);
    }

    /**
     * This method change the check boxes.
     * 
     * @param ckBox Array of string which contains the name of the columns to export
     */
    public void setChoicesColums(String[] ckBox) {
        getChildren().removeAll(this.choicesColums, this.enregistrer);

        this.choicesColums = new HBox();
        this.checkBoxes = new ArrayList<CheckBox>();
        for (String s : ckBox) {
            CheckBox cb = new CheckBox(s);
            this.checkBoxes.add(cb);
            choicesColums.getChildren().add(cb);
        }

        this.choicesColums.setAlignment(Pos.CENTER);
        this.choicesColums.setSpacing(25);

        getChildren().addAll(this.choicesColums, this.enregistrer);
    }

    /**
     * To get the selected list check boxes.
     * 
     * @return ArrayList of text which contains the name of the selected check boxes
     */
    public ArrayList<String> getSelectedChoices() {
        ArrayList<String> ret = new ArrayList<String>();
        for (CheckBox cb : this.checkBoxes) {
            if (cb.isSelected()) {
                ret.add(cb.getText());
            }
        }
        return ret;
    }

    /**
     * To get the selected radio button.
     * 
     * @return String which contains the name of the selected radio button
     */
    public String getSelectedTable() {
        return ((RadioButton) this.toggleGroup.getSelectedToggle()).getText();
    }

}