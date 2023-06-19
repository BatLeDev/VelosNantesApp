package controllers.connexion;

import javafx.event.ActionEvent;
import utilities.Rooter;

/**
 * This abstract class is a template for the controller of the Login and Register page.
 */
public abstract class ConnexionController {
    protected Rooter rooter;

    /**
     * Initialize the controller : set the rooter
     * 
     * @param rooter The rooter of the application
     */
    public ConnexionController(Rooter rooter) {
        this.rooter = rooter;
    }

    /**
     * Return to the Carte page
     * 
     * @param event The event triggered when the user click on the exit button
     */
    public void exit(ActionEvent event) {
        rooter.changePage(true, "Carte");
    }

}
