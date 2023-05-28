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
        calquesString.add("Calque 1 (1)");
        calquesString.add("Calque 2 (2)");
        calquesString.add("Calque 3 (3)");
        calquesString.add("Calque 4 (4)");

        graphiqueView.setCompteurCalquePane(calquesString);
        graphiqueView.getToutSelectionner().setOnAction(this::toutSelectionner);
        graphiqueView.getToutDeselectionner().setOnAction(this::toutDeselectionner);
    }

    private void requete (ActionEvent event) {
        if (this.checkDate() && this.checkSelection()){
            String typeSomme = graphiqueView.getTypeSommeComboBox().getValue();
            String typeTemps = graphiqueView.getTypeTempsComboBox().getValue();
            String typeGraphique = graphiqueView.getTypeGraphiqueComboBox().getValue();
            String dateDebut = graphiqueView.getDateDebut().getValue().toString();
            String dateFin = graphiqueView.getDateFin().getValue().toString();
            String selection = graphiqueView.getSelection();
            String selectionCheckBoxes = this.selectionToString();
            ArrayList<String> ret = DatabaseAccess.getGraphique(typeSomme, typeTemps, typeGraphique, dateDebut, dateFin, selection, selectionCheckBoxes);       
            System.out.println(ret);
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
        if (this.graphiqueView.getSelection().equals("Compteur")) {
            this.setupCompteurs();
            graphiqueView.getCalqueCompteursGroup().getToggles().get(1).setSelected(true);

        } else {
            this.setupCalques();
            graphiqueView.getCalqueCompteursGroup().getToggles().get(0).setSelected(true);
        }
        this.graphiqueView.getCalqueCompteursGroup().selectedToggleProperty().addListener(this::calqueCompteursListener);

    }


    private boolean checkSelection() {
        return !graphiqueView.getChackBoxesSelection().isEmpty();
    }

    private String selectionToString(){
        String ret = "";
        ArrayList<String> result = graphiqueView.getChackBoxesSelection();
        String string = result.get(0);
        String tmp = string.substring(string.indexOf("(") + 1, string.indexOf(")"));
        ret += tmp;
        for (int i = 1; i < result.size(); i++) {
            string = result.get(i);
            tmp = string.substring(string.indexOf("(") + 1, string.indexOf(")"));
            ret += "," + tmp;
        }
        return ret;
    }


}
