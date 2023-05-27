package controllers;

import views.ExporterView;
import views.GraphiqueView;

import java.time.LocalDate;

import javax.swing.Action;

import database.DatabaseAccess;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

public class GraphiqueController {
    
    private Rooter rooter;
    private GraphiqueView graphiqueView;

    public GraphiqueController(Rooter rooter) {
        this.rooter = rooter;
        this.graphiqueView = (GraphiqueView) rooter.getView("Graphique");

        this.setupSelection();
    }

    public void setupSelection() {
        graphiqueView.getGenererButton().setOnAction(this::requete);
    }

    private void requete (ActionEvent event) {
        if (!checkDate()){
            return;
        }
        String typeSomme = graphiqueView.getTypeSommeComboBox().getValue();
        String typeTemps = graphiqueView.getTypeTempsComboBox().getValue();
        String typeGraphique = graphiqueView.getTypeGraphiqueComboBox().getValue();
        String dateDebut = graphiqueView.getDateDebut().getValue().toString();
        String dateFin = graphiqueView.getDateFin().getValue().toString();
        DatabaseAccess databaseAccess = new DatabaseAccess();
        System.out.println(typeSomme + " " + typeTemps + " " + typeGraphique + " " + dateDebut + " " + dateFin);
        //databaseAccess.getGraphique(typeSomme, typeTemps, typeGraphique, dateDebut, dateFin);       
    }

    private boolean checkDate() {
        boolean ret = true;
        LocalDate dateDebut = graphiqueView.getDateDebut().getValue();
        LocalDate dateFin = graphiqueView.getDateFin().getValue();
        if (dateDebut == null || dateFin == null || dateDebut.isAfter(dateFin)) {
            System.out.println("Date de début supérieure à la date de fin");
            ret = false;
        }
        return ret;
    }


}
