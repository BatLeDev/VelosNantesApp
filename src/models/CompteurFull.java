package models;

public class CompteurFull extends Compteur{

    // ----------------------------- static methods -----------------------------
    
    /**
     * Delete a compteur by its id
     * 
     * @return the compteur object corresponding to the id
     */
    public static Compteur deleteCompteur(int id) {
        Compteur c = Compteur.compteurList.remove(id);
        if (c != null) {
            Quartier quartier = Quartier.getQuartier(c.getNumero());
            if (quartier != null) {
                quartier.removeCompteur(id);
            }
        }
        ReleveJournalier.removeAllRelevesOfACompteur(id);
        return c;
    }

    public static String[] getHeaders() {
        return new String[] { "numero", "libelle", "direction", "observation", "latitude", "longitude", "idQuartier", "nomQuartier", "nombreJourReleve", "nombreTotalPassage", "moyennePassageParJour", "frequenceErreurs", "nbErreurs", "heureSouventFrequetee" };
    }


    // ----------------------------- attributes -----------------------------
    
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


    // ----------------- Getters ----------------- //

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

    // ----------------- Setters ----------------- //

    /**
     * Setter for the quartier of the compteur
     * Update the compteur list of the old and new quartier
     * If the idQuartier is -1, the compteur is removed from the quartier and the Quartier is undefined
     * @param idQuartier an integer representing the id of the quartier of the compteur (positive)
     */
    public void setQuartier(int idQuartier) {
        if (idQuartier < -1) {
            throw new IllegalArgumentException(
                    "models.Compteur.setQuartier : L'id du quartier ne peut pas être négatif");
        } 

        if (idQuartier != -1) {
            //verification que le quartier existe
            Quartier q = Quartier.getQuartier(idQuartier);
            if (q == null) {
                throw new IllegalArgumentException(
                        "models.Compteur.setQuartier : L'id du quartier ne correspond à aucun quartier");
            }
            if (this.idQuartier != -1) {
                Quartier oldQuartier = Quartier.getQuartier(this.idQuartier);
                oldQuartier.removeCompteur(this.getNumero());
            }
            q.addCompteur(this.getNumero());
            this.nomQuartier = q.getNomQuartier();
        }

        this.idQuartier = idQuartier;
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
