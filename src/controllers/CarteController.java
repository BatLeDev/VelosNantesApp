package controllers;

import java.util.ArrayList;

import database.DatabaseAccess;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import models.CompteurFull;
import models.Marker;
import utilities.Rooter;
import views.CarteView;

public class CarteController {
    private Rooter rooter;
    private CarteView carteView;

    public CarteController(Rooter rooter, CarteView carteView) {
        this.rooter = rooter;
        this.carteView = carteView;
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

    public void webMachineLoaded(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
        if (newValue == Worker.State.SUCCEEDED) {
            System.out.println("Page loaded");
            
            // Generate the javascript code
            String js = this.generateJs();

            carteView.pageLoaded(js);
        }
    }
}
