package models;

public class Compteur {
    private int numero;
    private String libelle;
    private String direction;
    private String observation;
    private double latitude;
    private double longitude;

    public Compteur(int numero, String libelle, String direction, String observation, double latitude,
        double longitude) {
        this.numero = numero;
        this.libelle = libelle;
        this.direction = direction;
        this.observation = observation;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public String toString() {
        return "Compteur " + numero + " : " + libelle + " (" + latitude + ", " + longitude + ")";
    }

    public int getNumero() {
        return numero;
    }

    public String getLibelle() {
        return libelle;
    }

}
