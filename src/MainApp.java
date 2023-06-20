// JavaFX imports
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// Project imports
import database.DatabaseAccess;
import utilities.Rooter;

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
     * <p>Create : 
     * <ul>
     * <li>the {@link Rooter} instance</li>
     * <li>all objects from the database</li>
     * <li>show the main page (Carte)</li>
     * </ul>
     * 
     * @param primaryStage Stage instance (main window, generate by JavaFX)
     */
    @Override
    public void start(Stage primaryStage) {
        // Download Database
        DatabaseAccess.getQuartiers();
        DatabaseAccess.getCompteurs();
        DatabaseAccess.getJour();
        DatabaseAccess.getReleveJournaliers();

        rooter = new Rooter(primaryStage); // Rooter initialization

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