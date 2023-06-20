package views.connexion;

// JavaFX imports
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

// Project imports
import controllers.connexion.LoginController;
import utilities.Rooter;

/**
 * This class represents the view of the Login page.
 * <p> It content a left part with a text and a button to register and right part with a form to login.
 */
public class LoginView extends ConnexionView {
    private LoginController loginController;

    /**
     * Initialize elements of the view and create the controller
     * 
     * @param rooter The rooter of the application
     */
    public LoginView(Rooter rooter) {
        this.loginController = new LoginController(rooter, this);
        
        initializeBackground(loginController);
        String title = "Nouveau ici ?";
        String text = "Inscrivez-vous et d\u00E9couvrez un nouveau monde de possibilit\u00E9s.";
        String buttonText = "S'inscrire";
        initializeContentLeft(title, text, buttonText);
        this.switchPageBtn.setOnAction(e -> loginController.register(e));

        // ------------------------------ content-right ------------------------------
        Text titleRight = new Text("Connectez-vous \u00E0 votre compte !");
        titleRight.getStyleClass().add("title-center");

        this.utilisateurField = new TextField();
        this.utilisateurField.getStyleClass().add("input-field");
        this.utilisateurField.setPromptText("Utilisateur");

        this.passwordField = new PasswordField();
        this.passwordField.getStyleClass().add("input-field");
        this.passwordField.setPromptText("Mot de passe");

        Button connexionButton = new Button("Se connecter");
        connexionButton.getStyleClass().add("button-center");
        connexionButton.setOnAction(this.loginController::login);

        this.errorMessage = new Text();
        this.errorMessage.setWrappingWidth(325);
        this.errorMessage.getStyleClass().add("error-message");
        this.errorContainer = new StackPane(errorMessage);
        this.errorContainer.getStyleClass().add("error-message-container");
        this.errorContainer.setVisible(false);

        VBox contentRight = new VBox();
        contentRight.getStyleClass().add("content");
        contentRight.setSpacing(20);
        contentRight.getChildren().addAll(titleRight, this.utilisateurField, this.passwordField, connexionButton, this.errorContainer);

        AnchorPane.setTopAnchor(contentRight, 0.0);
        AnchorPane.setBottomAnchor(contentRight, 0.0);
        AnchorPane.setLeftAnchor(contentRight, 500.0);
        AnchorPane.setRightAnchor(contentRight, 0.0);

        getChildren().addAll(this.contentLeft, contentRight, this.exitBtn);
        getStyleClass().add("login-view");
    }

    /**
     * Get the fields of the form
     * <p> format : {@code [utilisateur, password]}
     * 
     * @return The fields of the form in an String[2]
     */
    public String[] getFields() {
        String[] fields = new String[2];
        fields[0] = this.utilisateurField.getText();
        fields[1] = this.passwordField.getText();
        return fields;
    }
}
