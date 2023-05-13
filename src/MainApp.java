
// JavaFX imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main class of the application
 * Run this class to launch the application
 */
public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args); // Create and open the main stage (window)
    }

    /**
     * Load the FXML file and display the scene
     * 
     * @param primaryStage the main stage (window)
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/GraphView.fxml"));
            BorderPane root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Calendar");
            primaryStage.show();

        } catch (Exception e) { // Catch any exception and print the stack trace
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.out.println("Closing the application");
    }
}
