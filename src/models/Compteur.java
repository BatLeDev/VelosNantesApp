package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Compteur implements IModels {

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
     * Get the list of all compteurs
     * 
     * @return an ArrayList of all compteurs
     */
    public static ArrayList<Compteur> getAll() {
        return new ArrayList<Compteur>(Compteur.compteurList.values());
    }

    /**
     * Get a compteur by its id
     * 
     * @return the compteur object corresponding to the id
     */
    public static Compteur getCompteur(int id) {
        return Compteur.compteurList.get(id);
    }

    public static void removeCompteur(int id) {
        Compteur.compteurList.remove(id);
        ReleveJournalier.removeAllRelevesOfACompteur(id);
    
    }

    public static String[] getColumnsSimplified() {
        return new String[] { "numero", "libelle", "direction", "observation", "latitude", "longitude", "le quartier" };
    }

    public static String[] getColumns() {
        return new String[] { "numero", "libelle", "direction", "observation", "latitude", "longitude", "leQuartier" };
    }

    public static ArrayList<String> getCompteursCSV(ArrayList<String> contenu){
        if (contenu == null || contenu.isEmpty() ){
            throw new IllegalArgumentException("models.Compteur.getCompteursCSV : contenu est null ou vide");
        }
        
        ArrayList<String> ret = new ArrayList<String>();
        String tmp = "";
        tmp = String.join(";", contenu);
        ret.add(tmp);

        for (Compteur c : Compteur.compteurList.values()) {
            tmp = c.toCSV(contenu);
            ret.add(tmp);
        }
        return ret;
    }

    // ----------------------------- attributes -----------------------------

    private int numero;
    private String libelle;
    private String direction;
    private String observation;
    private double latitude;
    private double longitude;
    private int leQuartier;

/**
     * Constructor of the class Compteur with quartier object
     * libelle + sens = "libelle vers sens"
     * can launch an IllegalArgumentException in setters if the parameters arn't valid
     * 
     * @param id         an integer representing the (unique) id of the compteur (positive)
     * @param libelle    a String representing the libelle of the compteur (not null or empty)
     * @param direction  a String representing the sens of the compteur (not null or empty)
     * @param latitude   a double representing the latitude of the compteur
     * @param longitude  a double representing the longitude of the compteur
     * @param idQuartier an integer which is the code of the Quartier, representing the quartier of the compteur
     */
    public Compteur(int id, String libelle, String direction, String observation, double latitude, double longitude,int idQuartier) {
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
            this.setQuartier(idQuartier);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("models.Compteur.constructor : " + e.getMessage());
        }

        // add the compteur to the compteurList
        Compteur.compteurList.put(id, this);
    }

    public String toString() {
        return "Compteur " + numero + " : " + libelle + " (" + latitude + ", " + longitude + ") " + direction + " " + observation + " " + leQuartier;
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

    public int getLeQuartier(){
        return this.leQuartier;
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
     * @param direction a String representing the sens of the compteur (not null or empty)
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


    /**
     * Setter for the quartier of the compteur
     * Update the compteur list of the old and new quartier
     * If the idQuartier is 0, the compteur is removed from the quartier and the Quartier is undefined
     * @param idQuartier an integer representing the id of the quartier of the compteur (positive)
     */
    public void setQuartier(int idQuartier) {
        if (idQuartier < 0) {
            throw new IllegalArgumentException(
                    "models.Compteur.setQuartier : L'id du quartier ne peut pas être négatif");
        } 

        if (idQuartier != 0) {
            //verification que le quartier existe
            Quartier q = Quartier.getQuartier(idQuartier);
            if (q == null) {
                throw new IllegalArgumentException(
                        "models.Compteur.setQuartier : L'id du quartier ne correspond à aucun quartier" + idQuartier);
            }
            if (this.leQuartier != 0) {
                Quartier oldQuartier = Quartier.getQuartier(this.leQuartier);
                oldQuartier.removeCompteur(this.getNumero());
            }
            q.addCompteur(this.getNumero());
        }

        this.leQuartier = idQuartier;
    }

    public String toCSV(ArrayList<String> contenu){
        if (contenu == null || contenu.isEmpty()){
            throw new IllegalArgumentException("models.Compteur.toCSV : Le contenu ne peut pas être null ou vide");
        }
        String ret;
        ArrayList<String> tmp = new ArrayList<String>();

        if(contenu.contains("numero")){
            tmp.add(this.getNumero()+"");
        }
        if(contenu.contains("libelle")){
            tmp.add(this.getLibelle());
        }
        if(contenu.contains("direction")){
            tmp.add(this.getDirection());
        }
        if(contenu.contains("observation")){
            tmp.add(this.getObservation());
        }
        if(contenu.contains("latitude")){
            tmp.add(this.getLatitude()+"");
        }
        if(contenu.contains("longitude")){
            tmp.add(this.getLongitude()+"");
        }
        if(contenu.contains("le quartier")){
            tmp.add(this.getLeQuartier()+"");
        }

        ret = String.join(";", tmp);
        return ret;
    }


}