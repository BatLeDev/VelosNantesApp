package models;

import java.util.Arrays;

public class ReleveJournalier {
    

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
     * @param presenceAnomalie the presenceAnomalie of the releve
     */
    public ReleveJournalier(int leCompteur , String leJour , int[] relevesHeures , String presenceAnomalie){
        this.leCompteur = leCompteur;
        this.leJour = leJour;

        this.setRelevesHeures(relevesHeures);
        this.setPresenceAnomalie(presenceAnomalie);

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
        this.relevesHeures = relevesHeures;
    }

    /**
     * Set the presenceAnomalie of the releve
     * 
     * @param presenceAnomalie the presenceAnomalie of the releve (null or in the
     *                         list of TYPE_ANOMALIE)
     */
    public void setPresenceAnomalie(String presenceAnomalie) {
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
}
