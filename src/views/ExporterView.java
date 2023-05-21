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
public class ExporterView extends BorderPane {

    // Elements of the view
    private Button hideButton;

    /**
     * Constructor
     * This method is called by the rooter to create the view
     * 
     * Initialise the elements of the view
     */
    public ExporterView() {
        // Creation of the elements of the view
        Text text = new Text("coucou");
        hideButton = new Button("Cacher Navbar");

        // Positioning of the elements
        setCenter(text);
        setBottom(hideButton);
        setAlignment(text, Pos.CENTER);
        setAlignment(hideButton, Pos.CENTER);
        setMargin(text, new Insets(10));
        setMargin(hideButton, new Insets(10));
    }

    /**
     * Getter for the hideButton
     * 
     * @return hideButton
     */
    public Button getHideButton() {
        return hideButton;
    }
}
