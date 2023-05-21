package views;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * This class represents the view of the Exporter page.
 * This page is a form to export data.
 */
public class GraphiqueView extends BorderPane {

    // Elements of the view
    private Button exempleButton;

    /**
     * Constructor
     * This method is called by the rooter to create the view
     * 
     * Initialise the elements of the view
     */
    public GraphiqueView() {
        // Creation of the elements of the view
        Text text = new Text("Exemple requete");
        exempleButton = new Button("Exemple");

        // Positioning of the elements
        setCenter(text);
        setBottom(exempleButton);
        setAlignment(text, Pos.CENTER);
        setAlignment(exempleButton, Pos.CENTER);
        setMargin(text, new Insets(10));
        setMargin(exempleButton, new Insets(10));
    }

    public Button getExempleButton() {
        return exempleButton;
    }

}

