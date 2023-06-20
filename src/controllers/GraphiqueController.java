package controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;

import views.GraphiqueView;
import utilities.Rooter;
import models.Compteur;
import models.ReleveJournalier;

public class GraphiqueController {
    private Rooter rooter;
    private GraphiqueView graphiqueView;
    ArrayList<Compteur> compteurs;
    

    public GraphiqueController(Rooter rooter, GraphiqueView graphiqueView) {
        this.rooter = rooter;
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
        graphiqueView.setCompteurCalquePane(compteursString,true);
        graphiqueView.getToutSelectionner().setOnAction(this::toutSelectionner);
        graphiqueView.getToutDeselectionner().setOnAction(this::toutDeselectionner);
    }

    public void requete (ActionEvent event) {
        if (this.checkDate() && this.checkSelection()){
            String typeSomme = graphiqueView.getTypeSommeComboBox().getValue();
            String typeTemps = graphiqueView.getTypeTempsComboBox().getValue();
            String dateDebut = graphiqueView.getDateDebut().getValue().toString();
            String dateFin = graphiqueView.getDateFin().getValue().toString();
            ArrayList<Integer> selectionCheckBoxes = this.getSelectionCompteurs();

            ArrayList<String> releves = this.getReleves(typeTemps, typeSomme, dateDebut, dateFin, selectionCheckBoxes);   
            System.out.println(releves);
        }

    }

    private ArrayList<String> getReleves(String typeTemps, String typeSomme, String dateDebut, String dateFin, ArrayList<Integer> compteurs){
        ArrayList<String> ret = new ArrayList<String>();
        ArrayList<ReleveJournalier> tmp = new ArrayList<ReleveJournalier>();
        if (typeTemps.equals("Jour de la Semaine")) {
            for (int i = 1; i < 8; i++) {
                int somme = 0;
                tmp = ReleveJournalier.getAllRelevesByDayOfAWeek(i, dateDebut, dateFin, compteurs);
                for (ReleveJournalier releve : tmp) {
                    somme += releve.getNbPassageTotal();
                }
                ret.add(i+","+somme);
            }

        } else if (typeTemps.equals("Jour du Mois")) {
            for (int i = 1; i < 32; i++) {
                int somme = 0;
                tmp = ReleveJournalier.getAllRelevesByDayOfAMonth(i, dateDebut, dateFin, compteurs);
                for (ReleveJournalier releve : tmp) {
                    somme += releve.getNbPassageTotal();
                }
                ret.add(i+","+somme);
            }

        } else if (typeTemps.equals("Mois")) {
            for (int i = 1; i < 13; i++) {
                int somme = 0;
                tmp = ReleveJournalier.getAllRelevesByMonth(i, dateDebut, dateFin, compteurs);
                for (ReleveJournalier releve : tmp) {
                    somme += releve.getNbPassageTotal();
                }
                ret.add(i+","+somme);
            }

        } else if (typeTemps.equals("Annee")) {
            for (int i = 2020; i < 2024; i++) {
                int somme = 0;
                tmp = ReleveJournalier.getAllRelevesByYear(i, dateDebut, dateFin, compteurs);
                for (ReleveJournalier releve : tmp) {
                    somme += releve.getNbPassageTotal();
                }
                ret.add(i+","+somme);
            }
        }

        if (typeSomme.equals("Moyenne")) {
            for (int i = 0; i < ret.size(); i++) {
                String[] tmp2 = ret.get(i).split(",");
                int somme = Integer.parseInt(tmp2[1]);
                somme = somme / compteurs.size();
                ret.set(i, tmp2[0] + "," + somme);
            }
        }
        return ret;
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
