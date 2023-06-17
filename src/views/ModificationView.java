package views;

import javax.swing.UIDefaults.LazyValue;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * This class represents the view of the Exporter page.
 * This page is a form to export data.
 */
public class ModificationView extends BorderPane {

    private TextArea requete;
    private Button executer;
    private Label reponse;

    public ModificationView() {
        
        this.requete = new TextArea("SELECT * FROM Compteur;");
        this.executer = new Button("Executer");
        this.reponse = new Label("Reponse");

        VBox tmp = new VBox();
        tmp.getChildren().addAll(this.requete, this.executer);
        this.setTop(tmp);
        this.setCenter(this.reponse);
    }

    public TextArea getRequete() {
        return this.requete;
    }

    public Button getExecuter() {
        return this.executer;
    }

    public Label getReponse() {
        return this.reponse;
    }

}
