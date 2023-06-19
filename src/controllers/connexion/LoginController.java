package controllers.connexion;

import javafx.event.ActionEvent;
import utilities.Rooter;
import views.connexion.LoginView;

import database.DatabaseAccess;

public class LoginController {
    private Rooter rooter;
    private LoginView loginView;

    public LoginController(Rooter rooter, LoginView loginView) {
        this.rooter = rooter;
        this.loginView = loginView;
    }

    public void exit(ActionEvent event) {
        rooter.changePage(true, "Carte");
    }

    public void register(ActionEvent event) {
        rooter.changePage(false, "Register");
    }

    public void login(ActionEvent event) {
        String[] fields = this.loginView.getFields();

        int checkLogin = DatabaseAccess.checkLogin(fields[0], fields[1]);
        if (checkLogin > 0) {
            // Get type de compte
            String typeDeCompte = DatabaseAccess.getTypeDeCompte(fields[0]);
            this.rooter.changePermission(typeDeCompte);
            rooter.changePage(true, "Carte");
        } else if (checkLogin == 0) {
            loginView.showErrorMessage("Mot de passe incorrect");
        } else {
            loginView.showErrorMessage("Utilisateur inconnu");
        }
    }
}
