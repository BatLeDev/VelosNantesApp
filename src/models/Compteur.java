package models;

public class Compteur {
    private int idCompteur;
    private String libelle;
    private String direction;
    private String observation;
    private double latitude;
    private double longitude;

    public Compteur(int idCompteur, String libelle, String direction, String observation, double latitude,
            double longitude) {
        this.idCompteur = idCompteur;
        this.libelle = libelle;
        this.direction = direction;
        this.observation = observation;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public String toString() {
        return "Compteur " + idCompteur + " : " + libelle + " (" + latitude + ", " + longitude + ")";
    }

}
