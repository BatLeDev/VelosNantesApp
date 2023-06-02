import javafx.application.Application;
import javafx.stage.Stage;

// Controllers import
import controllers.Rooter;
import controllers.ExporterController;
import controllers.GraphiqueController;
import controllers.CarteController;
import controllers.NavbarController;

/**
 * Main class
 * Run this class to launch the application
 * 
 * This class extends Application from JavaFX
 */
public class MainApp extends Application {

    /**
     * Rooter instance
     * Used to change the page displayed
     */
    private Rooter rooter;

    /**
     * Main method
     * 
     * @param args isn't used
     */
    public static void main(String[] args) {
        launch(args); // Start the application
    }

    /**
     * Start method
     * This method is called by the launch method
     * 
     * @param primaryStage Stage instance (main window, generate by JavaFX)
     */
    @Override
    public void start(Stage primaryStage) {
        // Creation of the rooter
        rooter = new Rooter(primaryStage);

        // Initialisation of each controller
        // Isn't necessary to keep the reference of the controller
        new ExporterController(rooter);
        new CarteController(rooter);
        new GraphiqueController(rooter);

        // Initialisation of the navbar controller
        new NavbarController(rooter);

        // Show the main page (Carte)
        rooter.changePage(true, "Carte");
    }
}