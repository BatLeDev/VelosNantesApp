package controllers;

import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import views.ExporterView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

import utilities.Rooter;
import utilities.ReadWriteFile;
import models.*;

public class ExporterController  {

    private Rooter rooter;
    private ExporterView exporterView;

    public ExporterController(Rooter rooter, ExporterView exporterView) {
        this.rooter = rooter;
        this.exporterView = exporterView;
    }

    public void test (ActionEvent action) {
        if (this.exporterView.getSelectedCheckBoxes() != null && this.exporterView.getSelectedCheckBoxes().size() > 0) {
            System.out.println("Les cases cochées sont :");
            ArrayList<String> coches = new ArrayList<String>();
            for (CheckBox cb : exporterView.getSelectedCheckBoxes()) {
                System.out.println(cb.getText());
                coches.add(cb.getText());
            }

            String table = exporterView.getSelectedRadioButton().getText();
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
        String selected = this.exporterView.getSelected();
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
