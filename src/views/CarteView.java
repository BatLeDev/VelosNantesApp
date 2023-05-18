package views;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

/**
 * This class represents the view of the Carte page.
 * This page is a map, with a legend and sensors points.
 */
public class CarteView extends BorderPane {

    // Elements of the view


    /**
     * Constructor
     * This method is called by the router to create the view
     * 
     * Initialise the elements of the view
     */
    public CarteView() {

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Carte sans API Google</title>\n" +
                "    <style>\n" +
                "        body, html {\n" +
                "            height: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        #map {\n" +
                "            height: 100%;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <!-- Inclure les fichiers CSS et JavaScript de Leaflet -->\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.css\" />\n"
                +
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"map\"></div>\n" +
                "\n" +
                "    <script>\n" +
                "        // Initialiser la carte\n" +
                "        var map = L.map('map').setView([47.2184, -1.5536], 13);\n" +
                "\n" +
                "        // Ajouter une couche de tuiles OpenStreetMap\n" +
                "        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {\n" +
                "            attribution: '© OpenStreetMap contributors'\n" +
                "        }).addTo(map);\n" +
                "\n" +
                "        // Ajuster la taille de la carte lorsque la fenêtre est redimensionnée\n" +
                "        map.on('resize', function () {\n" +
                "            map.invalidateSize();\n" +
                "        });\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";

        // Create the map view
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(htmlContent);

        // Positioning of the elements
        setCenter(webView);
    }
}
