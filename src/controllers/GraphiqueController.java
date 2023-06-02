package controllers;

import views.ExporterView;
import views.GraphiqueView;

import database.DatabaseAccess;

public class GraphiqueController {
    private Rooter rooter;
    private GraphiqueView graphiqueView;

    public GraphiqueController(Rooter rooter) {
        this.rooter = rooter;
        this.graphiqueView = (GraphiqueView) rooter.getView("Graphique");

        this.setup();
    }

    public void setup() {
        graphiqueView.getExempleButton().setOnAction(event -> {
            DatabaseAccess.exemple();
        });
    }
}
