package views;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This class represents the view of the Exporter page.
 * This page is a form to export data.
 */
public class ExporterView extends BorderPane {

    ToggleGroup toggleGroup;   

    /**
     * Constructor
     * This method is called by the rooter to create the view
     * 
     * Initialise the elements of the view
     */
    public ExporterView() {
        
        Pane top = initialiseGenererPane();
        this.setTop(top);

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

}
