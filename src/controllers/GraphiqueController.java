package controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;

import views.GraphiqueView;
import utilities.DataChart;
import models.Compteur;
import models.Jour;
import models.ReleveJournalier;

public class GraphiqueController {
    private GraphiqueView graphiqueView;
    ArrayList<Compteur> compteurs;
    

    public GraphiqueController(GraphiqueView graphiqueView) {
        this.graphiqueView = graphiqueView;
        this.compteurs = Compteur.getAll();
        this.setupCompteurs();
    }
    
    public void setupCompteurs() {
        ArrayList<String> compteursString = new ArrayList<String>();
        for (Compteur compteur : this.compteurs) {
            String tmp = compteur.getLibelle() + " (" + compteur.getNumero() + ")";
            compteursString.add(tmp);
        }
        this.graphiqueView.setCompteursPane(compteursString);
    }

    public void requete(ActionEvent event) {
        if (this.checkDate() && this.checkSelection()) {
            String typeSomme = graphiqueView.getTypeSommeComboBox().getValue();
            String typeTemps = graphiqueView.getTypeTempsComboBox().getValue();
            String dateDebut = graphiqueView.getDateDebut().getValue().toString();
            String dateFin = graphiqueView.getDateFin().getValue().toString();
            ArrayList<Integer> compteurs = this.getSelectionCompteurs();


            // Pour chaque possibilite de la sélection, on récupere les valeurs des releves et l'abscisse a afficher pour le graphique
            ArrayList<Double> releves = new ArrayList<Double>();
            ArrayList<String> abs = new ArrayList<String>();
            ArrayList<ReleveJournalier> tmp = new ArrayList<ReleveJournalier>();

            if (typeTemps.equals("Jour de la Semaine")) {
                for (int i = 1; i < 8; i++) {
                    double somme = 0;
                    tmp = ReleveJournalier.getAllRelevesByDayOfAWeek(i, dateDebut, dateFin, compteurs);
                    for (ReleveJournalier releve : tmp) {
                        somme += releve.getNbPassageTotal();
                    }

                    abs.add(Jour.jourDeLaSemaine[i-1]);
                    if (typeSomme.equals("Moyenne")) {
                        somme = somme / tmp.size();
                    }
                    releves.add(somme);
                }

            } else if (typeTemps.equals("Jour du Mois")) {
                for (int i = 1; i < 32; i++) {
                    double somme = 0;
                    tmp = ReleveJournalier.getAllRelevesByDayOfAMonth(i, dateDebut, dateFin, compteurs);
                    for (ReleveJournalier releve : tmp) {
                        somme += releve.getNbPassageTotal();
                    }
                    abs.add(i+"");
                    if (typeSomme.equals("Moyenne")) {
                        somme = somme / tmp.size();
                    }
                    releves.add(somme);
                }

            } else if (typeTemps.equals("Mois")) {
                for (int i = 1; i < 13; i++) {
                    double somme = 0;
                    tmp = ReleveJournalier.getAllRelevesByMonth(i, dateDebut, dateFin, compteurs);
                    for (ReleveJournalier releve : tmp) {
                        somme += releve.getNbPassageTotal();
                    }
                    abs.add(Jour.moisDeLAnnee[i-1]);
                    if (typeSomme.equals("Moyenne")) {
                        somme = somme / tmp.size();
                    }
                    releves.add(somme);
                }

            } else if (typeTemps.equals("Annee")) {
                for (int i = 2020; i < 2024; i++) {
                    double somme = 0;
                    tmp = ReleveJournalier.getAllRelevesByYear(i, dateDebut, dateFin, compteurs);
                    for (ReleveJournalier releve : tmp) {
                        somme += releve.getNbPassageTotal();
                    }
                    abs.add(i+"");
                    if (typeSomme.equals("Moyenne")) {
                        somme = somme / tmp.size();
                    }
                    releves.add(somme);
                }
            }

            this.ajoutGraphe(releves, abs);
        }
    }

    public void ajoutGraphe (ArrayList<Double> dataReleves, ArrayList<String> nom){
        DataChart lineChart = new DataChart(dataReleves, nom);
        this.graphiqueView.setGraphesPane(lineChart.getLineChart());
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

    private boolean checkSelection() {
        return !graphiqueView.getSelectionCompteurCheckBoxes().isEmpty();
    }

    private ArrayList<Integer> getSelectionCompteurs(){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        ArrayList<String> result = graphiqueView.getSelectionCompteurCheckBoxes();
        String string;
        String tmp;
        for (int i = 1; i < result.size(); i++) {
            string = result.get(i);
            tmp = string.substring(string.indexOf("(") + 1, string.indexOf(")"));
            ret.add(Integer.parseInt(tmp));
        }
        return ret;
    }

}
