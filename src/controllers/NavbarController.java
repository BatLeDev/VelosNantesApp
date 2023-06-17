package controllers;

import views.NavbarView;

public class NavbarController {
    private Rooter rooter;
    private NavbarView navbarView;

    public NavbarController(Rooter rooter) {
        this.rooter = rooter;
        this.navbarView = (NavbarView) rooter.getView("Navbar");

        this.setup();
    }

    public void setup() {
        navbarView.getExporterButton().setOnAction(event -> {
            rooter.changePage(true, "Exporter");
        });

        navbarView.getCarteButton().setOnAction(event -> {
            rooter.changePage(true, "Carte");
        });

        navbarView.getGraphiqueButton().setOnAction(event -> {
            rooter.changePage(true, "Graphique");
        });

        navbarView.getModificationButton().setOnAction(event -> {
            rooter.changePage(true, "Modification");
        });
    }

}
