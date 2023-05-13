package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NavbarController {
    @FXML
    private Button carteButton;

    @FXML
    private Button graphButton;

    @FXML
    private Button exportButton;


    @FXML
    private void accueilButtonClick() {
        Rooter root = new Rooter(carteButton);
    }

    @FXML
    private void profilButtonClick() {
        Rooter root = new Rooter(graphButton);
    }

    @FXML
    private void parametresButtonClick() {
        Rooter root = new Rooter(exportButton);
    }

}
