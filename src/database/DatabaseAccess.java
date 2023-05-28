package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;
import models.Compteur;

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

                Compteur compteur = new Compteur(numero, libelle, direction, observation, latitude, longitude);
                compteurs.add(compteur);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compteurs;
    }


    public static ArrayList<String> getGraphique ( String typeSomme, String typeTemps, String typeGraphique, String dateDebut, String dateFin, String selection, String selectionCheckBoxes){
        ArrayList<String> graphique = new ArrayList<String>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            System.out.println(dateDebut + " " + dateFin + " " + selectionCheckBoxes);
            String query = "SELECT total FROM vue_ReleveJournalierResume WHERE leJour BETWEEN '"+dateDebut+"' AND '"+dateFin+"' AND leCompteur IN ("+selectionCheckBoxes+");";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String total = resultSet.getString("total");
                graphique.add(total);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return graphique;
    }
}
