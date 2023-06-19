package controllers.connexion;

import database.DatabaseAccess;
import javafx.event.ActionEvent;
import utilities.Rooter;
import views.connexion.RegisterView;

public class RegisterController {
    private Rooter rooter;
    private RegisterView registerView;

    public RegisterController(Rooter rooter) {
        this.rooter = rooter;
        this.registerView = (RegisterView) rooter.getView("Register");

        this.setup();
    }

    public void setup() {
        registerView.getExitBtn().setOnAction(event -> {
            rooter.changePage(true, "Carte");
        });

        registerView.getLeftButton().setOnAction(event -> {
            rooter.changePage(false, "Login");
        });

        registerView.getRightButton().setOnAction(this::inscription);
    }

    private void inscription(ActionEvent event) {
        System.out.println("Inscription");
        String[] fields = this.registerView.getFields();

        // Check si l'utilisateur existe déjà
        int checkLogin = DatabaseAccess.checkLogin(fields[0], "");
        if (checkLogin >= 0) {
            registerView.showErrorMessage("Utilisateur déjà existant");
        } else {
            if (!fields[1].equals(fields[2])) {
                registerView.showErrorMessage("Les mots de passe ne correspondent pas");
            } else {
                DatabaseAccess.createAcount(fields[0], fields[1], "Utilisateur");
                rooter.changePage(false, "Login");
            }
        }

    }

}
