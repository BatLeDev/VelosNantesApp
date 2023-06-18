package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Quartier {

// ----------------------------- static attributes -----------------------------

    /**
     * HashMap containing all quartier's objects
     * The key is the id of the quartier
     * The value is the quartier object
     * This HashMap allow to get a quartier by his id and verify uniqueness of id
     */
    protected static HashMap<Integer,Quartier> quartierList = new HashMap<Integer,Quartier>();

    // ----------------------------- static methods -----------------------------

    /**
     * Get a quartier by his id
     * 
     * @param id the id of the quartier to get
     * @return the quartier object corresponding to the id, null if not found
     */
    public static Quartier getQuartier(int id) {
        return Quartier.quartierList.get(id); // Quartier if found, null if not
    }

    /**
     * Delete a quartier by its id
     * 
     * @param id the id of the quartier to delete
     * @return the quartier object corresponding to the id, null if not found
     */
    public static Quartier deleteQuartier(int id) {
        return Quartier.quartierList.remove(id); // Quartier if found, null if not
    }


    public static String[] getHeaders() {
        return new String[] { "idQuartier", "nomQuartier", "lgPisteCyclable" };
    }



    private int idQuartier;
    private String nomQuartier;
    private double lgPisteCyclable;
    private ArrayList<Integer> compteurIdList;

    public Quartier(int idQuartier, String nomQuartier, double lgPisteCyclable) {
        this.idQuartier = idQuartier;
        this.nomQuartier = nomQuartier;
        this.lgPisteCyclable = lgPisteCyclable;

        this.compteurIdList = new ArrayList<Integer>();
    }

    public int getIdQuartier() {
        return idQuartier;
    }

    public String getNomQuartier() {
        return nomQuartier;
    }

    public double getLgPisteCyclable() {
        return lgPisteCyclable;
    }

    public ArrayList<Integer> getCompteurIdList() {
        return compteurIdList;
    }
    
    /**
     * Add a compteur to the quartier
     * /!\ the quartierId of the compteur object is not updated in this method
     * 
     * @param idCompteur an int representing the id of compteur to save (positive)
     */
    protected void addCompteur(int idCompteur) {
        if (idCompteur < 0) {
            throw new IllegalArgumentException("models.Quartier.addCompteur : L'id du compteur doit être positif");
        }
        if (!this.compteurIdList.contains(idCompteur)) { // if the compteur is not already in the list
            this.compteurIdList.add(idCompteur);
        }
    }

    /**
     * Remove a compteur from the quartier
     * /!\ the quartierId of the compteur is not updated in this method
     * 
     * @param idCompteur an int representing the id of compteur to remove (positive)
     */
    protected void removeCompteur(int idCompteur) {
        if (idCompteur < 0) {
            throw new IllegalArgumentException("models.Quartier.removeCompteur : L'id du compteur doit être positif");
        }
        int indice = this.compteurIdList.indexOf(idCompteur);
        if (indice != -1) { // if the compteur is in the list
            this.compteurIdList.remove(indice);
        }
    }

    /**
     * Get a String representing the quartier
     * 
     * @return "nomQuartier#idQuartier : nbCompteurs = len(compteurList), longueurPiste = lgPisteCyclable"
     */
    public String toString() {
        String ret = this.nomQuartier + "#" + this.idQuartier + " : nbCompteurs = " + this.compteurIdList.size()
                + ", longueurPiste = " + this.lgPisteCyclable;
        return ret;
    }

}
