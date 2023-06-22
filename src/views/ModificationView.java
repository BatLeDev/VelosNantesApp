package views;

// Java imports
import java.util.ArrayList;
import java.util.Optional;

// JavaFX imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

// Project imports
import controllers.ModificationController;
import database.DatabaseAccess;
import models.IModels;
import utilities.Rooter;


/**
 * This class represents the view of the Exporter page.
 * This page is a form to export data.
 */
public class ModificationView extends BorderPane {
    private ModificationController modificationController;

    private ToggleGroup selectionGroup;
    private TableView<IModels> table;
    private Label message;
    private ArrayList<TextField> textFields;
    private Button ajouterButton;
    private Button supprimerButton;
    private HBox selectionBox;
    private RadioButton compteRadioButton;

    public ModificationView() {
        this.modificationController = new ModificationController(this);

        this.textFields = new ArrayList<TextField>();

        this.selectionGroup = new ToggleGroup();
        RadioButton compteurRadioButton = new RadioButton("Compteur");
        compteurRadioButton.setSelected(true);
        RadioButton quartierRadioButton = new RadioButton("Quartier");
        RadioButton jourRadioButton = new RadioButton("Jour");
        RadioButton releveRadioButton = new RadioButton("Releve Journalier");
        this.compteRadioButton = new RadioButton("Compte");

        compteurRadioButton.setToggleGroup(this.selectionGroup);
        quartierRadioButton.setToggleGroup(this.selectionGroup);
        jourRadioButton.setToggleGroup(this.selectionGroup);
        releveRadioButton.setToggleGroup(this.selectionGroup);
        this.compteRadioButton.setToggleGroup(this.selectionGroup);
        this.selectionGroup.selectedToggleProperty().addListener(this.modificationController::changerTable);

        this.selectionBox = new HBox(compteurRadioButton, quartierRadioButton, jourRadioButton, releveRadioButton);
        
        selectionBox.setSpacing(10);
        selectionBox.setAlignment(javafx.geometry.Pos.CENTER);

        this.table = new TableView<IModels>();

        this.ajouterButton = new Button("Ajouter");
        this.supprimerButton = new Button("Supprimer");

        this.message = new Label("> ");
        this.message.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");

        this.setPadding(new Insets(15, 10, 10, 10));
        this.setTop(selectionBox);
        this.setBottom(this.message);
        this.setRight(supprimerButton);
        this.message.setPrefHeight(50);

        this.ajouterButton.setOnAction(this.modificationController::ajouter);
        this.modificationController.changerTable(null, null, null);

        supprimerButton.setOnAction(this.modificationController::supprimer);
    }

    public TableView<IModels> getTable() {
        return this.table;
    }

    public void setTable(TableView<IModels> table) {
        this.supprimerButton.setVisible(true);
        this.table = table;

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(this.table);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        for (TextField textField : this.textFields) {
            hBox.getChildren().add(textField);
        }
        hBox.getChildren().add(this.ajouterButton);
        borderPane.setBottom(hBox);

        this.setCenter(borderPane);
    }

    public String getSelection() {
        return ((RadioButton) this.selectionGroup.getSelectedToggle()).getText();
    }

    public void setMessage(String message) {
        this.message.setText("> " + message);
    }

    public void setTextFields(String ... textFields) {
        this.textFields = new ArrayList<>();
        for (String textField : textFields) {
            TextField textField1 = new TextField();
            textField1.setPrefWidth(100);
            textField1.setPromptText(textField);
            this.textFields.add(textField1);
        }
    }

    public ArrayList<String> getTextFields() {
        ArrayList<String> textFields = new ArrayList<>();
        for (TextField textField : this.textFields) {
            textFields.add(textField.getText());
        }
        return textFields;
    }

    public void clearTextFields() {
        for (TextField textField : this.textFields) {
            textField.clear();
        }
    }

    public boolean newAlertSuppression() {
        boolean ret = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Supprimer l'élément sélectionné");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cet élément ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ret = true;
        }
        return ret;
    }

    public void updatePermissions(String typeDeCompte) {
        if (typeDeCompte.equals("Administrateur")) {
            this.selectionBox.getChildren().add(this.compteRadioButton);
        } else {
            this.selectionBox.getChildren().remove(this.compteRadioButton);
        }
    }

    public void setAccountTable(ArrayList<String[]> comptes) {
        this.supprimerButton.setVisible(false);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Espacement horizontal entre les colonnes
        gridPane.setVgap(10); // Espacement vertical entre les lignes
        gridPane.setPadding(new Insets(10)); // Marge interne du GridPane
        
        // Configuration des contraintes de colonnes pour l'alignement
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(20);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(80);
        gridPane.getColumnConstraints().addAll(col1Constraints, col2Constraints);
        
        int rowIndex = 0; // Indice de ligne
        
        for (String[] compte : comptes) {
            if (compte[0].equals("Public")) { // Ignore le compte public
                continue;
            }
            Label identifiantLabel = new Label(compte[0]);
            ComboBox<String> typeCompteComboBox = createTypeCompteComboBox(compte[1]);

            typeCompteComboBox.setOnAction(e -> { // Lorsqu'on change le type de compte, on met à jour la base de données
                DatabaseAccess.updateAccount(compte[0], typeCompteComboBox.getValue());
                this.setMessage("Le type de compte de " + compte[0] + " a été mis à jour.");
            });
            
            gridPane.addRow(rowIndex, identifiantLabel, typeCompteComboBox);
            rowIndex++;
        }
        
        this.setCenter(gridPane);
    }
    
    private ComboBox<String> createTypeCompteComboBox(String selectedValue) {
        ObservableList<String> options = FXCollections.observableArrayList(
                "Utilisateur",
                "Elu",
                "Administrateur");

        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setValue(selectedValue);

        return comboBox;
    }
}
