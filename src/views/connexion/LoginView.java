package views.connexion;

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

public class LoginView extends AnchorPane {
    private Button exitBtn;
    private Button leftButton;
    private Button connexionButton;
    private TextField utilisateurField;
    private PasswordField passwordField;
    private Text errorMessage;
    private StackPane errorContainer;

    public LoginView() {
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

        this.exitBtn = new Button();
        this.exitBtn.setGraphic(new ImageView(new Image("./ressources/images/exit-black.png")));
        this.exitBtn.getStyleClass().add("action-button");
        AnchorPane.setTopAnchor(this.exitBtn, 10.0);
        AnchorPane.setRightAnchor(this.exitBtn, 10.0);
        getChildren().add(logoImageView);

        // Content
        // content-left
        Text titleLeft = new Text("Nouveau ici ?");
        titleLeft.getStyleClass().add("title-banner");
        Text descriptionText = new Text("Inscrivez-vous et découvrez un nouveau monde de possibilités.");
        descriptionText.setWrappingWidth(325);
        descriptionText.getStyleClass().add("description-banner");
        this.leftButton = new Button("S'inscrire");
        this.leftButton.getStyleClass().add("button-banner");

        VBox contentLeft = new VBox();
        contentLeft.getStyleClass().add("content");
        contentLeft.setSpacing(20);
        contentLeft.getChildren().addAll(titleLeft, descriptionText, this.leftButton);

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
        this.connexionButton = new Button("Se connecter");
        this.connexionButton.getStyleClass().add("button-center");

        this.errorMessage = new Text("coucou");
        this.errorMessage.setWrappingWidth(325);
        this.errorMessage.getStyleClass().add("error-message");
        this.errorContainer = new StackPane(errorMessage);
        this.errorContainer.getStyleClass().add("error-message-container");
        this.errorContainer.setVisible(false);

        VBox contentRight = new VBox();
        contentRight.getStyleClass().add("content");
        contentRight.setSpacing(20);
        contentRight.getChildren().addAll(titleRight, this.utilisateurField, this.passwordField, this.connexionButton, this.errorContainer);

        AnchorPane.setTopAnchor(contentRight, 0.0);
        AnchorPane.setBottomAnchor(contentRight, 0.0);
        AnchorPane.setLeftAnchor(contentRight, 500.0);
        AnchorPane.setRightAnchor(contentRight, 0.0);

        getChildren().addAll(contentLeft, contentRight, this.exitBtn);
        getStyleClass().add("login-view");
    }

    public Button getExitBtn() {
        return this.exitBtn;
    }

    public Button getLeftButton() {
        return this.leftButton;
    }

    public Button getRightButton() {
        return this.connexionButton;
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
