package controllers;

// Java imports
import java.util.HashMap;

// JavaFX imports
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// Views imports
import views.*;

/**
 * This class represents the rooter of the application.
 * Generate all views, allow to change the view displayed.
 */
public class Rooter {

    /**
     * Main BorderPane
     * His content is changed to display the correct view
     * At the top of the BorderPane, the navbar is displayed (or not)
     * At the center of the BorderPane, the view is displayed
     */
    private BorderPane root;

    /**
     * Main Scene
     * This scene is displayed in the main window
     */
    private Scene mainScene;

    /**
     * HashMap of all views
     * The key is the title of the view
     * The value is the view
     * It's used to get a view by his title
     */
    private HashMap<String, BorderPane> views;

    /**
     * Constructor
     * This method is called by the MainApp class
     * 
     * Initialise the rooter:
     * - Create all views
     * - Create the navbar
     * - Create the main BorderPane
     * - Create the main Scene
     * 
     * @param primaryStage
     */
    public Rooter(Stage primaryStage) {
        // Initialisation of the HashMap of views
        this.views = new HashMap<String, BorderPane>();

        // Creation of each view
        ExporterView exporterView = new ExporterView();
        views.put("Exporter", exporterView);

        CarteView carteView = new CarteView();
        views.put("Carte", carteView);

        GraphiqueView graphiqueView = new GraphiqueView();
        views.put("Graphique", graphiqueView);

        // Creation of the navbar
        NavbarView navbarView = new NavbarView();
        views.put("Navbar", navbarView);

        // Initialisation of the main BorderPane
        this.root = new BorderPane();

        // Initialisation of the main Scene
        this.mainScene = new Scene(root, 800, 600);
        this.mainScene.getStylesheets().add("file:src/ressources/css/carte.css");
        // Configuration of the main window
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * Change the page displayed
     * 
     * @param showNavbar If true, the navbar is displayed
     * @param pageTitle The title of the page to display
     */
    public void changePage(boolean showNavbar, String pageTitle) {
        // Get the view to display
        BorderPane page = views.get(pageTitle);

        // Display the view in the center of the main BorderPane
        root.setCenter(page);

        // Display or hide the navbar
        if (showNavbar) {
            root.setTop(views.get("Navbar"));
        } else {
            root.setTop(null);
        }
    }

    /**
     * Get a view by his title
     * 
     * @param viewName The title of the view to get
     * @return The BorderPane view
     */
    public BorderPane getView(String viewName) {
        return views.get(viewName);
    }
}
