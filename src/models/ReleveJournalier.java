package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ReleveJournalier implements IModels {
    
    // ----------------------------- static attributes -----------------------------

    // These two HashMap are used to preserve the unicity of a ReleveJournalier
    /**
     * The list of all the ReleveJournalier associated to a Compteur, if an id of Compteur is not in this HashMap, it means that there aren't any ReleveJournalier associated to this Compteur
     */
    private static HashMap<Integer, ArrayList<ReleveJournalier>> releveComptList = new HashMap<Integer, ArrayList<ReleveJournalier>>();
    
    /**
     * The list of all the ReleveJournalier associated to a Jour, if a date of Jour is not in this HashMap, it means that there aren't any ReleveJournalier associated to this Jour
     */
    private static HashMap<String, ArrayList<ReleveJournalier>> releveJourList = new HashMap<String, ArrayList<ReleveJournalier>>();
    
    private static final String[] TYPE_ANOMALIE = {"Faible","Forte"};

    // ----------------------------- static methods -----------------------------

    /**
     * Check if the type is valid (null or in the list of TYPE_ANOMALIE)
     * @param type the type to check
     * @return true if the type is valid, false otherwise
     */
    private static boolean typeAnomalieValide(String type) {
        boolean ret = true;

        if (type != null) {
            ret = false;
            int i = 0;
            while (i < TYPE_ANOMALIE.length && !ret) {
                if (type.equals(TYPE_ANOMALIE[i])) {
                    ret = true;
                }
                i++;
            }
        }
        return ret;
    }

    /**
     * Check if the relevesHeures is valid (not null, length = 24, all values >= 0)
     * @param relevesHeures the relevesHeures to check
     * @return true if the relevesHeures is valid, false otherwise
     */
    private static boolean relevesHeuresValide(int[] relevesHeures) {
        boolean ret = false;

        if (relevesHeures != null && relevesHeures.length == 24) { // Check valid length
            ret = true;
            int i = 0;
            while (i < relevesHeures.length && ret) { // Check all values >= 0
                if (relevesHeures[i] < 0) {
                    ret = false;
                }
                i++;
            }
        }
        return ret;
    }

    /**
     * Add a ReleveJournalier to the HashMap releveComptList and releveJourList
     * 
     * @param releve the ReleveJournalier to add
     */
    private static void addReleveJournalier(ReleveJournalier releve) {
        int idCompteur = releve.getLeCompteur();
        String date = releve.getLeJour();

        if (releveComptList.get(idCompteur) == null) {
            releveComptList.put(idCompteur, new ArrayList<ReleveJournalier>());
        }
        releveComptList.get(idCompteur).add(releve);

        if (releveJourList.get(date) == null) {
            releveJourList.put(date, new ArrayList<ReleveJournalier>());
        }
        releveJourList.get(date).add(releve);
    }

    /**
     * Get the ReleveJournalier by the date of its Jour and the id of its Compteur
     * 
     * @param date       the date of the Jour associated (YYYY-MM-DD)
     * @param idCompteur the id of the Compteur associated
     * @return the a ReleveJournalier, null if it doesn't exist 
     */
    public static ReleveJournalier getReleveJournalier(String date, int idCompteur) {
        ReleveJournalier ret = null;
        ArrayList<ReleveJournalier> listJour = getRelevesByJour(date);
        ArrayList<ReleveJournalier> listCompt = getRelevesByCompteur(idCompteur);

        if (listJour != null && listCompt != null) {
            Iterator<ReleveJournalier> itJour = listJour.iterator();
            boolean trouve = false;
            while (itJour.hasNext() && !trouve) {

                Iterator<ReleveJournalier> itCompt = listCompt.iterator();
                ReleveJournalier tmp = itJour.next();
                while (itCompt.hasNext() && !trouve) {
                    if (tmp == itCompt.next()) {
                        trouve = true;
                        ret = tmp;
                    }
                }
            }
        }

        return ret;
    }

    /**
     * Get an ArrayList of the ReleveJournalier associated to a Jour
     * 
     * @param date the date of the Compteur (YYYY-MM-DD)
     * @return an ArrayList of ReleveJournalier, null if there aren't any ReleveJournalier for this Jour
     */
    public static ArrayList<ReleveJournalier> getRelevesByJour(String date){
        ArrayList<ReleveJournalier> tmp = releveJourList.get(date);
        ArrayList<ReleveJournalier> ret = null;

        if (tmp != null) {
            ret = new ArrayList<ReleveJournalier>(tmp);
        }
        return ret;
    }

    /**
     * Get an ArrayList of the ReleveJournalier associated to a Compteur
     * @param idCompteur the id of the Compteur
     * @return an ArrayList of ReleveJournalier, null if there aren't any ReleveJournalier for this Compteur
     */
    public static ArrayList<ReleveJournalier> getRelevesByCompteur(int idCompteur) {
        ArrayList<ReleveJournalier> tmp = releveComptList.get(idCompteur);
        ArrayList<ReleveJournalier> ret = null;

        if (tmp != null) {
            ret =  new ArrayList<ReleveJournalier>(tmp);
        }
        return ret;
    }


    /**
     * Remove a ReleveJournalier saved by the date of the Jour associated and the id of the Compteur associatedd.
     * If the ReleveJournalier doesn't exist, do nothing
     * 
     * @param date the date of the Jour associated (YYYY-MM-DD)
     * @param idCompteur the id of the Compteur associatedd
     * @return the ReleveJournalier removed, null if it doesn't exist
     */
    public static ReleveJournalier removeReleveJournalier(String date, int idCompteur) {
        ReleveJournalier releve = getReleveJournalier(date, idCompteur);
        if (releve != null) {
            releveComptList.get(idCompteur).remove(releve);
            releveJourList.get(date).remove(releve);
        }
        return releve;
    }

    /**
     * Remove all the ReleveJournalier associated to this Compteur
     * If the ReleveJournalier doesn't exist, do nothing
     * 
     * @param date the date of the Jour associated (YYYY-MM-DD)
     * @return an ArrayList of the ReleveJournalier removed, null if there aren't any ReleveJournalier for this Jour
     */
    public static ArrayList<ReleveJournalier> removeAllRelevesOfAJour(String date) {
        ArrayList<ReleveJournalier> releves = getRelevesByJour(date);
        if (releves != null) {
            for (ReleveJournalier releve : releves) {
                removeReleveJournalier(date, releve.getLeCompteur());
            }
        }
        return releves;
    }

    /**
     * Remove all the ReleveJournalier associated to this Compteur
     * If the ReleveJournalier doesn't exist, do nothing
     * 
     * @param idCompteur the id of the Compteur associated
     * @return an ArrayList of the ReleveJournalier removed, null if there aren't any ReleveJournalier for this Compteur
     */
    public static ArrayList<ReleveJournalier> removeAllRelevesOfACompteur(int idCompteur) {
        ArrayList<ReleveJournalier> releves = getRelevesByCompteur(idCompteur);
        if (releves != null) {
            for (ReleveJournalier releve : releves) {
                ReleveJournalier.removeReleveJournalier(releve.getLeJour(), idCompteur);
            }
        }
        return releves;
    }

    /**
     * Get all the RelevetJournalier saved
     * 
     * @return an ArrayList of all the ReleveJournalier saved
     */
    public static ArrayList<ReleveJournalier> getAllReleves() {
        ArrayList<ReleveJournalier> ret = new ArrayList<ReleveJournalier>();
        for (ArrayList<ReleveJournalier> releves : releveJourList.values()) {
            ret.addAll(releves);
        }
        return ret;
    }

    /**
     * Get all the ReleveJournalier for a day (1-7)
     * 
     * @param day the day of the week (1-7)
     * @param dayMin the minimum day of the week (YYYY-MM-DD)
     * @param dayMax the maximum day of the week (YYYY-MM-DD)
     * @return an ArrayList of all the ReleveJournalier for a day
     */
    public static ArrayList<ReleveJournalier> getAllRelevesByDayOfAWeek(int day, String dayMin, String dayMax, ArrayList<Integer> compteurs) {
        if (day < 1 || day > 7 || dayMin == null || dayMax == null)  {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay day must be between 1 and 7");
        }
        Jour jourMin = Jour.getJour(dayMin);
        Jour jourMax = Jour.getJour(dayMax);
        if (jourMin == null || jourMax == null) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay dayMin or dayMax doesn't exist");
        }
        if (jourMin.compareTo(jourMax) > 0) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay dayMin must be before dayMax");
        }

        if (compteurs == null) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay compteurs must be not null");
        }
        ArrayList<ReleveJournalier> ret = new ArrayList<ReleveJournalier>();
        Set<String> keys = releveJourList.keySet();
        for (String key : keys) {
            Jour jour = Jour.getJour(key);
            if (jour.getJour() == day && jour.compareTo(jourMin) >= 0 && jour.compareTo(jourMax) <= 0 ) {
                ArrayList<ReleveJournalier> releves = releveJourList.get(key);
                for (ReleveJournalier releve : releves) {
                    if (compteurs.contains(releve.getLeCompteur())) {
                        ret.add(releve);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Get all the ReleveJournalier for a day (1-31)
     * 
     * @param day the day of the month (1-31)
     * @param dayMin the minimum day of the month (YYYY-MM-DD)
     * @param dayMax the maximum day of the month (YYYY-MM-DD)
     * @return an ArrayList of all the ReleveJournalier for a day
     */
    public static ArrayList<ReleveJournalier> getAllRelevesByDayOfAMonth(int day, String dayMin, String dayMax, ArrayList<Integer> compteurs) {
        if (day < 1 || day > 31 || dayMin == null || dayMax == null)  {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay day must be between 1 and 31");
        }
        Jour jourMin = Jour.getJour(dayMin);
        Jour jourMax = Jour.getJour(dayMax);
        if (jourMin == null || jourMax == null) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay dayMin or dayMax doesn't exist");
        }
        if (jourMin.compareTo(jourMax) > 0) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay dayMin must be before dayMax");
        }
        if (compteurs == null) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay compteurs must be not null");
        }

        ArrayList<ReleveJournalier> ret = new ArrayList<ReleveJournalier>();
        Set<String> keys = releveJourList.keySet();
        for (String key : keys) {
            Jour jour = Jour.getJour(key);
            if (jour.getJourDuMois() == day && jour.compareTo(jourMin) >= 0 && jour.compareTo(jourMax) <= 0) {
            ArrayList<ReleveJournalier> releves = releveJourList.get(key);
                for (ReleveJournalier releve : releves) {
                    if (compteurs.contains(releve.getLeCompteur())) {
                        ret.add(releve);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Get all the ReleveJournalier for a month (1-12)
     * 
     * @param the month of the year (1-12)
     * 
     * @return an ArrayList of all the ReleveJournalier for a month
     */
    public static ArrayList<ReleveJournalier> getAllRelevesByMonth (int month, String dayMin, String dayMax, ArrayList<Integer> compteurs) {
        if (month < 1 || month > 12 || dayMin == null || dayMax == null)  {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay day must be between 1 and 31");
        }
        Jour jourMin = Jour.getJour(dayMin);
        Jour jourMax = Jour.getJour(dayMax);
        if (jourMin == null || jourMax == null) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay dayMin or dayMax doesn't exist");
        }
        if (jourMin.compareTo(jourMax) > 0) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay dayMin must be before dayMax");
        }
        if (compteurs == null) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay compteurs must be not null");
        }

        ArrayList<ReleveJournalier> ret = new ArrayList<ReleveJournalier>();
        Set<String> keys = releveJourList.keySet();
        for (String key : keys) {
            Jour jour = Jour.getJour(key);
            if (jour.getMois() == month && jour.compareTo(jourMin) >= 0 && jour.compareTo(jourMax) <= 0) {
                ArrayList<ReleveJournalier> releves = releveJourList.get(key);
                for (ReleveJournalier releve : releves) {
                    if (compteurs.contains(releve.getLeCompteur())) {
                        ret.add(releve);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Get all the ReleveJournalier for a year
     * 
     * @param year the year
     * @return an ArrayList of all the ReleveJournalier for a year
     */
    public static ArrayList<ReleveJournalier> getAllRelevesByYear (int year, String dayMin, String dayMax, ArrayList<Integer> compteurs) {
        if (year < 1 || dayMin == null || dayMax == null)  {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay day must be between 1 and 31");
        }
        Jour jourMin = Jour.getJour(dayMin);
        Jour jourMax = Jour.getJour(dayMax);
        if (jourMin == null || jourMax == null) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay dayMin or dayMax doesn't exist");
        }
        if (jourMin.compareTo(jourMax) > 0) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay dayMin must be before dayMax");
        }
        if (compteurs == null) {
            throw new IllegalArgumentException("models.ReleveJournalier.getAllRelevesByDay compteurs must be not null");
        }

        ArrayList<ReleveJournalier> ret = new ArrayList<ReleveJournalier>();
        Set<String> keys = releveJourList.keySet();
        for (String key : keys) {
            Jour jour = Jour.getJour(key);
            if (jour.getAnnee() == year && jour.compareTo(jourMin) >= 0 && jour.compareTo(jourMax) <= 0) {
                ArrayList<ReleveJournalier> releves = releveJourList.get(key);
                for (ReleveJournalier releve : releves) {
                    if (compteurs.contains(releve.getLeCompteur())) {
                        ret.add(releve);
                    }
                }
            }
        }
        return ret;
    }

    public static String[] getColumnsSimplified(){
        return new String[]{"Compteur", "Jour", "RelevesHeures", "PresenceAnomalie", "nbPassageTotal"};
    }

    public static String[] getColumns(){
        return new String[]{"Compteur", "Jour", "heure00", "heure01", "heure02", "heure03", "heure04", 
        "heure05", "heure06", "heure07", "heure08", "heure09", "heure10", "heure11", "heure12",
        "heure13", "heure14", "heure15", "heure16", "heure17", "heure18", "heure19", "heure20", 
        "heure21", "heure22", "heure23", "PresenceAnomalie"};
    }

    public static ArrayList<String> getRelevesCSV (ArrayList<String> contenu){
        if (contenu == null || contenu.isEmpty() ){
            throw new IllegalArgumentException("models.ReleveJournalier.getRelevesCSV : Le parametre contenu n'est pas valide");
        }

        ArrayList<String> ret = new ArrayList<String>();
        ret.add(String.join(";", contenu));

        for (ReleveJournalier releve : ReleveJournalier.getAllReleves()) {
            ret.add(releve.toCSV(contenu));
        }
        return ret;
    }

    // ----------------------------- attributes -----------------------------
    private int leCompteur;
    private String leJour;
    private int[] relevesHeures;
    private String presenceAnomalie;

    /** 
     * Constructor of the class ReleveJournalier
     * can launch an IllegalArgumentException if the parameters are not valid
     * 
     * @param leCompteur the id of the compteur (positive)
     * @param leJour the day of the releve (not null or empty)
     * @param relevesHeures the relevesHeures of the releve (not null, length = 24, all values >= 0)
     * @param presenceAnomalie the presenceAnomalie of the releve (null or in the list of TYPE_ANOMALIE)
     */
    public ReleveJournalier(int leCompteur , String leJour , int[] relevesHeures , String presenceAnomalie){
        if (Compteur.getCompteur(leCompteur) == null){
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : Le parametre leCompteur n'est pas valide");
        }
        if (Jour.getJour(leJour) == null) {
            throw new IllegalArgumentException(
                    "models.ReleveJournalier.constructor : Le parametre leJour n'est pas valide");
        }
        if (ReleveJournalier.getReleveJournalier(leJour, leCompteur) != null) {
            throw new IllegalArgumentException(
                    "models.ReleveJournalier.constructor : Le releveJournalier existe deja");
        }
        this.leCompteur = leCompteur;
        this.leJour = leJour;

        try {
            this.setRelevesHeures(relevesHeures);
            this.setPresenceAnomalie(presenceAnomalie);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : " + e.getMessage());
        }
        ReleveJournalier.addReleveJournalier(this);
    }

    // ----------------------------- setters -----------------------------

    /**
     * Set the relevesHeures of the releve
     * can launch an IllegalArgumentException if the parameters are not valid
     * 
     * @param relevesHeures the relevesHeures of the releve (not null, length = 24,
     *                      all values >= 0)
     */
    public void setRelevesHeures(int[] relevesHeures) {
        if (!ReleveJournalier.relevesHeuresValide(relevesHeures)) {
            throw new IllegalArgumentException(
                    "models.ReleveJournalier.setRelevesHeures : Le parametre relevesHeures n'est pas valide");
        }
        this.relevesHeures = relevesHeures;
    }

    /**
     * Set the presenceAnomalie of the releve
     * 
     * @param presenceAnomalie the presenceAnomalie of the releve (null or in the
     *                         list of TYPE_ANOMALIE)
     */
    public void setPresenceAnomalie(String presenceAnomalie) {
        if (!ReleveJournalier.typeAnomalieValide(presenceAnomalie)) {
            throw new IllegalArgumentException(
                    "models.ReleveJournalier.setPresenceAnomalie : Le parametre presenceAnomalie n'est pas valide");
        }
        this.presenceAnomalie = presenceAnomalie;
    }

    /**
     * Set the relevesHeures of the releve at the hour
     * can launch an IllegalArgumentException if the parameters are not valid
     * 
     * @param heure     the hour of the releve (between 0 and 23)
     * @param nbPassage the relevesHeures of the releve at the hour (positive or 0)
     */
    public void setPassageHoraire(int heure, int nbPassage) {
        if ((heure < 0) || (heure > 23)) {
            throw new IllegalArgumentException(
                    "models.ReleveJournalier.setPassageHoraire : Le parametre heure n'est pas valide");
        }
        if (nbPassage < 0) {
            throw new IllegalArgumentException(
                    "models.ReleveJournalier.setPassageHoraire : Le parametre nbPassage n'est pas valide");
        }
        relevesHeures[heure] = nbPassage;
    }
    
    // ----------------------------- getters -----------------------------

    /**
     * Get the compteur of the releve
     * @return the compteur id of the releve
     */
    public int getLeCompteur() {
        return leCompteur;
    }

    /**
     * Get the day of the releve
     * @return the date of the releve
     */
    public String getLeJour() {
        return leJour;
    }

    /**
     * Get the relevesHeures of the releve
     * 
     * @return an array of int representing the relevesHeures of the releve
     */
    public int[] getRelevesHeures() {
        int[] tab = new int[24];
        for (int i = 0; i < 24; i++){
            tab[i] = relevesHeures[i];
        }
        return tab;
    }

    /**
     * Get the presenceAnomalie of the releve
     * @return null, "Faible" or "Forte"
     */
    public String getPresenceAnomalie() {
        return presenceAnomalie;
    }

    // ----------------------------- methods -----------------------------

    /**
     * Get the relevesHeures of the releve at the hour
     * can launch an IllegalArgumentException if the parameters are not valid
     * 
     * @param heure the hour to know (between 0 and 23)
     * @return the number of passage of the releve at this hour
     */
    public int getPassageHoraire(int heure) {
        if ((heure < 0) || (heure > 23)) {
            throw new IllegalArgumentException(
                    "models.ReleveJournalier.getPassageHoraire : Le parametre heure n'est pas valide");
        }
        return relevesHeures[heure];
    }

    /**
     * Get the total of nbPassage of the releve
     * @return the total relevesHeures of the releve
     */
    public int getNbPassageTotal() {
        int ret = 0;
        for (int i = 0; i < relevesHeures.length; i++) {
            ret += relevesHeures[i];
        }
        return ret;
    }

    /**
     * Get the average of nbPassage by hour of the releve
     * @return the average relevesHeures of the releve
     */
    public double getMoyennePassageByHour() {
        double ret = 0;
        int nbPassage = 0;
        for (int i = 0; i < relevesHeures.length; i++) {
            ret += relevesHeures[i] * i;
            nbPassage += relevesHeures[i];
        }
        if (nbPassage != 0) {
            ret = ret / nbPassage;
        }
        return ret;
    }
    
    /**
     * To String method
     * format : "models.ReleveJournalier.toString : Compteur : " + leCompteur + ", Jour : " + leJour + ", RelevesHeures : " + relevesHeures + ", PresenceAnomalie : " + presenceAnomalie
     * @return the string
     */
    public String toString() {
        String ret = "[";
        ret += "Compteur : " + leCompteur + ", ";
        ret += "Jour : " + leJour + ", ";
        ret += "RelevesHeures : " + Arrays.toString(relevesHeures) + ", ";
        ret += "PresenceAnomalie : " + presenceAnomalie;
        ret += "]";
        return ret;
    }


    public String toCSV (ArrayList<String> contenu){
        if (contenu == null || contenu.isEmpty() ){
            throw new IllegalArgumentException("models.ReleveJournalier.toCSV : Le parametre contenu n'est pas valide");
        }

        String ret;
        ArrayList<String> tmp = new ArrayList<String>();

        if (contenu.contains("Compteur")){
            tmp.add(this.leCompteur + "");
        }
        if (contenu.contains("Jour")){
            tmp.add(this.leJour);
        }
        if (contenu.contains("RelevesHeures")){
            tmp.add(Arrays.toString(this.relevesHeures));
        }
        if (contenu.contains("PresenceAnomalie")){
            tmp.add(this.presenceAnomalie);
        }
        if (contenu.contains("nbPassageTotal")){
            tmp.add(this.getNbPassageTotal() + "");
        }

        ret = String.join(";", tmp);
        return ret;
    }

}
