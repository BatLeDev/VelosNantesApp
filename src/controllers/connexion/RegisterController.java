package controllers.connexion;

import javafx.event.ActionEvent;

import database.DatabaseAccess;
import utilities.Rooter;
import views.connexion.RegisterView;

/**
 * This class represents the controller of the Register page.
 * <p> It contains all needed methods to interact with the user and the database (create an account)
 */
public class RegisterController extends ConnexionController {
    private RegisterView registerView;

    /**
     * Initialize the controller : set the rooter and the view
     * 
     * @param rooter The rooter of the application
     * @param registerView The view of the Register page
     */
    public RegisterController(Rooter rooter, RegisterView registerView) {
        super(rooter);
        this.registerView = registerView;
    }

    /**
     * Change the page to the Login page
     * @param event
     */
    public void login(ActionEvent event) {
        rooter.changePage(false, "Login");
    }

    /**
     * Check the fields and create an account if all is correct
     */
    public void register() {
        System.out.println("Inscription");
        String[] fields = this.registerView.getFields();

        // Check if user already exist
        int checkLogin = DatabaseAccess.checkLogin(fields[0], "");
        if (checkLogin >= 0) {
            registerView.showErrorMessage("Utilisateur déjà existant");
        } else {
            if (!fields[1].equals(fields[2])) { // Check if password and confirm password are the same
                registerView.showErrorMessage("Les mots de passe ne correspondent pas");
            } else {
                DatabaseAccess.createAccount(fields[0], fields[1], "Utilisateur");
                rooter.changePage(false, "Login");
            }
        }

    }

}
