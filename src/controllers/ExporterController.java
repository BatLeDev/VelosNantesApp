package controllers;

// Java imports
import java.util.ArrayList;

// JavaFX imports
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Toggle;

// Project imports
import models.*;
import utilities.ReadWriteFile;
import views.ExporterView;

/**
 * This class is the controller of the Exporter page.
 * <p>This class is used to generate a csv file from models.
 */
public class ExporterController  {
    private ExporterView exporterView;

    /**
     * Initialize the controller of the Exporter page
     * 
     * @param exporterView The view of the Exporter page
     */
    public ExporterController(ExporterView exporterView) {
        this.exporterView = exporterView;
    }

    /**
     * This method is called when the user click on the button "Enregistrer".
     * <p>It generate a csv file from the selected columns and the selected table.
     * <p> Parameters are automatically set by the listener.
     */
    public void enregistrer(ActionEvent action) {
        // Check if a table is selected and if there is at least one column selected
        if (this.exporterView.getSelectedChoices() != null && this.exporterView.getSelectedChoices().size() > 0) {
            ArrayList<String> checked = exporterView.getSelectedChoices();

            String table = exporterView.getSelectedTable();
            ArrayList<String> contenu = new ArrayList<String>();

            if (table.equals("Jour")) {
                contenu = Jour.getJoursCSV(checked);

            } else if (table.equals("Compteur")) {
                contenu = Compteur.getCompteursCSV(checked);

            } else if (table.equals("Releve Journalier")) {
                contenu = ReleveJournalier.getRelevesCSV(checked);

            } else if (table.equals("Quartier")) {
                contenu = Quartier.getQuartiersCSV(checked);
            }

            try {
                String filename = ReadWriteFile.fileChooser();
                ReadWriteFile.writeCsv(filename, contenu);
            } catch (Exception e) {
                System.out.println("Erreur lors de l'enregistrement : " + e.getMessage());
            }
        }
    }

    /**
     * This method is called when the user change a table to export.
     * <p>It set the selection of the columns to export.
     * <p> Parameters are automatically set by the listener.
     */
    public void updateChoiceColums(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        String selected = exporterView.getSelectedTable();
        if (selected.equals("Jour")){
            this.exporterView.setChoicesColums(Jour.getColumns());

        } else if (selected.equals("Compteur")){
            this.exporterView.setChoicesColums(Compteur.getColumns());

        } else if (selected.equals("Releve Journalier")){
            this.exporterView.setChoicesColums(ReleveJournalier.getColumnsSimplified());

        } else if (selected.equals("Quartier")){
            this.exporterView.setChoicesColums(Quartier.getColumns());
        }
    }
}