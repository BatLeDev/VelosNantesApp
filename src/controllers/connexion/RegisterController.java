package controllers.connexion;

import utilities.Rooter;
import views.connexion.RegisterView;

public class RegisterController {
    private Rooter rooter;
    private RegisterView registerView;

    public RegisterController(Rooter rooter, RegisterView registerView) {
        this.rooter = rooter;
        this.registerView = registerView;
    }

    public void exit() {
        rooter.changePage(true, "Carte");
    }

    public void login() {
        rooter.changePage(true, "Login");
    }
}
