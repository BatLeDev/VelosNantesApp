package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;

import models.Compteur;
import models.CompteurFull;

import utilities.BCrypt;

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
*/

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

    public static String[] getQuartiersFromID(int id) {
        String[] quartiers = new String[2];

        try {
            // Connection to the database
            Statement connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Quartier WHERE id = " + id;
            ResultSet resultSet = connection.executeQuery(query);

            while (resultSet.next()) {
                String quartierName = resultSet.getString("nom");
                String quartierId = resultSet.getString("code");

                quartiers[0] = quartierName;
                quartiers[1] = quartierId;
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quartiers;
    }

    public static ArrayList<String> exporterRequete(ArrayList<String> requete, String table) {
        ArrayList<String> contenu = new ArrayList<String>();

        try {
            // Connection to the database
            Statement connection = DatabaseConnection.getConnection();

            String tmp = requete.get(0);
            for (int i = 1; i < requete.size(); i++) {
                tmp += ", " + requete.get(i);
            }

            String query = "SELECT "+tmp+" FROM "+table+";";        
            ResultSet resultSet = connection.executeQuery(query);

            tmp = requete.get(0);
            for (int i = 1; i < requete.size(); i++) {
                tmp += ";" + requete.get(i);
            }
            contenu.add(tmp);
            
            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                tmp = resultSet.getString(1);
                for (int i = 2; i <= columnCount; i++) {
                    tmp += ";"+resultSet.getObject(i);
                }
                contenu.add(tmp);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contenu;
    }

    public static ArrayList<String> getGraphique(String typeSomme, String typeTemps, String typeGraphique,
            String dateDebut, String dateFin, String selection, String selectionCheckBoxes) {
        ArrayList<String> graphique = new ArrayList<String>();
        try {
            Statement connection = DatabaseConnection.getConnection();

            String group;
            if (typeTemps.equals("Tout")) {
                group = "leJour";
            } else if (typeTemps.equals("Jour")) {
                group = "DAY(leJour)";
            } else if (typeTemps.equals("Mois")) {
                group = "MONTH(leJour)";
            } else {
                group = "YEAR(leJour)";
            }

            String type;
            if (typeSomme.equals("Somme")) {
                type = "SUM(total)";
            } else {
                type = "AVG(total)";
            }

            String query = "SELECT " + type + " FROM vue_ReleveJournalierResume WHERE leJour BETWEEN '" + dateDebut
                    + "' AND '" + dateFin + "' AND leCompteur IN (" + selectionCheckBoxes + ") GROUP BY " + group + ";";
            ResultSet resultSet = connection.executeQuery(query);

            while (resultSet.next()) {
                String total = resultSet.getString(type);
                graphique.add(total);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return graphique;
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

                Compteur compteur = new Compteur(numero, libelle, direction, observation, latitude, longitude);
                compteurs.add(compteur);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compteurs;
    }

    /**
     * Check if the username exists and if the password is correct
     * 
     * @param username
     * @param password
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
                    System.out.println("Mot de passe correct !");
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
     * Create user account
     * 
     * @param identifiant The username of the account
     * @param motDePasse The password of the account
     * @param typeDeCompte The type of the account ("Administrateur", "Utilisateur", "Elu")
     */
    public static void createAcount(String identifiant, String motDePasse, String typeDeCompte) {
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

    public static String requeteSQL (String requete) throws SQLException {
        String contenu = "";

        Statement connexion = DatabaseConnection.getConnection();

        ResultSet resultSet = connexion.executeQuery(requete);
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            contenu+=(resultSet.getMetaData().getColumnName(i))+"\t";
        }
        contenu+="\n";
        while (resultSet.next()) {
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                contenu+=(resultSet.getString(i)+"\t");
            }
            contenu+="\n";
        }
        resultSet.close();

        return contenu;
    }
}
