package views;

// JavaFX imports
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;

// Java imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Project imports
import controllers.CarteController;
import utilities.Rooter;

/**
 * This class represents the view of the Carte page.
 * <p> This page is a map, with a legend and markers points which represents sensors.
 * <p> The markers are loaded from the database, and the map is a html file with javascript.
 */
public class CarteView extends BorderPane {
    /**
     * Path of the html file of the map, use an online map with an API
     */
    private static final String CARTE_HTML_PATH = "src/ressources/html/carte.html";

    // Controller of the view
    private CarteController carteController;

    // Elements of the view
    private WebEngine webEngine;
    private StackPane content;

    /**
     * Initialize elements of the view and create the controller
     * 
     * @param rooter The rooter of the application
     */
    public CarteView(Rooter rooter) {
        this.carteController = new CarteController(rooter, this);

        // Show a loading pane while map and markers are loading.
        Label loadingLabel = new Label("Chargement en cours ... (Cela peut dépendre de la connexion internet)");
        this.setCenter(loadingLabel);

        AnchorPane legendPane = this.createLegendPane();

        // Create the map view
        WebView webView = new WebView();
        this.webEngine = webView.getEngine();
        this.webEngine.loadContent(readHTMLFile(CARTE_HTML_PATH));

        // Superposition of the map and the legend
        this.content = new StackPane();
        this.content.getChildren().addAll(webView, legendPane);

        // Listener to know when the map is loaded
        this.webEngine.getLoadWorker().stateProperty().addListener(this.carteController::webMachineLoaded);
    }

    /**
     * Add the markers to the map and show the map
     * 
     * @param js The javascript code to add the markers
     */
    public void pageLoaded(String js) {
        this.webEngine.executeScript(js);
        this.setCenter(this.content);
        System.out.println("Markers loaded");
    }

    /**
     * Initialize the legend pane
     * 
     * @return The legend pane
     */
    private AnchorPane createLegendPane() {
        // Rectangle title
        Label legendTitleLabel = new Label("Légende");
        legendTitleLabel.getStyleClass().add("legend-title-label");
        StackPane legendTitle = new StackPane();
        legendTitle.getChildren().add(legendTitleLabel);
        legendTitle.getStyleClass().add("legend-title");

        // Rectangle with the legend
        GridPane legendGrid = new GridPane();
        legendGrid.setHgap(10);
        legendGrid.setVgap(10);
        legendGrid.setPadding(new Insets(10, 10, 10, 10));

        Label legendMarkersTitle = new Label("Fréquentation moyenne par jour :");
        GridPane.setConstraints(legendMarkersTitle, 0, 1, 2, 1);

        Image legendMarkersImage1 = new Image("file:src/ressources/images/markers/marker1.png");
        ImageView legendMarkersImageView1 = new ImageView(legendMarkersImage1);
        GridPane.setConstraints(legendMarkersImageView1, 0, 2);
        Label legendMarkers1 = new Label("< 250");
        GridPane.setConstraints(legendMarkers1, 1, 2);

        Image legendMarkersImage2 = new Image("file:src/ressources/images/markers/marker2.png");
        ImageView legendMarkersImageView2 = new ImageView(legendMarkersImage2);
        GridPane.setConstraints(legendMarkersImageView2, 0, 3);
        Label legendMarkers2 = new Label("250 à 500");
        GridPane.setConstraints(legendMarkers2, 1, 3);

        Image legendMarkersImage3 = new Image("file:src/ressources/images/markers/marker3.png");
        ImageView legendMarkersImageView3 = new ImageView(legendMarkersImage3);
        GridPane.setConstraints(legendMarkersImageView3, 0, 4);
        Label legendMarkers3 = new Label("500 à 1000");
        GridPane.setConstraints(legendMarkers3, 1, 4);

        Image legendMarkersImage4 = new Image("file:src/ressources/images/markers/marker4.png");
        ImageView legendMarkersImageView4 = new ImageView(legendMarkersImage4);
        GridPane.setConstraints(legendMarkersImageView4, 0, 5);
        Label legendMarkers4 = new Label("1000 à 2000");
        GridPane.setConstraints(legendMarkers4, 1, 5);

        Image legendMarkersImage5 = new Image("file:src/ressources/images/markers/marker5.png");
        ImageView legendMarkersImageView5 = new ImageView(legendMarkersImage5);
        GridPane.setConstraints(legendMarkersImageView5, 0, 6);
        Label legendMarkers5 = new Label("> 2000");
        GridPane.setConstraints(legendMarkers5, 1, 6);

        legendGrid.getChildren().addAll(legendMarkersTitle, legendMarkersImageView1, legendMarkers1,
                legendMarkersImageView2, legendMarkers2, legendMarkersImageView3, legendMarkers3,
                legendMarkersImageView4, legendMarkers4, legendMarkersImageView5, legendMarkers5);
        legendGrid.getStyleClass().add("legend-grid");

        // Position of the legend
        AnchorPane legendPane = new AnchorPane();
        legendPane.setPadding(new Insets(10));
        legendPane.getChildren().addAll(legendGrid, legendTitle);
        legendPane.setMouseTransparent(true);
        AnchorPane.setBottomAnchor(legendGrid, 10.0);
        AnchorPane.setLeftAnchor(legendGrid, 10.0);
        AnchorPane.setBottomAnchor(legendTitle, 290.0);
        AnchorPane.setLeftAnchor(legendTitle, 10.0);

        return legendPane;
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
