package controllers.connexion;

// JavaFX imports
import javafx.event.ActionEvent;

// Project imports
import utilities.Rooter;
import database.DatabaseAccess;
import views.connexion.LoginView;

/**
 * This class represents the controller of the Login page.
 * <p> It contains all needed methods to interact with the user and the database
 */
public class LoginController {
    private Rooter rooter;
    private LoginView loginView;

    /**
     * Initialize the controller
     * 
     * @param rooter The rooter of the application
     * @param loginView The view of the Login page
     */
    public LoginController(Rooter rooter, LoginView loginView) {
        this.rooter = rooter;
        this.loginView = loginView;
    }

    /**
     * Change the page to the Carte page
     * 
     * @param event The event triggered when the user click on the exit button
     */
    public void exit(ActionEvent event) {
        rooter.changePage(true, "Carte");
    }

    /**
     * Change the page to the Register page
     * 
     * @param event The event triggered when the user click on the register button
     */
    public void register(ActionEvent event) {
        rooter.changePage(false, "Register");
    }

    /**
     * Check the login and change the page to the Carte page if the login is correct
     * 
     * @param event The event triggered when the user click on the login button
     */
    public void login(ActionEvent event) {
        String[] fields = this.loginView.getFields();

        int checkLogin = DatabaseAccess.checkLogin(fields[0], fields[1]);
        if (checkLogin > 0) { // -1 if username doesn't exist, 0 if password is incorrect, 1 if all is correct
            String typeDeCompte = DatabaseAccess.getTypeDeCompte(fields[0]); // Get type de compte from database
            this.rooter.changePermission(typeDeCompte);
            rooter.changePage(true, "Carte");
        } else if (checkLogin == 0) {
            loginView.showErrorMessage("Mot de passe incorrect");
        } else {
            loginView.showErrorMessage("Utilisateur inconnu");
        }
    }

}
