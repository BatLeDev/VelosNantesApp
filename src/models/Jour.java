package models;

public class Jour {

    private String date;
    private int jourDeSemaine;
    private int temp;
    private boolean isWeekend;
    private boolean estFerie;


    public Jour(String date, int jourDeSemaine, int temp) {
        this.date = date;
        this.jourDeSemaine = jourDeSemaine;
        this.temp = temp;
    }

    
}

