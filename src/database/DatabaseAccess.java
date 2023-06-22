package database;

// SQL imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Java imports
import java.util.ArrayList;

// Project imports
import models.*;
import utilities.BCrypt;

/**
 * This class is used to access the database
 */
public class DatabaseAccess {

    /*
    public static void exemple() {
        try {
            // Connection to the database
            Statement connection = DatabaseConnection.getConnection();
        
            String query = "SELECT * FROM Quartier";
            ResultSet resultSet = connection.executeQuery(query);
        
            printResult(resultSet);
        
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void printResult(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
    
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
    
                System.out.println(columnName + " : " + columnValue);
            }
            System.out.println();
        }
    }
*/

    /**
     * Get the list of the counters with their statistics
     * 
     * @return The list of the counters with their statistics
     */
    public static ArrayList<CompteurFull> getCompteursWithStats() {
        ArrayList<CompteurFull> compteurs = new ArrayList<CompteurFull>();

        try {
            // Connection to the database
            Statement connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM vue_statCompteur";
            ResultSet resultSet = connection.executeQuery(query);

            while (resultSet.next()) {
                int numero = resultSet.getInt("numero");
                String libelle = resultSet.getString("libelle");
                String direction = resultSet.getString("direction");
                String observation = resultSet.getString("observations");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                int idQuartier = resultSet.getInt("idQuartier");
                String nomQuartier = resultSet.getString("nomQuartier");
                int nombreJourReleve = resultSet.getInt("nombreJourReleve");
                int nombreTotalPassage = resultSet.getInt("nombreTotalPassage");
                double moyennePassageParJour = resultSet.getDouble("moyennePassageParJour");
                double frequenceErreurs = resultSet.getDouble("frequenceErreurs");
                int nbErreurs = resultSet.getInt("nbErreurs");
                String heureSouventFrequetee = resultSet.getString("heureSouventFrequetee");

                CompteurFull compteur = new CompteurFull(numero, libelle, direction, observation, latitude, longitude,
                        idQuartier, nomQuartier, nombreJourReleve, nombreTotalPassage, moyennePassageParJour,
                        frequenceErreurs, nbErreurs, heureSouventFrequetee);

                compteurs.add(compteur);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compteurs;
    }

    public static ArrayList<Compteur> getCompteurs() {
        ArrayList<Compteur> compteurs = new ArrayList<Compteur>();

        try {
            // Connection to the database
            Statement connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Compteur";
            ResultSet resultSet = connection.executeQuery(query);

            while (resultSet.next()) {
                int numero = resultSet.getInt("numero");
                String libelle = resultSet.getString("libelle");
                String direction = resultSet.getString("direction");
                String observation = resultSet.getString("observations");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                int idQuartier = resultSet.getInt("leQuartier");

                Compteur compteur = new Compteur(numero, libelle, direction, observation, latitude, longitude,
                        idQuartier);
                compteurs.add(compteur);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compteurs;
    }

    public static ArrayList<Quartier> getQuartiers() {
        ArrayList<Quartier> quartiers = new ArrayList<Quartier>();

        try {
            // Connection to the database
            Statement statement = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Quartier";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("code");
                String nom = resultSet.getString("nom");
                double lg = resultSet.getDouble("longueurPiste");

                Quartier quartier = new Quartier(id, nom, lg);
                quartiers.add(quartier);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des quartiers" + e.getMessage());
        }

        return quartiers;
    }

    public static ArrayList<Jour> getJour() {
        ArrayList<Jour> jours = new ArrayList<Jour>();

        try {
            // Connection to the database
            Statement statement = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Jour";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String date = resultSet.getString("jourDate");
                int j = resultSet.getInt("jourDeSemaine");
                double temperatureMoyenne = resultSet.getDouble("temperature");

                Jour jour = new Jour(date, j, temperatureMoyenne);
                jours.add(jour);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des jours" + e.getMessage());
        }

        return jours;
    }

    public static ArrayList<ReleveJournalier> getReleveJournaliers() {
        ArrayList<ReleveJournalier> releveJournaliers = new ArrayList<ReleveJournalier>();

        try {
            // Connection to the database
            Statement statement = DatabaseConnection.getConnection();

            String query = "SELECT * FROM ReleveJournalier";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String date = resultSet.getString("leJour");
                int numero = resultSet.getInt("leCompteur");
                int heure00 = resultSet.getInt("heure0");
                int heure01 = resultSet.getInt("heure1");
                int heure02 = resultSet.getInt("heure2");
                int heure03 = resultSet.getInt("heure3");
                int heure04 = resultSet.getInt("heure4");
                int heure05 = resultSet.getInt("heure5");
                int heure06 = resultSet.getInt("heure6");
                int heure07 = resultSet.getInt("heure7");
                int heure08 = resultSet.getInt("heure8");
                int heure09 = resultSet.getInt("heure9");
                int heure10 = resultSet.getInt("heure10");
                int heure11 = resultSet.getInt("heure11");
                int heure12 = resultSet.getInt("heure12");
                int heure13 = resultSet.getInt("heure13");
                int heure14 = resultSet.getInt("heure14");
                int heure15 = resultSet.getInt("heure15");
                int heure16 = resultSet.getInt("heure16");
                int heure17 = resultSet.getInt("heure17");
                int heure18 = resultSet.getInt("heure18");
                int heure19 = resultSet.getInt("heure19");
                int heure20 = resultSet.getInt("heure20");
                int heure21 = resultSet.getInt("heure21");
                int heure22 = resultSet.getInt("heure22");
                int heure23 = resultSet.getInt("heure23");
                String observation = resultSet.getString("probabiliteAnomalie");
                int[] heures = { heure00, heure01, heure02, heure03, heure04, heure05, heure06,
                        heure07, heure08, heure09, heure10, heure11, heure12, heure13, heure14, heure15,
                        heure16, heure17, heure18, heure19, heure20, heure21, heure22, heure23 };

                ReleveJournalier releveJournalier = new ReleveJournalier(numero, date, heures, observation);
                releveJournaliers.add(releveJournalier);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des relevés journaliers" + e.getMessage());
        }

        return releveJournaliers;
    }

    /**
     * Check if the username exists and if the password is correct
     * 
     * @param username The username
     * @param password The password
     * @return -1 if username doesn't exist, 0 if password is incorrect, 1 if all is correct
     */
    public static int checkLogin(String username, String password) {
        int res = -1;

        try {
            // Connection to the database
            Statement connection = DatabaseConnection.getConnection();

            // Check if username exists
            String query = "SELECT motDePasse FROM Compte WHERE identifiant = '" + username + "';";
            ResultSet resultSet = connection.executeQuery(query);

            // Check if username exists
            if (resultSet.next()) {
                res = 0;

                // Check if password is correct
                if (BCrypt.checkpw(password, resultSet.getString("motDePasse"))) {
                    res = 1;
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Get the type of the account
     * 
     * @param username The username of the account
     * @return The type of the account
     */
    public static String getTypeDeCompte(String username) {
        String typeDeCompte = null;

        try {
            // Connection to the database
            Statement connection = DatabaseConnection.getConnection();

            String query = "SELECT typeDeCompte FROM Compte WHERE identifiant = '" + username + "';";
            ResultSet resultSet = connection.executeQuery(query);

            if (resultSet.next()) {
                typeDeCompte = resultSet.getString("typeDeCompte");
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeDeCompte;
    }

    /**
     * Create user account (insert into the database)
     * 
     * @param identifiant The username of the account
     * @param motDePasse The password of the account
     * @param typeDeCompte The type of the account ("Administrateur", "Utilisateur", "Elu")
     */
    public static void createAccount(String identifiant, String motDePasse, String typeDeCompte) {
        try {
            // Connection to the database
            Statement connection = DatabaseConnection.getConnection();
            String passwordHash = BCrypt.hashpw(motDePasse, BCrypt.gensalt());

            String query = "INSERT INTO Compte (identifiant, motDePasse, typeDeCompte) VALUES ('" + identifiant + "', '"
                    + passwordHash + "', '" + typeDeCompte + "');";
            connection.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change the typeDeCompte of an account
     * 
     * @param identifiant The username of the account
     * @param typeDeCompte The new type of the account
     */
    public static void updateAccount(String identifiant, String typeDeCompte) {
        try {
            // Connection to the database
            Statement connection = DatabaseConnection.getConnection();

            String query = "UPDATE Compte SET typeDeCompte = \"" + typeDeCompte + "\" WHERE identifiant = \"" + identifiant + "\";";
            connection.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all accounts
     * 
     * @return a list of 2 strings arrays (identifiant, typeDeCompte)
     */
    public static ArrayList<String[]> getAccounts() {
        ArrayList<String[]> accounts = new ArrayList<String[]>();

        try {
            // Connection to the database
            Statement connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Compte";
            ResultSet resultSet = connection.executeQuery(query);

            while (resultSet.next()) {
                String identifiant = resultSet.getString("identifiant");
                String typeDeCompte = resultSet.getString("typeDeCompte");

                String[] account = { identifiant, typeDeCompte };
                accounts.add(account);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    /**
     * Add a new Compter to the database
     * 
     * @param Compteur The number of the Compteur
     */
    public static void addCompteur(Compteur compteur) throws SQLException{
        // Connection to the database
        Statement connection = DatabaseConnection.getConnection();
        String quartier =compteur.getLeQuartier()+"";
        String observation = compteur.getObservation();
        if (observation == null) {
            observation = "NULL";
        }
        String query= null;
        if (quartier.equals("0")) {
            query = "INSERT INTO Compteur (numero,libelle,direction,observations,longitude,latitude) VALUES ('" + compteur.getNumero() + "', '" + compteur.getLibelle() + "', '" + compteur.getDirection() + "', " + observation + ", '" + compteur.getLatitude() + "', '" + compteur.getLongitude() +"');";
        } else {
            query = "INSERT INTO Compteur VALUES ('" + compteur.getNumero() + "', '" + compteur.getLibelle() + "', '" + compteur.getDirection() + "', '" + observation + "', " + compteur.getLatitude() + ", '" + compteur.getLongitude() + "', '" + quartier + "');";
        }
        
       connection.executeUpdate(query);
        
    }

    /**
     * Add a new ReleveJournalier to the database
     * 
     * @param ReleveJournalier The ReleveJournalier to add
     */
    public static void addReleveJournalier(ReleveJournalier releve) throws SQLException {
        // Connection to the database
        Statement connection = DatabaseConnection.getConnection();

        String anomalie = releve.getPresenceAnomalie();
        if (anomalie == null) {
            anomalie = "NULL";
        }
        String query = "INSERT INTO ReleveJournalier VALUES ("+ releve.getCompteur() + ", '" + releve.getJour() + "', " + releve.getHeure00() + ", " + releve.getHeure01() + ", " + releve.getHeure02() + ", " + releve.getHeure03() + ", " + releve.getHeure04() + ", " + releve.getHeure05() + ", " + releve.getHeure06() + ", " + releve.getHeure07() + ", " + releve.getHeure08() + ", " + releve.getHeure09() + ", " + releve.getHeure10() + ", " + releve.getHeure11() + ", " + releve.getHeure12() + ", " + releve.getHeure13() + ", " + releve.getHeure14() + ", " + releve.getHeure15() + ", " + releve.getHeure16() + ", " + releve.getHeure17() + ", " + releve.getHeure18() + ", " + releve.getHeure19() + ", " + releve.getHeure20() + ", " + releve.getHeure21() + ", " + releve.getHeure22() + ", " + releve.getHeure23() + ", " + anomalie + ");";
        connection.executeUpdate(query);
    }

    /**
     * Add a new Jour to the database
     * 
     * @param Jour The Jour to add
     */
     public static void addJour(Jour jour) throws SQLException {
        // Connection to the database
        Statement connection = DatabaseConnection.getConnection();

        String query = "INSERT INTO Jour VALUES ('" + jour.getDate() + "', '" + jour.getJour() +"','"+jour.getTemperatureMoyenne()+"')";
        connection.executeUpdate(query);

     }

    /**
     * Add a new Quartier to the database
     *
     * @param Quartier The Quartier to add
     */
    public static void addQuartier (Quartier quartier) throws SQLException{
        // Connection to the database
        Statement connection = DatabaseConnection.getConnection();

        String query = "INSERT INTO Quartier VALUES ('" + quartier.getId() + "', '" + quartier.getNom() + "', '" + quartier.getLgPisteCyclable() +"');";
        connection.executeUpdate(query);
    }

    /**
     * Delete a Compteur from the database
     * 
     * @param numero The number of the Compteur to delete
     */
    public static void deleteCompteur(int numero) throws SQLException {
        // Connection to the database
        Statement connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM ReleveJournalier WHERE leCompteur = '" + numero + "';";
        connection.executeUpdate(query);
        query = "DELETE FROM Compteur WHERE numero = '" + numero + "';";
        connection.executeUpdate(query);
    }

    /**
     * Delete a ReleveJournalier from the database
     * 
     * @param numero The number of the Compteur of the ReleveJournalier to delete
     * @param jour The day of the ReleveJournalier to delete
     */
    public static void deleteReleveJournalier(int numero, String jour) throws SQLException {
        // Connection to the database
        Statement connection = DatabaseConnection.getConnection();

        String query = "DELETE FROM ReleveJournalier WHERE leCompteur = '" + numero + "' AND leJour = '" + jour + "';";
        connection.executeUpdate(query);
    }

    /**
     * Delete a Jour from the database
     * 
     * @param jour The day of the Jour to delete
     */
    public static void deleteJour(String jour) throws SQLException {
        // Connection to the database
        Statement connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM ReleveJournalier WHERE leJour = '" + jour + "'";
        connection.executeUpdate(query);
        query = "DELETE FROM Jour WHERE jourDate = '" + jour + "';";
        connection.executeUpdate(query);

    }

    /**
     * Delete a Quartier from the database
     *
     * @param id The id of the Quartier to delete
     */
    public static void deleteQuartier(int id) throws SQLException {
        // Connection to the database
        Statement connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM Compteur WHERE leQuartier = '" + id + "';";
        connection.executeUpdate(query);
        query = "DELETE FROM Quartier WHERE code = '" + id + "';";
        connection.executeUpdate(query);

    }

}
