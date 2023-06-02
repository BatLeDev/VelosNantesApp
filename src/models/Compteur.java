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

    public int getNumero() {
        return idCompteur;
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
}
