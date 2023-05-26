package models;

import java.util.ArrayList;

public class Marker {
    private String libelle;
    private double longitude;
    private double latitude;
    private String observation;
    private int idQuartier;
    private String nomQuartier;
    private ArrayList<CompteurFull> compteurs;

    public Marker(String libelle, double longitude, double latitude, String observation, int idQuartier,
            String nomQuartier, ArrayList<CompteurFull> compteurs) {
        this.libelle = libelle;
        this.longitude = longitude;
        this.latitude = latitude;
        this.observation = observation;
        this.idQuartier = idQuartier;
        this.nomQuartier = nomQuartier;
        this.compteurs = compteurs;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getObservation() {
        return observation;
    }

    public int getIdQuartier() {
        return idQuartier;
    }

    public String getNomQuartier() {
        return nomQuartier;
    }

    public ArrayList<CompteurFull> getCompteurs() {
        return compteurs;
    }

    public String toString() {
        String ret = "Marker [libelle=" + libelle + ", longitude=" + longitude + ", latitude=" + latitude
                + ", observation="
                + observation + ", idQuartier=" + idQuartier + ", nomQuartier=" + nomQuartier + ", compteurs= [";

        for (CompteurFull compteur : compteurs) {
            ret += compteur.toString() + ", ";
        }
        ret += "]]";
        return ret;
    }

    public String toJs() {
        String compteurDetails = "";

        for (CompteurFull compteur : compteurs) {
            compteurDetails += compteur.toJs() + ",";
        }

        compteurDetails = compteurDetails.substring(0, compteurDetails.length() - 1); // Supprimer la virgule finale

        String ret =  "{ " +
                "long: " + longitude +
                ", lat: " + latitude +
                ", libelle: \"" + libelle + "\"" +
                ", quartier: \"" + nomQuartier + "\"" +
                ", idQuartier: " + idQuartier +
                ", freqmoy: " + calcFreqMoy() +
                ", releve: \"" + (observation != null ? "Oui" : "Non") + "\"" +
                ", color: \"" + getColor() + "\"" +
                ", details: [" + compteurDetails + "]" +
                " }";

        return ret;
    }
    
    private double calcFreqMoy() {
        double freqMoy = 0;
        for (CompteurFull compteur : compteurs) {
            freqMoy += compteur.getMoyennePassageParJour();
        }
        double ret = freqMoy / compteurs.size();
        ret = Math.round(ret * 100) / 100d;
        return ret;
    }
    
    private String getColor() {
        double freqMoy = calcFreqMoy();
        if (freqMoy < 1) {
            return "#999999";
        } else if (freqMoy < 250) {
            return "#6AAE27";
        } else if (freqMoy < 500) {
            return "#C3C201";
        } else if (freqMoy < 1000) {
            return "#FFE600";
        } else if (freqMoy < 1500) {
            return "#FF6B00";
        } else {
            return "#FF0000";
        }
    }
}
