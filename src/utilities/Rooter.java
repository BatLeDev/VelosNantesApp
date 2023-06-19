package utilities;

// Java imports
import java.util.HashMap;

import controllers.ModificationController;
import controllers.NavbarController;
import controllers.connexion.RegisterController;

// JavaFX imports
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Views imports
import views.*;
import views.connexion.*;

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
    private HashMap<String, Pane> views;

    /**
     * The permission of the user logged
     * Null if no user is logged
     * "Utilisateur", "Elu", "Administrateur"
     */
    private String typeDeCompte;

    private NavbarView navbarView;



    /**
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
        this.views = new HashMap<String, Pane>();

        // initialization of pages
        CarteView carteView = new CarteView(this);
        views.put("Carte", carteView);

        GraphiqueView graphiqueView = new GraphiqueView(this);
        views.put("Graphique", graphiqueView);

        ExporterView exporterView = new ExporterView(this);
        views.put("Exporter", exporterView);

        ModificationController modificationController = new ModificationController(this);
        ModificationView modificationView = new ModificationView(modificationController);
        views.put("Modification", modificationView);

        LoginView loginView = new LoginView(this);
        views.put("Login", loginView);

        RegisterView registerView = new RegisterView(this);
        views.put("Register", registerView);

        NavbarController navbarController = new NavbarController(this);
        this.navbarView = new NavbarView(navbarController);
        navbarController.setNavbarView(navbarView);

        // Initialisation of the main BorderPane
        this.root = new BorderPane();

        // Initialisation of the main Scene
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
     * @param showNavbar If true, the navbar is displayed
     * @param pageTitle The title of the page to display
     */
    public void changePage(boolean showNavbar, String pageTitle) {
        // Get the view to display
        Pane page = views.get(pageTitle);

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
     */
    public void changePermission(String typeDeCompte) {
        // Get the name of the calling class
        String callingClassName = new Throwable().getStackTrace()[1].getClassName();

        // Check if the calling class is LoginController
        if (!callingClassName.equals("controllers.connexion.LoginController")) {
            throw new SecurityException(
                    "Permission refusée : Accès à la méthode changePermission est limité à la classe LoginController.");
        }

        // Change the permission
        this.typeDeCompte = typeDeCompte;
        this.navbarView.updateLogged(typeDeCompte);
    }

}
