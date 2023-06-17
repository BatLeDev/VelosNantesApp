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

    }


}
