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

    // Elements
    private Button exporterButton;
    private Button carteButton;
    private Button graphiqueButton;
    private Button loginButton;
    private Button registerButton;

    private ImageView rondObject3;
    private Button modificationButton;

    private HBox navbarBox;

    /**
     * Constructor of the NavbarView class.
     * <p> Initializes the navbar view.
     * @param navbarController The controller of the navbar view
     */
    public NavbarView(NavbarController controller) {
        this.navbarController = controller;

        // Initializations of buttons
        carteButton = new Button("Carte");
        graphiqueButton = new Button("Graphique");
        exporterButton = new Button("Exporter");
        modificationButton = new Button("Modification");
        loginButton = new Button("Se connecter");
        registerButton = new Button("S'inscrire");

        // Add listeners to the buttons
        carteButton.setOnAction(e -> this.navbarController.buttonClicked("Carte"));
        graphiqueButton.setOnAction(e -> this.navbarController.buttonClicked("Graphique"));
        exporterButton.setOnAction(e -> this.navbarController.buttonClicked("Exporter"));
        modificationButton.setOnAction(e -> this.navbarController.buttonClicked("Modification"));
        loginButton.setOnAction(e -> this.navbarController.buttonClicked("Login"));
        registerButton.setOnAction(e -> this.navbarController.buttonClicked("Register"));

        // Initialize the logo
        Image logoImage = new Image("./ressources/images/logo-full.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200);
        logoImageView.setPreserveRatio(true);

        // Creation of the horizontal box for the navbar buttons
        this.navbarBox = new HBox(10);

        Image rondImg = new Image("./ressources/images/rond.png");
        ImageView rondObject = new ImageView(rondImg);
        ImageView rondObject2 = new ImageView(rondImg);
        this.rondObject3 = new ImageView(rondImg);
        
        // Add the buttons to the horizontal box
        this.navbarBox.getChildren().addAll(carteButton, rondObject, graphiqueButton, rondObject2, exporterButton);

        // Connections buttons box
        HBox loginBox = new HBox(10);
        loginBox.getChildren().addAll(loginButton, registerButton);

        // Positioning of the elements
        setLeft(logoImageView);
        setCenter(navbarBox);
        setRight(loginBox);

        // Styling
        getStyleClass().add("navbar-view");
        navbarBox.getStyleClass().add("navbar-box");
        loginBox.getStyleClass().add("login-box");
        loginButton.getStyleClass().add("login-button");
        registerButton.getStyleClass().add("register-button");
    }

    /**
     * Sets the page selected and unselects the others.
     * 
     * <p> It changes the style of the button by adding the "selected" class. 
     * Add an underline to the text of the button.
     * 
     * @param pageTitle The title of the page to set selected
     */
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

    /**
     * Update the navbar when the user is logged or not.
     * <ul>
     * <li> If the user is logged, it removes the login and register buttons and adds the modification button.
     * <li> If the user logged is an "Administrateur" or an "Elu", it adds the modification button.
     * <li> If the user isn't logged, it removes the modification button and adds the login and register buttons.
     * </ul>
     * 
     * @param typeDeCompte The type of the account of the user
     */
    public void updateLogged(String typeDeCompte) {

        if (typeDeCompte != null) { // If the user is logged
            loginButton.setVisible(false);
            registerButton.setVisible(false);

            if (typeDeCompte.equals("Administrateur") || typeDeCompte.equals("Elu")) { // If permissions are good
                this.navbarBox.getChildren().addAll(this.rondObject3, this.modificationButton);
            } else {
                this.navbarBox.getChildren().removeAll(this.rondObject3, this.modificationButton);
            }

        } else {
            loginButton.setVisible(true);
            registerButton.setVisible(true);

            this.navbarBox.getChildren().removeAll(this.rondObject3, this.modificationButton);
        }
    }

}
