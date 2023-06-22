package views.connexion;

// JavaFX imports
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

// Project imports
import controllers.connexion.ConnexionController;

/**
 * This abstract class is a template for the view of the Login and Register page.
 * <p> It content a left part with a text and a button to register, a backgrund banner and a header whitch contains a logo and a button to exit the page.
 */
public abstract class ConnexionView extends AnchorPane {
    protected Text errorMessage;
    protected StackPane errorContainer;

    protected TextField utilisateurField;
    protected PasswordField passwordField;

    protected Button exitBtn;
    protected Button switchPageBtn;
    protected VBox contentLeft;

    /**
     * Initialize elements of the background banner and the header
     * 
     * @param controller The controller shared between the two connexion sub-controllers
     */
    protected void initializeBackground(ConnexionController controller) {
        // --------------------- background banner ---------------------
        Image banner = new Image("file:./ressources/images/banner-connexion.png");
        ImageView bannerView = new ImageView(banner);
        bannerView.fitHeightProperty().bind(heightProperty());
        bannerView.setPreserveRatio(true);
        Region widthConstraint = new Region();
        widthConstraint.prefWidthProperty().bind(widthProperty().multiply(0.7));
        getChildren().addAll(bannerView, widthConstraint);

        // ------------------------------ header ------------------------------
        Image logoImage = new Image("file:./ressources/images/logo-full-white.png");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200);
        logoImageView.setPreserveRatio(true);
        logoImageView.getStyleClass().add("logo");
        getChildren().add(logoImageView);

        this.exitBtn = new Button();
        this.exitBtn.setGraphic(new ImageView(new Image("file:./ressources/images/exit-black.png")));
        this.exitBtn.getStyleClass().add("action-button");
        this.exitBtn.setOnAction(e -> controller.exit(e));

        AnchorPane.setTopAnchor(this.exitBtn, 10.0);
        AnchorPane.setRightAnchor(this.exitBtn, 10.0);
    }

    /**
     * Initialize elements of the left part of the view
     * 
     * @param title The title of the left part 
     * @param text The text of the left part 
     * @param buttonText The text of the button of the left part
     */
    protected void initializeContentLeft(String title, String text, String buttonText) {
        Text titleLeft = new Text(title);
        titleLeft.getStyleClass().add("title-banner");

        Text descriptionText = new Text(text);
        descriptionText.setWrappingWidth(325);
        descriptionText.getStyleClass().add("description-banner");

        this.switchPageBtn = new Button(buttonText);
        this.switchPageBtn.getStyleClass().add("button-banner");

        this.contentLeft = new VBox();
        this.contentLeft.getStyleClass().add("content");
        this.contentLeft.setSpacing(20);
        this.contentLeft.getChildren().addAll(titleLeft, descriptionText, this.switchPageBtn);

        AnchorPane.setTopAnchor(this.contentLeft, 0.0);
        AnchorPane.setBottomAnchor(this.contentLeft, 0.0);
        AnchorPane.setLeftAnchor(this.contentLeft, 77.5);
    }

    /**
     * Show an error message under the form, with the message in parameter
     * 
     * @param message The message to show
     */
    public void showErrorMessage(String message) {
        this.errorMessage.setText(message);
        this.errorContainer.setVisible(true);
    }
}
