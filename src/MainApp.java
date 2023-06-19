// JavaFX imports
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// Utilities import
import utilities.Rooter;

// Controllers import
import controllers.*;
import controllers.connexion.*;

/**
 * It's the main class, run it to launch the application. 
 * This class extends Application from JavaFX.
 */
public class MainApp extends Application {

    /**
     * The unique instance of the {@link Rooter} class. It's used to change the
     * current page.
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
     * Method called by JavaFX when the application is launched.
     * It's used to initialize the application.
     * 
     * @param primaryStage Stage instance (main window, generate by JavaFX)
     */
    @Override
    public void start(Stage primaryStage) {
        // Rooter initialization
        rooter = new Rooter(primaryStage);

        // Set the icon of the application
        primaryStage.getIcons().add(new Image("./ressources/images/logo.png"));
        primaryStage.setTitle("VelosNantes");
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(800);
        primaryStage.setMaximized(true);
        primaryStage.show();

        // Show the main page (Carte)
        rooter.changePage(true, "Carte");
    }
}