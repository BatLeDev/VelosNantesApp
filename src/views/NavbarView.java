package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class NavbarView extends BorderPane {

    private Button exporterButton;
    private Button carteButton;
    private Button graphiqueButton;
    private Button loginButton;
    private Button registerButton;

    public NavbarView() {
        carteButton = new Button("Carte");
        graphiqueButton = new Button("Graphique");
        exporterButton = new Button("Exporter");
        loginButton = new Button("Se connecter");
        registerButton = new Button("S'inscrire");

        // Création de l'ImageView avec le logo souhaité
        Image logoImage = new Image("./ressources/images/logo-full.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200);
        logoImageView.setPreserveRatio(true);

        // Création de la boîte horizontale pour les boutons de la navbar
        HBox navbarBox = new HBox(10);
        Image rondImg = new Image("./ressources/images/rond.png");
        ImageView rondObject = new ImageView(rondImg);
        ImageView rondObject2 = new ImageView(rondImg);
        navbarBox.getChildren().addAll(carteButton, rondObject, graphiqueButton, rondObject2, exporterButton);

        // Création de la boîte horizontale pour les boutons de connexion et d'inscription
        HBox loginBox = new HBox(10);
        loginBox.getChildren().addAll(loginButton, registerButton);
        loginBox.setAlignment(Pos.CENTER_RIGHT);

        // Positionnement des éléments
        setLeft(logoImageView);
        setCenter(navbarBox);
        setRight(loginBox);

        // Ajout de la classe CSS navbar
        getStyleClass().add("navbar-view");
        navbarBox.getStyleClass().add("navbar-box");
        loginBox.getStyleClass().add("login-box");
    }

    public Button getExporterButton() {
        return exporterButton;
    }

    public Button getCarteButton() {
        return carteButton;
    }

    public Button getGraphiqueButton() {
        return graphiqueButton;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }
}
