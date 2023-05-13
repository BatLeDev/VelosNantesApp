import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Création du label
        Label label = new Label("Hello, wordl!");

        // Création du layout et ajout du label
        StackPane root = new StackPane();
        root.getChildren().add(label);

        // Création de la scène
        Scene scene = new Scene(root, 300, 200);

        // Configuration de la scène principale et affichage de la fenêtre
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello JavaFX");
        primaryStage.show();
    }
}
