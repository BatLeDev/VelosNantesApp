package controllers;

import utilities.Rooter;
import views.CarteView;

public class CarteController {
    private Rooter rooter;
    private CarteView carteView;

    public CarteController(Rooter rooter, CarteView carteView) {
        this.rooter = rooter;
        this.carteView = carteView;
        this.setup();
    }

    public void setup() {
        
    }

}
