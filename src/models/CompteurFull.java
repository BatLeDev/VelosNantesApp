package models;

public class CompteurFull {
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
        return "CompteurFull [numero=" + numero + ", libelle=" + libelle + ", direction=" + direction + ", observation="
                + observation + ", latitude=" + latitude + ", longitude=" + longitude + ", idQuartier=" + idQuartier
                + ", nomQuartier=" + nomQuartier + ", nombreJourReleve=" + nombreJourReleve + ", nombreTotalPassage="
                + nombreTotalPassage + ", moyennePassageParJour=" + moyennePassageParJour + ", frequenceErreurs="
                + frequenceErreurs + ", nbErreurs=" + nbErreurs + ", heureSouventFrequetee=" + heureSouventFrequetee
                + "]";
    }

    public String toJs() {
        return "[\"" + direction + "\", " + numero + ", " + moyennePassageParJour + ", " + frequenceErreurs + "]";
    }

}
