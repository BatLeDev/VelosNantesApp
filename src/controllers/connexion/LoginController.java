package controllers.connexion;

import javafx.event.ActionEvent;
import utilities.Rooter;
import views.connexion.LoginView;

import database.DatabaseAccess;

public class LoginController {
    private Rooter rooter;
    private LoginView loginView;

    public LoginController(Rooter rooter) {
        this.rooter = rooter;
        this.loginView = (LoginView) rooter.getView("Login");

        this.setup();
    }

    public void setup() {
        loginView.getExitBtn().setOnAction(event -> {
            rooter.changePage(true, "Carte");
        });

        loginView.getLeftButton().setOnAction(event -> {
            rooter.changePage(false, "Register");
        });

        loginView.getRightButton().setOnAction(this::login);
    }

    private void login(ActionEvent event) {
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
