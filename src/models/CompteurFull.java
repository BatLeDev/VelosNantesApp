package models;

public class CompteurFull extends Compteur{

    public static String[] getHeaders() {
        return new String[] { "numero", "libelle", "direction", "observation", "latitude", "longitude", "idQuartier", "nomQuartier", "nombreJourReleve", "nombreTotalPassage", "moyennePassageParJour", "frequenceErreurs", "nbErreurs", "heureSouventFrequetee" };
    }
    
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
        
        super(numero, libelle, direction, observation, latitude, longitude );
        this.idQuartier = idQuartier;
        this.nomQuartier = nomQuartier;
        this.nombreJourReleve = nombreJourReleve;
        this.nombreTotalPassage = nombreTotalPassage;
        this.moyennePassageParJour = moyennePassageParJour;
        this.frequenceErreurs = frequenceErreurs;
        this.nbErreurs = nbErreurs;
        this.heureSouventFrequetee = heureSouventFrequetee;
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

    public String toJs() {
        return "[\"" + this.getDirection() + "\", " + this.getNumero() + ", " + this.moyennePassageParJour + ", " + this.frequenceErreurs + "]";
    }

}
