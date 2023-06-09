package controllers;

import views.GraphiqueView;
import utilities.DataChart;

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
        graphiqueView.setCompteurCalquePane(compteursString,true);
        graphiqueView.getToutSelectionner().setOnAction(this::toutSelectionner);
        graphiqueView.getToutDeselectionner().setOnAction(this::toutDeselectionner);
    }


    public void setupCalques(){
        ArrayList<String> calquesString = new ArrayList<String>();
        calquesString.add("Calque 1 (1)");
        calquesString.add("Calque 2 (2)");
        calquesString.add("Calque 3 (3)");
        calquesString.add("Calque 4 (4)");

        graphiqueView.setCompteurCalquePane(calquesString,false);
        graphiqueView.getToutSelectionner().setOnAction(this::toutSelectionner);
        graphiqueView.getToutDeselectionner().setOnAction(this::toutDeselectionner);
    }

    private void requete (ActionEvent event) {
        if (this.checkDate() && this.checkSelection()){
            String typeSomme = graphiqueView.getTypeSommeComboBox().getValue();
            String typeTemps = graphiqueView.getTypeTempsComboBox().getValue();
            String dateDebut = graphiqueView.getDateDebut().getValue().toString();
            String dateFin = graphiqueView.getDateFin().getValue().toString();
            String selection = graphiqueView.getSelection();
            String selectionCheckBoxes = this.selectionToString();
            ArrayList<Double> ret = DatabaseAccess.getGraphique(typeSomme, typeTemps, dateDebut, dateFin, selection, selectionCheckBoxes);       
            System.out.println(ret);
            this.ajoutGraphe(ret);
        }

    }

    private boolean checkDate() {
        boolean ret = true;
        LocalDate dateDebut = graphiqueView.getDateDebut().getValue();
        LocalDate dateFin = graphiqueView.getDateFin().getValue();
        if (dateDebut == null || dateFin == null || dateDebut.isAfter(dateFin) || dateDebut.isBefore(LocalDate.of(2020, 1, 1)) || dateFin.isAfter(LocalDate.of(2023, 1, 31))) {
            System.out.println("Les dates doivent etre comprisent entre : 2020-01-01 et 2023-01-31");
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
        return !graphiqueView.getSelectionCompteurCheckBoxes().isEmpty();
    }

    private String selectionToString(){
        String ret = "";
        ArrayList<String> result = graphiqueView.getSelectionCompteurCheckBoxes();
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


    public void ajoutGraphe (ArrayList<Double> dataReleves, ArrayList<String> nom){
        DataChart lineChart = new DataChart(dataReleves, nom);
        this.graphiqueView.setGraphesPane(lineChart.getLineChart());
    }

    public void ajoutGraphe (ArrayList<Double> dataReleves){
        DataChart lineChart = new DataChart(dataReleves);
        this.graphiqueView.setGraphesPane(lineChart.getLineChart());
    }


}
