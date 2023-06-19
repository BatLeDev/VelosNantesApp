package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;

import models.*;

public class DatabaseAccess {

    public static void exemple() {
        try {
            // Connection to the database
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM Quartier";
            ResultSet resultSet = statement.executeQuery(query);

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

    public static ArrayList<CompteurFull> getCompteursWithStats() {
        ArrayList<CompteurFull> compteurs = new ArrayList<CompteurFull>();

        try {
            // Connection to the database
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM vue_statCompteur";
            ResultSet resultSet = statement.executeQuery(query);

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
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM Quartier WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(query);

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
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            String tmp = requete.get(0);
            for (int i = 1; i < requete.size(); i++) {
                tmp += ", " + requete.get(i);
            }

            String query = "SELECT "+tmp+" FROM "+table+";";        
            ResultSet resultSet = statement.executeQuery(query);

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
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

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
            ResultSet resultSet = statement.executeQuery(query);

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
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM Compteur";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int numero = resultSet.getInt("numero");
                String libelle = resultSet.getString("libelle");
                String direction = resultSet.getString("direction");
                String observation = resultSet.getString("observations");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                int idQuartier = resultSet.getInt("leQuartier");

                Compteur compteur = new Compteur(numero, libelle, direction, observation, latitude, longitude, idQuartier);
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
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

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
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

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

    public static ArrayList<ReleveJournalier> getReleveJournaliers () {
        ArrayList<ReleveJournalier> releveJournaliers = new ArrayList<ReleveJournalier>();

        try {
            // Connection to the database
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

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
                int[] heures = {heure00, heure01, heure02, heure03, heure04, heure05, heure06,
                     heure07, heure08, heure09, heure10, heure11, heure12, heure13, heure14, heure15,
                      heure16, heure17, heure18, heure19, heure20, heure21, heure22, heure23};

                ReleveJournalier releveJournalier = new ReleveJournalier(numero, date, heures, observation);
                releveJournaliers.add(releveJournalier);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des relevés journaliers" + e.getMessage());
        }

        return releveJournaliers;
    }
}
