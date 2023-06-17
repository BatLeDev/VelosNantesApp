package controllers.connexion;

import javax.swing.Action;

import javafx.event.ActionEvent;
import utilities.Rooter;
import views.connexion.LoginView;

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
        System.out.println("Connection en cour...");
        String[] fields = this.loginView.getFields();
        System.out.println("Utilisateur : " + fields[0]);
        System.out.println("Mot de passe : " + fields[1]);

        if (fields[0].equals("admin") && fields[1].equals("admin")) {
            System.out.println("Connexion réussie !");
            this.rooter.changePage(true, "Carte");
        } else {
            this.loginView.showErrorMessage("Erreur de connexion");
        }
    }
}
