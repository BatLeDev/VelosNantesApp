package models;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Class Jour
 * This class is used to represent a day, with its date, its day, its holidays and its average temperature
 * 
 * This class save all the days in a HashMap, with the date as key and the Jour object as value
 */
public class Jour implements IModels, Comparable<Jour> {

    // ----------------------------- static attributes -----------------------------
    
    /**
     * HashMap containing all Jour objects
     * In key it's the date of the jour
     * In value it's the jour object
     * This HashMap allow to get a jour by his date and verify uniqueness of date
     */
    private static HashMap<String, Jour> jourList = new HashMap<String, Jour>();

    /**
     * The differents day of the week (Lundi - Dimanche)
     * index 0 : Lundi , index 6 : Dimanche
     */
    public final static String[] jourDeLaSemaine = {"Lundi", "Mardi", "Mercredi","Jeudi", "Vendredi","Samedi","Dimanche"};

    /** 
     * The differents months of the year (Janvier - Décembre)
     * index 0 : Javier, index 11 : Décembre
     */
    public final static String[] moisDeLAnnee = {"Janvier", "Février", "Mars", "Avril", "Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};

    // ----------------------------- static methods -----------------------------

    /**
     * Get a jour by its date
     * 
     * @param date the date of the jour to get (format : YYYY-MM-DD)
     * @return the jour object corresponding to the date
     */
    public static Jour getJour (String date) {
        return Jour.jourList.get(date);
    }

    /**
     * Delete a jour by its date
     * 
     * @param date the date of the jour to delete (format : YYYY-MM-DD)
     */
    public static void deleteJour (String date) {
        Jour.jourList.remove(date);
        ReleveJournalier.removeAllRelevesOfAJour(date);
    }

    /**
     * Check if a date is valid : YYYY-MM-DD
     * 
     * @param date the date to check (format : YYYY-MM-DD)
     * @return true if the date is valid, false otherwise
     */
    private static boolean dateValide(String date) {
        boolean ret = false;

        if (date != null && !date.isEmpty()) {
            String[] dateSplit = date.split("-");
            if (dateSplit.length == 3) {
                int annee = Integer.parseInt(dateSplit[0]);
                int mois = Integer.parseInt(dateSplit[1]);
                int jour = Integer.parseInt(dateSplit[2]);

                if ((jour > 0) && (jour < 32) && (mois > 0) && (mois < 13) && (annee > 2000)) {
                    ret = true;
                }
            }
        }
        return ret;
    }

 
    public static String[] getColumns() {
        return new String[] { "Date", "Jour", "Temperature Moyenne" };
    }

    public static ArrayList<String> getJoursCSV(ArrayList<String> contenu) {
        if (contenu == null || contenu.isEmpty() ){
            throw new IllegalArgumentException("models.Jour.getJoursCSV : contenu est null ou vide");
        }

        ArrayList<String> ret = new ArrayList<String>();
        String tmp = "";
        tmp = String.join(";", contenu);
        ret.add(tmp);

        for (Jour j : Jour.jourList.values()) {
            tmp = j.toCSV(contenu);
            ret.add(tmp);
        }

        return ret;
    }

    // ----------------------------- attributes -----------------------------

    private String date;
    private int jour;
    private double temperatureMoyenne;

    // ----------------------------- constructor -----------------------------

    /**
     * Constructor of Jour
     * 
     * @param date the date of the jour to create (format : YYYY-MM-DD)
     * @param temperatureMoyenne the average temperature (must be between -40 and 60)
     * @param jour the day of the week ("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche")
     * @param vacances the holidays (null if there is no holidays)
     */
    public Jour(String date, int jour, double temperatureMoyenne) {
        if (!Jour.dateValide(date)) {
            throw new IllegalArgumentException("models.Jour.constructor : Le parametre date n'est pas valide");
        }
        if (Jour.jourList.containsKey(date)) {
            throw new IllegalArgumentException("models.Jour.constructor : Cette date est déjà créée");
        }

        this.date = date;
        try {
            this.setJour(jour);
            this.setTemperatureMoyenne(temperatureMoyenne);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("models.Jour.constructor : " + e.getMessage());
        }

        Jour.jourList.put(date, this);
    }
    
    /**
     * Constructor of Jour
     * 
     * @param date the date of the jour to create (format : YYYY-MM-DD)
     * @param temperatureMoyenne the average temperature (must be between -40 and 60)
     * @param jour the day of the week (0-6)
     * @param vacances the holidays (null if there is no holidays)
     */
    public Jour(String date, int jour, String vacances, double temperatureMoyenne) {
        if (!Jour.dateValide(date)) {
            throw new IllegalArgumentException("models.Jour.constructor : Le parametre date n'est pas valide");
        }
        if (Jour.jourList.containsKey(date)) {
            throw new IllegalArgumentException("models.Jour.constructor : Cette date est déjà créée");
        }

        this.date = date;
        try {
            this.setJour(jour);
            this.setTemperatureMoyenne(temperatureMoyenne);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("models.Jour.constructor : " + e.getMessage());
        }

        Jour.jourList.put(date, this);
    }

    // ----------------------------- setters -----------------------------

    /**
     * Set the day of the week with a string
     * valid string : "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"
     * 
     * @param jour the day of the week
     */
    public void setJour(int jour) {
        if ( jour < 1 || jour > 7) {
            throw new IllegalArgumentException(
                    "models.Jour.setJour : Le parametre jour n'est pas valide (1-7)");
        }
        this.jour = jour;
    }


    /**
     * Set the average temperature
     * 
     * The average temperature must be between -40 and 60
     * 
     * @param temperatureMoyenne the average temperature
     */
    public void setTemperatureMoyenne(double temperatureMoyenne) {
        if (temperatureMoyenne < -40 || temperatureMoyenne > 60) {
            throw new IllegalArgumentException(
                    "models.Jour.setTemperatureMoyenne : temperatureMoyenne abérante");
        }
        this.temperatureMoyenne = temperatureMoyenne;
    }

    // ----------------------------- getters -----------------------------

    /**
     * Get the date of the jour
     * 
     * @return the date of the jour (format : YYYY-MM-DD)
     */
    public String getDate() {
        return this.date;
    }

    public double getTemperatureMoyenne() {
        return this.temperatureMoyenne;
    }

    public int getJour() {
        return this.jour;
    }


    
    // ----------------------------- methods -----------------------------

    public int getJourDuMois(){
        String[] tmp = this.date.split("-");
        return Integer.parseInt(tmp[2]);
    } 

    public int getMois() {
        String[] tmp = this.date.split("-");
        return Integer.parseInt(tmp[1]);
    }

    public int getAnnee() {
        String[] tmp = this.date.split("-");
        return Integer.parseInt(tmp[0]);
    }

    public int compareTo(Jour jour) {
        int ret = 0;
        if (this.getAnnee() > jour.getAnnee()){
            ret = 1;

        } else if (this.getAnnee() < jour.getAnnee()){
            ret = -1;
        } else if (this.getMois() > jour.getMois()){
            ret = 1;
        } else if (this.getMois() < jour.getMois()){
            ret = -1;
        } else if (this.getJourDuMois() > jour.getJourDuMois()){
            ret = 1;
        } else if (this.getJourDuMois() < jour.getJourDuMois()){
            ret = -1;
        }
        return ret;
    }



    /**
     * Check if the day is a weekend
     * 
     * @return true if the day is a weekend, false otherwise
     */
    public boolean estWeekEnd(){
        boolean ret = false;
        if ( this.jour == 6 || this.jour == 7){
            ret = true;
        }
        return ret;
    }

    public String toCSV(ArrayList<String> contenu) {
        if (contenu == null || contenu.isEmpty() ){
            throw new IllegalArgumentException("models.Jour.toCSV : Le parametre contenu n'est pas valide");
        }

        String ret;
        ArrayList<String> tmp = new ArrayList<String>();
        
        if (contenu.contains("Date")) {
            tmp.add(this.date);
        }
        if (contenu.contains("Jour")) {
            tmp.add(this.jour + "");
        }
        if (contenu.contains("Temperature Moyenne")) {
            tmp.add(this.temperatureMoyenne + "");
        }

        ret = String.join(";", tmp);
        return ret;
    }

}
