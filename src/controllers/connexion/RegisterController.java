package controllers.connexion;

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
    }

}
