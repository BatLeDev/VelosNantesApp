package controllers;

import utilities.Rooter;

public class NavbarController {
    private Rooter rooter;

    public NavbarController(Rooter rooter) {
        this.rooter = rooter;
    }

    public void buttonClicked(String page) {
        if (page.equals("Login") || page.equals("Register")) {
            rooter.changePage(false, page);
        } else {
            rooter.changePage(true, page);
        }
    }
    
}
