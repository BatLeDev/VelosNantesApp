package controllers;

import views.CarteView;

public class CarteController {
    private Rooter rooter;
    private CarteView carteView;

    public CarteController(Rooter rooter) {
        this.rooter = rooter;
        this.carteView = (CarteView) rooter.getView("Carte");

        this.setup();
    }

    public void setup() {
        
    }

}
