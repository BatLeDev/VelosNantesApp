package views.connexion;

import controllers.connexion.LoginController;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utilities.Rooter;

public class LoginView extends AnchorPane {
    private LoginController loginController;

    private TextField utilisateurField;
    private PasswordField passwordField;
    private Text errorMessage;
    private StackPane errorContainer;

    public LoginView(Rooter rooter) {
        this.loginController = new LoginController(rooter, this);
        
        // Background
        Image banner = new Image("./ressources/images/banner-connexion.png");
        ImageView bannerView = new ImageView(banner);
        bannerView.fitHeightProperty().bind(heightProperty());
        bannerView.setPreserveRatio(true);
        Region widthConstraint = new Region();
        widthConstraint.prefWidthProperty().bind(widthProperty().multiply(0.3));
        getChildren().addAll(bannerView, widthConstraint);

        // Foreground
        // Header
        Image logoImage = new Image("./ressources/images/logo-full-white.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200);
        logoImageView.setPreserveRatio(true);
        logoImageView.getStyleClass().add("logo");
        getChildren().add(logoImageView);

        Button exitBtn = new Button();
        exitBtn.setGraphic(new ImageView(new Image("./ressources/images/exit-black.png")));
        exitBtn.getStyleClass().add("action-button");
        exitBtn.setOnAction(this.loginController::exit);
        AnchorPane.setTopAnchor(exitBtn, 10.0);
        AnchorPane.setRightAnchor(exitBtn, 10.0);

        // Content
        // content-left
        Text titleLeft = new Text("Nouveau ici ?");
        titleLeft.getStyleClass().add("title-banner");

        Text descriptionText = new Text("Inscrivez-vous et découvrez un nouveau monde de possibilités.");
        descriptionText.setWrappingWidth(325);
        descriptionText.getStyleClass().add("description-banner");

        Button inscriptionBtn = new Button("S'inscrire");
        inscriptionBtn.getStyleClass().add("button-banner");
        inscriptionBtn.setOnAction(this.loginController::register);

        VBox contentLeft = new VBox();
        contentLeft.getStyleClass().add("content");
        contentLeft.setSpacing(20);
        contentLeft.getChildren().addAll(titleLeft, descriptionText, inscriptionBtn);

        AnchorPane.setTopAnchor(contentLeft, 0.0);
        AnchorPane.setBottomAnchor(contentLeft, 0.0);
        AnchorPane.setLeftAnchor(contentLeft, 77.5);

        // content-right
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

        this.errorMessage = new Text("coucou");
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

        getChildren().addAll(contentLeft, contentRight, exitBtn);
        getStyleClass().add("login-view");
    }

    public String[] getFields() {
        String[] fields = new String[2];
        fields[0] = this.utilisateurField.getText();
        fields[1] = this.passwordField.getText();
        return fields;
    }

    public void showErrorMessage(String message) {
        this.errorMessage.setText(message);
        this.errorContainer.setVisible(true);
    }
}
