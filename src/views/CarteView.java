package views;

import javafx.scene.control.Label;
// JavaFX imports
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.concurrent.Worker;

// Java imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Project imports
import database.DatabaseAccess;
import models.CompteurFull;
import models.Marker;

/**
 * This class represents the view of the Carte page.
 * This page is a map, with a legend and markers points which represents sensors.
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
        // Show "Chargement en cour ..."
        Label loadingLabel = new Label("Chargement en cours ... (Cela peut dépendre de la connexion internet)");
        this.setCenter(loadingLabel);

        // Create the map view
        WebView webView = new WebView();
        this.webEngine = webView.getEngine();

        this.webEngine.loadContent(readHTMLFile(CARTE_HTML_PATH));

        // Load the markers when the page is loaded
        this.webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                System.out.println("Page loaded");
                loadMarkers(); // Load the markers on the map
                setCenter(webView); // Add the map to the view
            }
        });
    }

    /**
     * Load the markers on the map
     * 
     * Get the list of sensors from the database
     * Create a list of markers from the list of sensors
     * Convert the list of markers to a javascript string
     * Execute the javascript string in the web engine
     */
    private void loadMarkers() {
        ArrayList<CompteurFull> compteursList = DatabaseAccess.getCompteursWithStats();
        ArrayList<Marker> markersList = new ArrayList<>();

        // For each sensor, we create a marker
        for (CompteurFull compteur : compteursList) {
            boolean markerExists = false;

            // We check if the marker already exists, if it does, we add the sensor to the marker
            for (Marker marker : markersList) {
                if (marker.getLongitude() == compteur.getLongitude()
                        && marker.getLatitude() == compteur.getLatitude() && !markerExists) {
                    marker.getCompteurs().add(compteur);
                    markerExists = true;
                }
            }

            // If the marker doesn't exist, we create it
            if (!markerExists) {
                ArrayList<CompteurFull> compteurs = new ArrayList<>();
                compteurs.add(compteur);
                Marker marker = new Marker(compteur.getLibelle(), compteur.getLongitude(), compteur.getLatitude(),
                        compteur.getObservation(), compteur.getIdQuartier(), compteur.getNomQuartier(), compteurs);
                markersList.add(marker);
            }
        }

        // We convert the list of markers to a javascript string and add the markers to the map
        String js = "var markersData = [";
        for (Marker marker : markersList) {
            js += marker.toJs() + ", ";
        }
        js += "];";
        js += "addMarkers();";

        this.webEngine.executeScript(js);
        System.out.println("Markers loaded");
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
