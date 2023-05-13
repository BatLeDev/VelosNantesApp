package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Rooter {

    public Rooter (Button button) {
        String strPath = "";
        System.out.println(button.getText());
        if (button.getText().equals("Graphique")){
            strPath = "../views/GraphView.fxml";
        } else if (button.getText().equals("Carte")){
            strPath = "../views/CarteView.fxml";
        } else if (button.getText().equals("Export")){
            strPath = "../views/ExportView.fxml";
        }
        // Afficher le graphe
        FXMLLoader loader = new FXMLLoader(getClass().getResource(strPath));
        try {
            BorderPane graphRoot = loader.load();
            // ... Effectuer les actions supplémentaires si nécessaire

            // Afficher la scène du graphe
            Scene scene = new Scene(graphRoot);
            Stage primaryStage = (Stage) button.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle(button.getText());
            primaryStage.sizeToScene();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}