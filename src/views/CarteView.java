package views;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * This class represents the view of the Carte page.
 * This page is a map, with a legend and sensors points.
 */
public class CarteView extends BorderPane {

    // Elements of the view
    private Button exporterButton;

    /**
     * Constructor
     * This method is called by the rooter to create the view
     * 
     * Initialise the elements of the view
     */
    public CarteView() {
        // Creation of the elements of the view
        Text text = new Text("carte");
        exporterButton = new Button("Voir Exporter");

        // Positioning of the elements
        setCenter(text);
        setBottom(exporterButton);
        setAlignment(text, Pos.CENTER);
        setAlignment(exporterButton, Pos.CENTER);
        setMargin(text, new Insets(10));
        setMargin(exporterButton, new Insets(10));
    }

    /**
     * Getter for the exporterButton
     * 
     * @return exporterButton
     */
    public Button getExporterButton() {
        return exporterButton;
    }
}
