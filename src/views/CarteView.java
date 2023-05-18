package views;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.concurrent.Worker;

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
                loadMarkers();
            }
        });

        // Positioning of the elements
        setCenter(webView);
    }

    private void loadMarkers() {
        String js = "var marker = L.marker([47.2184, -1.5536]).addTo(map);\n" +
                "marker.bindPopup(\"<b>Hello world!</b><br>I am a popup.\").openPopup();";

        this.webEngine.executeScript(js);
    }

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
