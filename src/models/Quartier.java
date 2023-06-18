package models;

import java.util.ArrayList;

public class Quartier {

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
    
    protected void addCompteur(int idCompteur) {
        if (idCompteur < 0) {
            throw new IllegalArgumentException("models.Quartier.addCompteur : L'id du compteur doit être positif");
        }
    }

    public String toString() {
        String ret = this.nomQuartier + "#" + this.idQuartier + " : nbCompteurs = " + this.compteurIdList.size()
                + ", longueurPiste = " + this.lgPisteCyclable;
        return ret;
    }

}
