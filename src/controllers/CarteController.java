package controllers;

// JavaFX imports
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarteController {


    // ------------------------------ FXML elements ------------------------------
    @FXML
    private ImageView carteImageView;

    // ----------------------------------------------------------------------------


    // ------------------------------ Initializer ------------------------------

    public void initialize() {
        String imagePath = "../ressources/images/carte.png";
        Image carteImage = new Image(getClass().getResourceAsStream(imagePath));
        carteImageView.setImage(carteImage);
    }

    // ------------------------------ Event handlers ------------------------------
    @FXML
    private void zoomIn() {
        System.out.println("Zoom in");
    }

    @FXML
    private void zoomOut() {
        System.out.println("Zoom out");
    }
}
