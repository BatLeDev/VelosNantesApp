package controllers;

import java.sql.SQLException;

import database.DatabaseAccess;
import javafx.event.ActionEvent;
import views.ModificationView;


public class ModificationController  {
    private Rooter rooter;
    private ModificationView modificationView;

    public ModificationController(Rooter rooter) {
        this.rooter = rooter;
        this.modificationView = (ModificationView) rooter.getView("Modification");

        this.setup();
    }

    private void setup() {
        this.modificationView.getExecuter().setOnAction(this::executer);
    }

    private void executer(ActionEvent event) {
        String requete = this.modificationView.getRequete().getText();
        try {
            this.modificationView.getReponse().setText(DatabaseAccess.requeteSQL(requete));
        } catch (SQLException e) {
            this.modificationView.getReponse().setText(e.getMessage());
        }
    }
}
