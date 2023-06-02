package views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * This class represents the view of the Navbar.
 * This page is a navigation bar.
 * 
 * This view can be used in other views.
 */
public class NavbarView extends BorderPane {

    // Each button link of the navbar
    private Button exporterButton;
    private Button carteButton;

    /**
     * Constructor
     * This method is called by the rooter to create the view
     * 
     * Initialise the elements of the navbar view:
     * - Each link
     * - Icon of the application
     * - Btn of login/logout
     */
    public NavbarView() {
        // Creation of the elements of the navbar
        exporterButton = new Button("Exporter");
        carteButton = new Button("Carte");

        // Positioning of the elements
        setPadding(new Insets(10));
        setLeft(exporterButton);
        setRight(carteButton);
    }

    /**
     * Getter for the exporterButton
     * 
     * @return exporterButton
     */
    public Button getExporterButton() {
        return exporterButton;
    }

    public Button getCarteButton() {
        return carteButton;
    }

}
