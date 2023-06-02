package controllers;

import java.util.ArrayList;

import database.DatabaseAccess;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import views.ExporterView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

import utilities.WriteFile;

public class ExporterController  {
    private Rooter rooter;
    private ExporterView exporterView;

    public ExporterController(Rooter rooter) {
        this.rooter = rooter;
        this.exporterView = (ExporterView) rooter.getView("Exporter");

        setupEnregistrer();
    }

    public void setupEnregistrer() {
        exporterView.getToggleGroup().selectedToggleProperty().addListener(this::selectionEnregistrer);
        exporterView.getEnregistrer().setOnAction(this::test);
    }

    private void test (ActionEvent action) {
        if (exporterView.getSelectedCheckBoxes().size() == 0) {
            System.out.println("Aucune case n'est cochée");
        } else {
            System.out.println("Les cases cochées sont :");
            ArrayList<String> coches = new ArrayList<String>();
            for (CheckBox cb : exporterView.getSelectedCheckBoxes()) {
                System.out.println(cb.getText());
                coches.add(cb.getText());
            }
            String table = exporterView.getSelectedRadioButton().getText();
            if (table.equals("Jour")) {
                coches = this.transformeColonneJour(coches);

            } else if (table.equals("Compteur")) {
                coches = this.transformeColonneCompteur(coches);

            } else if (table.equals("Releve Journalier")) {
                coches = this.transformeColonneReleveJournalier(coches);
                table = "vue_ReleveJournalierResume";
            } 

            ArrayList<String> contenu = DatabaseAccess.exporterRequete(coches, table);

            WriteFile.writeCsv(WriteFile.fileChooser(), contenu);
        }
    }

    public void selectionEnregistrer(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        RadioButton rb = (RadioButton) exporterView.getToggleGroup().getSelectedToggle();
        String selected = rb.getText();
        if (selected.equals("Jour")){
            exporterView.setSelectionJour();
            this.setupEnregistrer();

        } else if (selected.equals("Compteur")){
            exporterView.setSelectionCompteur();
            this.setupEnregistrer();

        } else if (selected.equals("Releve Journalier")){
            exporterView.setSelectionReleveJournalier();
            this.setupEnregistrer();

        }
    }

    private ArrayList<String> transformeColonneJour(ArrayList<String> contenu){
        ArrayList<String> colonne = new ArrayList<String>();
        for (String s : contenu) {
            if (s.equals("Date")){
                colonne.add("jourDate");
            } else if (s.equals("Jour de la semaine")){
                colonne.add("jourDeSemaine");
            } else if (s.equals("Temperature")){
                colonne.add("temperature");
            }
        }
        return colonne;
    }

    private ArrayList<String> transformeColonneCompteur(ArrayList<String> contenu){
        ArrayList<String> colonne = new ArrayList<String>();
        for (String s : contenu) {
            if (s.equals("Numero")){
                colonne.add("Numero");
            } else if (s.equals("Libelle")){
                colonne.add("libelle");
            } else if (s.equals("Direction")){
                colonne.add("direction");
            } else if (s.equals("Observations")){
                colonne.add("observations");
            } else if (s.equals("Longitude")){
                colonne.add("longitude");
            } else if (s.equals("Latitude")){
                colonne.add("latitude");
            } else if (s.equals("Le Quartier")){
                colonne.add("leQuartier");
            }
        }
        return colonne;
    }

    
    private ArrayList<String> transformeColonneReleveJournalier(ArrayList<String> contenu){
        ArrayList<String> colonne = new ArrayList<String>();
        for (String s : contenu) {
            if (s.equals("Compteur")){
                colonne.add("leCompteur");
            } else if (s.equals("Jour")){
                colonne.add("leJour");
            } else if (s.equals("Probabilite d'anomalie")){
                colonne.add("probabiliteAnomalie");
            } else if (s.equals("Total des releves")){
                colonne.add("total");
            } else if (s.equals("Heure la plus frequentee")){
                colonne.add("heureMax");
            } else if (s.equals("Passage maximum")){
                colonne.add("freqHeureMax");
            }
        }
        return colonne;
    }

}
