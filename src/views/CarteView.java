package views;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.concurrent.Worker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import database.DatabaseAccess;
import models.CompteurFull;
import models.Marker;

/**
 * This class represents the view of the Carte page.
 * This page is a map, with a legend and sensors points.
 */
public class CarteView extends BorderPane {
    private static final String CARTE_HTML_PATH = "src/ressources/html/carte.html";

    // Elements of the view
    private WebEngine webEngine;

    /**
     * Constructor
     * This method is called by the router to create the view
     * 
     * Initialise the elements of the view
     */
    public CarteView() {
        // Create the map view
        WebView webView = new WebView();
        this.webEngine = webView.getEngine();

        this.webEngine.loadContent(readHTMLFile(CARTE_HTML_PATH));

        // Load the markers when the page is loaded
        this.webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                System.out.println("Page loaded");
                loadMarkers();
            }
        });

        // Positioning of the elements
        setCenter(webView);
    }

    private void loadMarkers() {

        ArrayList<CompteurFull> compteursList = DatabaseAccess.getCompteursWithStats();

        ArrayList<Marker> markersList = new ArrayList<>();

        for (CompteurFull compteur : compteursList) {
            boolean markerExists = false;

            for (Marker marker : markersList) {
                if (marker.getLongitude() == compteur.getLongitude()
                        && marker.getLatitude() == compteur.getLatitude() && !markerExists) {
                    marker.getCompteurs().add(compteur);
                    markerExists = true;
                }
            }

            if (!markerExists) {
                ArrayList<CompteurFull> compteurs = new ArrayList<>();
                compteurs.add(compteur);
                Marker marker = new Marker(compteur.getLibelle(), compteur.getLongitude(), compteur.getLatitude(),
                        compteur.getObservation(), compteur.getIdQuartier(), compteur.getNomQuartier(), compteurs);
                markersList.add(marker);
            }
        }

        String js = "var markersData = [";
        for (Marker marker : markersList) {
            js += marker.toJs() + ", ";
        }
        js += "];";

        js += "\n";
        js += "for (var i = 0; i < markersData.length; i++) {";
        js += "    var markerData = markersData[i];";
        js += "    createMarker(markerData);";
        js += "}";

        this.webEngine.executeScript(js);
    }

    /**
     * Convert a html file to a string
     * 
     * @param filePath Path of the html file
     * @return The html file converted to a string, executed by the web engine
     */
    public String readHTMLFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
