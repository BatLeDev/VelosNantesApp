package controllers;

import views.ExporterView;
import views.GraphiqueView;

import java.time.LocalDate;
import java.util.ArrayList;

import database.DatabaseAccess;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Toggle;
import models.Compteur;

public class GraphiqueController {
    
    private Rooter rooter;
    private GraphiqueView graphiqueView;
    ArrayList<Compteur> compteurs;
    

    public GraphiqueController(Rooter rooter) {
        this.rooter = rooter;
        this.graphiqueView = (GraphiqueView) rooter.getView("Graphique");
        compteurs = DatabaseAccess.getCompteurs();

        this.setupSelection();
        this.graphiqueView.getCalqueCompteursGroup().getToggles().get(1).setSelected(true);
        this.graphiqueView.getCalqueCompteursGroup().selectedToggleProperty().addListener(this::calqueCompteursListener);

    }

    public void setupSelection() {
        graphiqueView.getGenererButton().setOnAction(this::requete);
        this.setupCompteurs();
    }

    public void setupCompteurs() {
        ArrayList<String> compteursString = new ArrayList<String>();
        for (Compteur compteur : this.compteurs) {       
            String tmp = compteur.getLibelle() + " (" + compteur.getNumero() + ")";     
            compteursString.add(tmp);
        }  
        graphiqueView.setCompteurCalquePane(compteursString);
        graphiqueView.getToutSelectionner().setOnAction(this::toutSelectionner);
        graphiqueView.getToutDeselectionner().setOnAction(this::toutDeselectionner);
    }


    public void setupCalques(){
        ArrayList<String> calquesString = new ArrayList<String>();
        calquesString.add("Calque 1");
        calquesString.add("Calque 2");
        calquesString.add("Calque 3");
        calquesString.add("Calque 4");

        graphiqueView.setCompteurCalquePane(calquesString);
        graphiqueView.getToutSelectionner().setOnAction(this::toutSelectionner);
        graphiqueView.getToutDeselectionner().setOnAction(this::toutDeselectionner);
    }

    private void requete (ActionEvent event) {
        if (checkDate()){
            String typeSomme = graphiqueView.getTypeSommeComboBox().getValue();
            String typeTemps = graphiqueView.getTypeTempsComboBox().getValue();
            String typeGraphique = graphiqueView.getTypeGraphiqueComboBox().getValue();
            String dateDebut = graphiqueView.getDateDebut().getValue().toString();
            String dateFin = graphiqueView.getDateFin().getValue().toString();
            DatabaseAccess databaseAccess = new DatabaseAccess();
            System.out.println(typeSomme + " " + typeTemps + " " + typeGraphique + " " + dateDebut + " " + dateFin);
            //databaseAccess.getGraphique(typeSomme, typeTemps, typeGraphique, dateDebut, dateFin);       
        }

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

    private void toutSelectionner(ActionEvent event) {
        graphiqueView.toutSelectionner();
    }

    private void toutDeselectionner(ActionEvent event) {
        graphiqueView.toutDeselectionner();
    }

    private void calqueCompteursListener(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        if (this.graphiqueView.getSelectedLeftPane() == this.graphiqueView.getCompteurRadioButton()){
            this.setupCompteurs();
            graphiqueView.getCalqueCompteursGroup().getToggles().get(1).setSelected(true);

        } else {
            this.setupCalques();
            graphiqueView.getCalqueCompteursGroup().getToggles().get(0).setSelected(true);
        }
        this.graphiqueView.getCalqueCompteursGroup().selectedToggleProperty().addListener(this::calqueCompteursListener);

    }

}
