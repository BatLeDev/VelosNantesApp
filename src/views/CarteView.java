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
import javafx.geometry.Insets;

// Project imports
import controllers.CarteController;
import utilities.ReadWriteFile;
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
    private GridPane legendGrid;

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
        this.webEngine.loadContent(ReadWriteFile.readHTMLFile(CARTE_HTML_PATH));

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
        this.legendGrid = new GridPane();
        this.legendGrid.setHgap(10);
        this.legendGrid.setVgap(10);
        this.legendGrid.setPadding(new Insets(10, 10, 10, 10));

        Label legendMarkersTitle = new Label("Fréquentation moyenne par jour :");
        GridPane.setConstraints(legendMarkersTitle, 0, 1, 2, 1);
        this.legendGrid.getChildren().add(legendMarkersTitle);

        addLegendMarker("marker1.png", "< 250", 2);
        addLegendMarker("marker2.png", "250 à 500", 3);
        addLegendMarker("marker3.png", "500 à 1000", 4);
        addLegendMarker("marker4.png", "1000 à 2000", 5);
        addLegendMarker("marker5.png", "> 2000", 6);
        
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
     * Add a marker to the legend
     * 
     * @param markerName The name of the marker
     * @param text The text to show
     * @param row The row of the grid
     */
    private void addLegendMarker(String markerName, String text, int row) {
        Image legendImage = new Image("file:src/ressources/images/markers/" + markerName);
        ImageView legendImageView = new ImageView(legendImage);
        GridPane.setConstraints(legendImageView, 0, row);
        Label legendMarker = new Label(text);
        GridPane.setConstraints(legendMarker, 1, row);
        this.legendGrid.getChildren().addAll(legendImageView, legendMarker);
    }
}
