package controllers;

import views.ExporterView;

public class ExporterController {
    private Rooter rooter;
    private ExporterView exporterView;

    public ExporterController(Rooter rooter) {
        this.rooter = rooter;
        this.exporterView = (ExporterView) rooter.getView("Exporter");

        this.setup();
    }

    public void setup() {
        exporterView.getHideButton().setOnAction(event -> {
            rooter.changePage(false, "Exporter");
        });
    }
}
