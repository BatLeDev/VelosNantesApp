package models;

/**
 * This class represents a compteur with all its stats, use principally for the map
 */
public class CompteurFull {
    // ----------------------------- attributes -----------------------------
    
    private int numero;
    private String libelle;
    private String direction;
    private String observation;
    private double latitude;
    private double longitude;
    private int idQuartier;
    private String nomQuartier;
    private int nombreJourReleve;
    private int nombreTotalPassage;
    private double moyennePassageParJour;
    private double frequenceErreurs;
    private int nbErreurs;
    private String heureSouventFrequetee;

    /**
     * Constructor
     * 
     * @param numero the id of the capteur
     * @param libelle the name of the capteur
     * @param direction the direction of the capteur
     * @param observation the observation of the capteur (Relevage manuelle)
     * @param latitude the latitude of the capteur
     * @param longitude the longitude of the capteur
     * @param idQuartier the id of the quartier of the capteur
     * @param nomQuartier the name of the quartier of the capteur 
     * @param nombreJourReleve the number of days the capteur has been releved 
     * @param nombreTotalPassage the total number of passage of the capteur 
     * @param moyennePassageParJour the average number of passage per day of the capteur
     * @param frequenceErreurs the frequency of errors of the capteur (nombreJourReleve/nbErreurs)
     * @param nbErreurs the number of errors of the capteur
     * @param heureSouventFrequetee the hour when they are the most passage
     */
    public CompteurFull(int numero, String libelle, String direction, String observation, double latitude,
            double longitude, int idQuartier, String nomQuartier, int nombreJourReleve,
            int nombreTotalPassage, double moyennePassageParJour, double frequenceErreurs,
            int nbErreurs, String heureSouventFrequetee) {
        
        this.numero = numero;
        this.libelle = libelle;
        this.direction = direction;
        this.observation = observation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.idQuartier = idQuartier;
        this.nomQuartier = nomQuartier;
        this.nombreJourReleve = nombreJourReleve;
        this.nombreTotalPassage = nombreTotalPassage;
        this.moyennePassageParJour = moyennePassageParJour;
        this.frequenceErreurs = frequenceErreurs;
        this.nbErreurs = nbErreurs;
        this.heureSouventFrequetee = heureSouventFrequetee;
    }


    // ----------------- Getters ----------------- //
    public int getNumero() {
        return numero;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDirection() {
        return direction;
    }

    public String getObservation() {
        return observation;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getIdQuartier() {
        return idQuartier;
    }

    public String getNomQuartier() {
        return nomQuartier;
    }

    public int getNombreJourReleve() {
        return nombreJourReleve;
    }

    public int getNombreTotalPassage() {
        return nombreTotalPassage;
    }

    public double getMoyennePassageParJour() {
        return moyennePassageParJour;
    }

    public double getFrequenceErreurs() {
        return frequenceErreurs;
    }

    public int getNbErreurs() {
        return nbErreurs;
    }

    public String getHeureSouventFrequetee() {
        return heureSouventFrequetee;
    }

    public String toString() {
        return "CompteurFull [numero=" + this.getNumero() + ", libelle=" + this.getLibelle() + ", direction=" + this.getDirection() + ", observation="
                + this.getObservation() + ", latitude=" + this.getLatitude() + ", longitude=" + this.getLongitude() + ", idQuartier=" + idQuartier
                + ", nomQuartier=" + nomQuartier + ", nombreJourReleve=" + nombreJourReleve + ", nombreTotalPassage="
                + nombreTotalPassage + ", moyennePassageParJour=" + moyennePassageParJour + ", frequenceErreurs="
                + frequenceErreurs + ", nbErreurs=" + nbErreurs + ", heureSouventFrequetee=" + heureSouventFrequetee
                + "]";
    }

    /**
     * Convert the compteur to a javascript array
     * @return the compteur in a javascript array
     */
    public String toJs() {
        return "[\"" + this.getDirection() + "\", " + this.getNumero() + ", " + this.moyennePassageParJour + ", " + this.frequenceErreurs + "]";
    }

}
