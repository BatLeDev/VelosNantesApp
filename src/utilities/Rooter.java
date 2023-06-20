package utilities;

// Java imports
import java.util.HashMap;

// JavaFX imports
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Project imports
import views.*;
import views.connexion.*;

import controllers.ModificationController;

/**
 * This class represents the rooter of the application. It's the principal class because it's used to display all views.
 * <p>Generate all views, allow to change the view displayed, display or hide the navbar, change the permission of the user logged.
 */
public class Rooter {
    /**
     * Main BorderPane
     * <p>His content is changed to display the correct view.
     * At the top of the BorderPane, the navbar is displayed (or not);
     * and at the center of the BorderPane, the view is displayed
     */
    private BorderPane root;

    /**
     * This scene is displayed in the main window
     */
    private Scene mainScene;

    /**
     * {@code HashMap} of all views
     * <ul>
     * <li>The key is the title of the view
     * <li>The value is the view
     * </ul>
     * It's used to get a view by his title
     */
    private HashMap<String, Pane> views;

    /**
     * The navbar displayed (or not) at the top of the main BorderPane
     */
    private NavbarView navbarView;

    private String typeDeCompte;

    /**
     * This method is called by the MainApp class
     * 
     * <p>Initialise the rooter:
     * <ul>
     * <li>Create all views
     * <li>Create the navbar
     * <li>Create the main BorderPane
     * <li>Create the main Scene
     * <ul>
     * 
     * @param primaryStage generated by javafx
     */
    public Rooter(Stage primaryStage) {
        // Initialisation of the HashMap of views
        this.views = new HashMap<String, Pane>();

        // initialization of pages
        CarteView carteView = new CarteView(this);
        views.put("Carte", carteView);

        GraphiqueView graphiqueView = new GraphiqueView(this);
        views.put("Graphique", graphiqueView);

        ExporterView exporterView = new ExporterView();
        views.put("Exporter", exporterView);

        ModificationController modificationController = new ModificationController(this);
        ModificationView modificationView = new ModificationView(modificationController);
        views.put("Modification", modificationView);

        LoginView loginView = new LoginView(this);
        views.put("Login", loginView);

        RegisterView registerView = new RegisterView(this);
        views.put("Register", registerView);

        this.navbarView = new NavbarView(this);

        // Initialization of the main BorderPane
        this.root = new BorderPane();

        // Initialization of the main Scene
        this.mainScene = new Scene(root);

        // Add CSS Files
        this.mainScene.getStylesheets().add("file:src/ressources/css/carte.css");
        this.mainScene.getStylesheets().add("file:src/ressources/css/navbar.css");
        this.mainScene.getStylesheets().add("file:src/ressources/css/connexion.css");

        // Configuration of the main window
        primaryStage.setScene(mainScene);
    }

    /**
     * Change the page displayed
     * 
     * @param showNavbar If {@code true}, the {@code Navbar} is displayed
     * @param pageTitle The title of the page to display
     * @throws NullPointerException if the page doesn't exist
     */
    public void changePage(boolean showNavbar, String pageTitle) {
        // Get the view to display
        Pane page = views.get(pageTitle);

        if (page == null) {
            throw new NullPointerException("utilisies.Rooter.changePage : The page " + pageTitle + " doesn't exist.");
        }

        // Display the view in the center of the main BorderPane
        root.setCenter(page);

        // Display or hide the navbar
        if (showNavbar) {
            this.navbarView.setPageSelected(pageTitle); // Change the page selected in the navbar
            root.setTop(this.navbarView);
        } else {
            root.setTop(null);
        }
    }

    /**
     * Get a view by his title
     * 
     * @param viewName The title of the view to get
     * @return The Pane view
     */
    public Pane getView(String viewName) {
        return views.get(viewName);
    }

    /**
     * Change the views displayed from permissions of the user logged
     * 
     * @param typeDeCompte The permission of the user (null, "Utilisateur", "Elu", "Administrateur")
     * @throws SecurityException if the calling class isn't LoginController
     */
    public void changePermission(String typeDeCompte) {
        this.navbarView.updateLogged(typeDeCompte);
        this.typeDeCompte = typeDeCompte;
    }

    /**
     * Get the permission of the user logged
     * 
     * @return The permission of the user (null, "Utilisateur", "Elu", "Administrateur")
     */
    public String getPermission() {
        return this.typeDeCompte;
    }

}
