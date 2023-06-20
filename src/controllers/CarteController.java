package controllers;

// Java imports
import java.util.ArrayList;

// JavaFX imports
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;

// Project imports
import database.DatabaseAccess;
import models.CompteurFull;
import models.Marker;
import views.CarteView;

/**
 * This class is the controller of the Carte page.
 * <p> This class acts as a bridge between the view and the data
 */
public class CarteController {
    private CarteView carteView;

    /**
     * Initialize the controller
     * 
     * @param carteView The view of the Carte page
     */
    public CarteController(CarteView carteView) {
        this.carteView = carteView;
    }

    /**
     * Generate markers from the database
     * 
     * @return the list of markers generated
     */
    private ArrayList<Marker> generateMarkers() {
        ArrayList<CompteurFull> compteursList = DatabaseAccess.getCompteursWithStats();
        ArrayList<Marker> markersList = new ArrayList<Marker>();

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

        return markersList;
    }

    /**
     * Generate the javascript code to add the markers to the map
     * @return a list of markers in javascript
     */
    public String generateJs() {
        ArrayList<Marker> markersList = generateMarkers();

        // We convert the list of markers to a javascript string and add the markers to the map
        String js = "var markersData = [";
        for (Marker marker : markersList) {
            js += marker.toJs() + ", ";
        }
        js += "];";
        js += "addMarkers();";

        return js;
    }

    /**
     * This method is called when the page is loaded
     * 
     * @param observable not used
     * @param oldValue not used
     * @param newValue check if the page is loaded
     */
    public void webMachineLoaded(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
        if (newValue == Worker.State.SUCCEEDED) {
            System.out.println("Page loaded");
            String js = this.generateJs(); // Generate the javascript code
            carteView.pageLoaded(js); // Update the view
        }
    }
}
