package views.connexion;

import controllers.connexion.RegisterController;
import utilities.Rooter;

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

public class RegisterView extends AnchorPane {
    private RegisterController registerController;

    public RegisterView(Rooter rooter) {
        this.registerController = new RegisterController(rooter, this);

        // Background
        Image banner = new Image("./ressources/images/banner-connexion.png");
        ImageView bannerView = new ImageView(banner);
        bannerView.fitHeightProperty().bind(heightProperty());
        bannerView.setPreserveRatio(true);
        Region widthConstraint = new Region();
        widthConstraint.prefWidthProperty().bind(widthProperty().multiply(0.7));
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
        exitBtn.setOnAction(e -> registerController.exit());

        AnchorPane.setTopAnchor(exitBtn, 10.0);
        AnchorPane.setRightAnchor(exitBtn, 10.0);

        // Content
        // content-left
        Text titleLeft = new Text("D\u00E9ja Inscrit ?");
        titleLeft.getStyleClass().add("title-banner");
        Text descriptionText = new Text("Si vous avez d\u00E9j\u00E0 votre propre compte, connectez-vous simplement.");
        descriptionText.setWrappingWidth(325);
        descriptionText.getStyleClass().add("description-banner");
        Button connextionPage = new Button("Se connecter");
        connextionPage.getStyleClass().add("button-banner");

        VBox contentLeft = new VBox();
        contentLeft.getStyleClass().add("content");
        contentLeft.setSpacing(20);
        contentLeft.getChildren().addAll(titleLeft, descriptionText, connextionPage);

        AnchorPane.setTopAnchor(contentLeft, 0.0);
        AnchorPane.setBottomAnchor(contentLeft, 0.0);
        AnchorPane.setLeftAnchor(contentLeft, 77.5);

        // content-right
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
        
        Button inscriptionBtn = new Button("S'inscrire");
        inscriptionBtn.getStyleClass().add("button-center");
        Text descText = new Text("Si vous faites parti de la mairie de Nantes, contactez un responsable pour avoir acc\u00E8s a tous les privil\u00E8ges.");
        descText.setWrappingWidth(400);
        descText.getStyleClass().add("description-center");

        this.errorMessage = new Text("coucou");
        this.errorMessage.setWrappingWidth(325);
        this.errorMessage.getStyleClass().add("error-message");
        this.errorContainer = new StackPane(errorMessage);
        this.errorContainer.getStyleClass().add("error-message-container");
        this.errorContainer.setVisible(false);

        VBox contentRight = new VBox();
        contentRight.getStyleClass().add("content");
        contentRight.setSpacing(20);
        contentRight.getChildren().addAll(titleRight, utilisateurField, passwordField, passwordFieldConfirm, inscriptionBtn, descText, this.errorContainer);

        AnchorPane.setTopAnchor(contentRight, 0.0);
        AnchorPane.setBottomAnchor(contentRight, 0.0);
        AnchorPane.setLeftAnchor(contentRight, 500.0);
        AnchorPane.setRightAnchor(contentRight, 0.0);

        getChildren().addAll(contentLeft, contentRight, exitBtn);

        getStyleClass().add("register-view");
    }

    public String[] getFields() {
        String[] fields = new String[3];
        fields[0] = this.utilisateurField.getText();
        fields[1] = this.passwordField.getText();
        fields[2] = this.passwordFieldConfirm.getText();

        return fields;
    }

    public void showErrorMessage(String message) {
        this.errorMessage.setText(message);
        this.errorContainer.setVisible(true);
    }
}
