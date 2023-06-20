package controllers;

import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import views.ExporterView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Toggle;

import models.*;
import utilities.ReadWriteFile;

public class ExporterController  {

    private ExporterView exporterView;
    public ExporterController(ExporterView exporterView) {
        this.exporterView = exporterView;
        this.exporterView.setSelection(Jour.getHeaders());
    }

    public void enregistrer (ActionEvent action) {
        if (this.exporterView.getSelectedCheckBoxes() != null && this.exporterView.getSelectedCheckBoxes().size() > 0) {
            System.out.println("Les cases cochÃ©es sont :");
            ArrayList<String> coches = new ArrayList<String>();
            for (CheckBox cb : exporterView.getSelectedCheckBoxes()) {
                System.out.println(cb.getText());
                coches.add(cb.getText());
            }

            String table = exporterView.getSelected();
            ArrayList<String> contenu = new ArrayList<String>();

            if (table.equals("Jour")) {
                contenu = Jour.getJoursCSV(coches);

            } else if (table.equals("Compteur")) {
                contenu = Compteur.getCompteursCSV(coches);

            } else if (table.equals("Releve Journalier")) {
                contenu = ReleveJournalier.getRelevesCSV(coches);

            } else if (table.equals("Quartier")) {
                contenu = Quartier.getQuartiersCSV(coches);
            }


            ReadWriteFile.writeCsv(ReadWriteFile.fileChooser(), contenu);
        }
    }

    public void selectionEnregistrer(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        String selected = exporterView.getSelected();
        if (selected.equals("Jour")){
            this.exporterView.setSelection(Jour.getHeaders());

        } else if (selected.equals("Compteur")){
            this.exporterView.setSelection(Compteur.getHeaders());

        } else if (selected.equals("Releve Journalier")){
            this.exporterView.setSelection(ReleveJournalier.getHeadersSimplified());
        } else if (selected.equals("Quartier")){
            this.exporterView.setSelection(Quartier.getHeaders());
        }
    }


}