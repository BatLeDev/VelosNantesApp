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
import controllers.connexion.RegisterController;
import utilities.Rooter;

/**
 * This class represents the view of the Register page.
 * <p> It content a left part with a text and a button to login and right part with a form to register.
 */
public class RegisterView extends ConnexionView {
    private RegisterController registerController;

    private PasswordField passwordFieldConfirm;

    /**
     * Initialize elements of the view and create the controller
     * 
     * @param rooter The rooter of the application
     */
    public RegisterView(Rooter rooter) {
        this.registerController = new RegisterController(rooter, this);

        initializeBackground(registerController);
        String title = "D\u00E9ja Inscrit ?";
        String text = "Si vous avez d\u00E9j\u00E0 votre propre compte, connectez-vous simplement.";
        String buttonText = "Se connecter";
        initializeContentLeft(title, text, buttonText);
        this.switchPageBtn.setOnAction(e -> registerController.login(e));

        // ------------------------------ content-right ------------------------------
        Text titleRight = new Text("Cr\u00E9er un compte");
        titleRight.getStyleClass().add("title-center");
        this.utilisateurField = new TextField();
        this.utilisateurField.getStyleClass().add("input-field");
        this.utilisateurField.setPromptText("Utilisateur");
        this.passwordField = new PasswordField();
        this.passwordField.getStyleClass().add("input-field");
        this.passwordField.setPromptText("Mot de passe");
        this.passwordFieldConfirm = new PasswordField();
        this.passwordFieldConfirm.getStyleClass().add("input-field");
        this.passwordFieldConfirm.setPromptText("Confirmez le mot de passe");
        
        Button inscriptionButton = new Button("S'inscrire");
        inscriptionButton.getStyleClass().add("button-center");
        inscriptionButton.setOnAction(e -> registerController.register());

        Text descText = new Text("Si vous faites parti de la mairie de Nantes, contactez un responsable pour avoir acc\u00E8s a tous les privil\u00E8ges.");
        descText.setWrappingWidth(400);
        descText.getStyleClass().add("description-center");

        this.errorMessage = new Text();
        this.errorMessage.setWrappingWidth(325);
        this.errorMessage.getStyleClass().add("error-message");
        this.errorContainer = new StackPane(errorMessage);
        this.errorContainer.getStyleClass().add("error-message-container");
        this.errorContainer.setVisible(false);

        VBox contentRight = new VBox();
        contentRight.getStyleClass().add("content");
        contentRight.setSpacing(20);
        contentRight.getChildren().addAll(titleRight, this.utilisateurField, this.passwordField, this.passwordFieldConfirm, inscriptionButton, descText, this.errorContainer);

        AnchorPane.setTopAnchor(contentRight, 0.0);
        AnchorPane.setBottomAnchor(contentRight, 0.0);
        AnchorPane.setLeftAnchor(contentRight, 500.0);
        AnchorPane.setRightAnchor(contentRight, 0.0);

        getChildren().addAll(this.contentLeft, contentRight, this.exitBtn);

        getStyleClass().add("register-view");
    }

    /**
     * Get the fields of the form
     * <p> format : {@code [utilisateur, password, passwordConfirm]}
     * 
     * @return The fields of the form in an String[3]
     */
    public String[] getFields() {
        String[] fields = new String[3];
        fields[0] = this.utilisateurField.getText();
        fields[1] = this.passwordField.getText();
        fields[2] = this.passwordFieldConfirm.getText();
        return fields;
    }

}
