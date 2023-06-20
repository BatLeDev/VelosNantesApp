package controllers;

import utilities.Rooter;

/**
 * This class is the controller of the navbar.
 * <p> It's used to change the page displayed when a button is clicked.
 */
public class NavbarController {
    private Rooter rooter;

    /**
     * Constructor of the NavbarController class.
     * <p> Initialise the navbar controller.
     * 
     * @param rooter The rooter of the application
     */
    public NavbarController(Rooter rooter) {
        this.rooter = rooter;
    }

    /**
     * This method is called by the navbar view when a button is clicked.
     * <p> It changes the page displayed, or do an action.
     * 
     * @param page The page to change / action to do
     */
    public void buttonClicked(String page) {
        if (page.equals("Login") || page.equals("Register")) {
            rooter.changePage(false, page);
        } else if (page.equals("Logout")) {
            rooter.changePermission(null);
            rooter.changePage(true, "Carte");
            System.out.println("Logout success");
        } else {
            rooter.changePage(true, page);
        }
    }
    
}
