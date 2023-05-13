package controllers;

// JavaFX imports
import javafx.fxml.FXML;

public class ExportController {
    public void initialize() {
        System.out.println("ExportController initialized");
    }

    @FXML
    public void download() {
        System.out.println("Exporting...");
    }
}
