package views;

import controllers.NavbarController;
// JavaFX imports
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * It's the view of the navbar. It's used to display the navbar.
 * <p> This class extends BorderPane from JavaFX.
 * <p> It contains:
 * <ul>
 * <li> A {@link Button} to go to the map page
 * <li> A {@link Button} to go to the graph page
 * <li> A {@link Button} to go to the export page
 * <li> A {@link Button} to go to the modification page (when the user is logged)
 * <li> A {@link Button} to go to the login page (when the user isn't logged)
 * <li> A {@link Button} to go to the register page (when the user isn't logged)
 * </ul>
 */
public class NavbarView extends BorderPane {
    private NavbarController navbarController;

    private Button exporterButton;
    private Button carteButton;
    private Button graphiqueButton;
    private Button modificationButton;
    private Button loginButton;
    private Button registerButton;
    private ImageView rondObject3 = new ImageView();

    public NavbarView(NavbarController navbarController) {
        this.navbarController = navbarController;
        
        carteButton = new Button("Carte");
        graphiqueButton = new Button("Graphique");
        exporterButton = new Button("Exporter");
        modificationButton = new Button("Modification");
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
        this.rondObject3 = new ImageView(rondImg);

        modificationButton.setVisible(false);
        rondObject3.setVisible(false);
        
        navbarBox.getChildren().addAll(carteButton, rondObject, graphiqueButton, rondObject2, exporterButton, this.rondObject3, modificationButton);

        // Création de la boîte horizontale pour les boutons de connexion et d'inscription
        HBox loginBox = new HBox(10);
        loginBox.getChildren().addAll(loginButton, registerButton);

        // Positionnement des éléments
        setLeft(logoImageView);
        setCenter(navbarBox);
        setRight(loginBox);

        // Ajout de la classe CSS navbar
        getStyleClass().add("navbar-view");
        navbarBox.getStyleClass().add("navbar-box");
        loginBox.getStyleClass().add("login-box");
        loginButton.getStyleClass().add("login-button");
        registerButton.getStyleClass().add("register-button");
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

    public Button getModificationButton() {
        return modificationButton;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public void setPageSelected(String pageTitle) {
        carteButton.getStyleClass().remove("selected");
        graphiqueButton.getStyleClass().remove("selected");
        exporterButton.getStyleClass().remove("selected");
        modificationButton.getStyleClass().remove("selected");

        switch (pageTitle) {
            case "Carte":
                carteButton.getStyleClass().add("selected");
                break;
            case "Graphique":
                graphiqueButton.getStyleClass().add("selected");
                break;
            case "Exporter":
                exporterButton.getStyleClass().add("selected");
                break;
            case "Modification":
                modificationButton.getStyleClass().add("selected");
                break;
        }
    }

    public void updateLogged(String typeDeCompte) {
        if (typeDeCompte != null) {
            loginButton.setVisible(false);
            registerButton.setVisible(false);
            if (typeDeCompte.equals("Administrateur") || typeDeCompte.equals("Elu")) {
                modificationButton.setVisible(true);
                rondObject3.setVisible(true);
            } else {
                modificationButton.setVisible(false);
                rondObject3.setVisible(false);
            }
        } else {
            loginButton.setVisible(true);
            registerButton.setVisible(true);
            modificationButton.setVisible(false);
            rondObject3.setVisible(false);
        }
    }
}
