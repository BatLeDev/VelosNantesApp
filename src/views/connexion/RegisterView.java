package views.connexion;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RegisterView extends AnchorPane {
    private Button exitBtn;
    private Button leftButton;

    public RegisterView() {
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

        this.exitBtn = new Button();
        this.exitBtn.setGraphic(new ImageView(new Image("./ressources/images/exit-black.png")));
        this.exitBtn.getStyleClass().add("action-button");
        AnchorPane.setTopAnchor(this.exitBtn, 10.0);
        AnchorPane.setRightAnchor(this.exitBtn, 10.0);
        getChildren().add(logoImageView);

        // Content
        // content-left
        Text titleLeft = new Text("D\u00E9ja Inscrit ?");
        titleLeft.getStyleClass().add("title-banner");
        Text descriptionText = new Text("Si vous avez d\u00E9j\u00E0 votre propre compte, connectez-vous simplement.");
        descriptionText.setWrappingWidth(325);
        descriptionText.getStyleClass().add("description-banner");
        this.leftButton = new Button("Se connecter");
        this.leftButton.getStyleClass().add("button-banner");

        VBox contentLeft = new VBox();
        contentLeft.getStyleClass().add("content");
        contentLeft.setSpacing(20);
        contentLeft.getChildren().addAll(titleLeft, descriptionText, this.leftButton);

        AnchorPane.setTopAnchor(contentLeft, 0.0);
        AnchorPane.setBottomAnchor(contentLeft, 0.0);
        AnchorPane.setLeftAnchor(contentLeft, 77.5);

        // content-right
        Text titleRight = new Text("Cr\u00E9er un compte");
        titleRight.getStyleClass().add("title-center");
        TextField utilisateurField = new TextField();
        utilisateurField.getStyleClass().add("input-field");
        utilisateurField.setPromptText("Utilisateur");
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("input-field");
        passwordField.setPromptText("Mot de passe");
        PasswordField passwordFieldConfirm = new PasswordField();
        passwordFieldConfirm.getStyleClass().add("input-field");
        passwordFieldConfirm.setPromptText("Confirmez le mot de passe");
        Button connexionButton = new Button("S'inscrire");
        connexionButton.getStyleClass().add("button-center");
        Text descText = new Text("Si vous faites parti de la mairie de Nantes, contactez un responsable pour avoir acc\u00E8s a tous les privil\u00E8ges.");
        descText.setWrappingWidth(400);
        descText.getStyleClass().add("description-center");

        VBox contentRight = new VBox();
        contentRight.getStyleClass().add("content");
        contentRight.setSpacing(20);
        contentRight.getChildren().addAll(titleRight, utilisateurField, passwordField, passwordFieldConfirm, connexionButton, descText);

        AnchorPane.setTopAnchor(contentRight, 0.0);
        AnchorPane.setBottomAnchor(contentRight, 0.0);
        AnchorPane.setLeftAnchor(contentRight, 500.0);
        AnchorPane.setRightAnchor(contentRight, 0.0);

        getChildren().addAll(contentLeft, contentRight, this.exitBtn);

        getStyleClass().add("register-view");
    }

    public Button getExitBtn() {
        return this.exitBtn;
    }

    public Button getLeftButton() {
        return this.leftButton;
    }
}
