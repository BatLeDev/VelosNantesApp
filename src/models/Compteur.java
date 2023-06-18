package models;

import java.util.HashMap;

public class Compteur {

// ----------------------------- static attributes -----------------------------

    /**
     * HashMap containing all compteurs objects
     * In key it's the id of the compteur
     * In value it's the compteur object
     * This HashMap allow to get a compteur by his id and verify uniqueness of id
     */
    protected static HashMap<Integer,Compteur> compteurList = new HashMap<Integer,Compteur>();

    // ----------------------------- static methods -----------------------------

    /**
     * Get a compteur by its id
     * 
     * @return the compteur object corresponding to the id
     */
    public static Compteur getCompteur(int id) {
        return Compteur.compteurList.get(id);
    }


    public static String[] getHeaders() {
        return new String[] { "numero", "libelle", "direction", "observation", "latitude", "longitude" };
    }


    // ----------------------------- attributes -----------------------------

    private int numero;
    private String libelle;
    private String direction;
    private String observation;
    private double latitude;
    private double longitude;

/**
     * Constructor of the class Compteur with quartier object
     * libelle + sens = "libelle vers sens"
     * can launch an IllegalArgumentException in setters if the parameters arn't valid
     * 
     * @param id        an integer representing the (unique) id of the compteur (positive)
     * @param libelle   a String representing the libelle of the compteur (not null or empty)
     * @param sens      a String representing the sens of the compteur (not null or empty)
     * @param latitude  a double representing the latitude of the compteur
     * @param longitude a double representing the longitude of the compteur
     * @param quartier  a Quartier object representing the quartier of the compteur
     */
    public Compteur(int id, String libelle, String direction,String observation, double latitude, double longitude) {
        if (id < 0 || compteurList.containsKey(id)) {
            throw new IllegalArgumentException("models.Compteur.constructor : l'id est invalide ( < 0 ou deja existant )");
        }

        this.numero = id;
        try {
            this.setLibelle(libelle);
            this.setDirection(direction);
            this.setObservation(observation);
            this.setLatitude(latitude);
            this.setLongitude(longitude);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("models.Compteur.constructor : " + e.getMessage());
        }

        // add the compteur to the compteurList
        Compteur.compteurList.put(id, this);
    }

    public String toString() {
        return "Compteur " + numero + " : " + libelle + " (" + latitude + ", " + longitude + ")";
    }

    public int getNumero() {
        return this.numero;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public String getDirection() {
        return this.direction;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getObservation(){
        return this.observation;
    }

    // ----------------------------- setters -----------------------------

    /**
     * Setter for the libelle of the compteur
     * 
     * @param libelle a String representing the libelle of the compteur (not null or empty)
     */
    public void setLibelle(String libelle) {
        if (libelle == null || libelle.isEmpty()) {
            throw new IllegalArgumentException("models.Compteur.setLibelle : Le libelle ne peut pas être null ou vide");
        }
        this.libelle = libelle;
    }

    /**
     * Setter for the sens of the compteur
     * 
     * @param sens a String representing the sens of the compteur (not null or empty)
     */
    public void setDirection(String direction) {
        if (direction == null || direction.isEmpty()) {
            throw new IllegalArgumentException("models.Compteur.setSens : Le sens ne peut pas être null ou vide");
        }
        this.direction = direction;
    }

    /**
     * Setter for the observation of the compteur
     * 
     * @param observation a String representing the observation of the compteur (not null or empty)
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

    /**
     * Setter for the latitude of the compteur
     * 
     * @param latitude a double representing the latitude of the compteur
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    /**
     * Setter for the longitude of the compteur
     * 
     * @param longitude a double representing the longitude of the compteur
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}