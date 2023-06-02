package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;
import models.CompteurFull;

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

    
}