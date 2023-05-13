package controllers;

// JavaFX imports
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

/**
 * Controller of the CalendarView
 * Handle the events of the view
 * Edit view elements
 * 
 */
public class CalendarController {


    // ------------------------------ FXML elements ------------------------------
    @FXML
    private Label dayLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private Label yearLabel;
    // ----------------------------------------------------------------------------

    private Calendar calendar;

    // ------------------------------ Initializer ------------------------------

    /**
     * Initialize the controller
     * Called automatically by JavaFX
     */
    public void initialize() {
        calendar = new Calendar(); // Initialize the calendar object with the current date
        initializeComboBox(); // Initialize the month combobox
        updateDateLabels(); // Update the date labels (in the view)

        // Add a listener to the month combobox
        monthComboBox.valueProperty().addListener((observable, oldValue, newValue) -> handleMonthChange(newValue));
    }

    /**
     * Initialize the month combobox
     * Add the months names to the combobox
     */
    private void initializeComboBox() {
        String[] monthsNames = calendar.getMonthsNames();
        monthComboBox.getItems().addAll(monthsNames);
    }

    // ------------------------------ Event handlers ------------------------------

    /**
     * Handle the key pressed event
     * Called when the user press a key
     * 
     * @param keyEvent the key event
     */
    @FXML
    private void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) { // Get the key code
            case LEFT: { // If the key is the left arrow
                calendar.previousDay();
                updateDateLabels();
                break;
            }
            case RIGHT: { // If the key is the right arrow
                calendar.nextDay();
                updateDateLabels();
                break;
            }
            default: { // If the key is not the left or right arrow
                break; // Do nothing
            }
        }
    }

    /**
     * Handle the month change
     * Called when the user select a month in the combobox
     * 
     * @param selectedMonth the selected month
     */
    private void handleMonthChange(String selectedMonth) {
        int monthIndex = monthComboBox.getItems().indexOf(selectedMonth); // Get the index of the selected month
        calendar.setMonth(monthIndex + 1); // [0, 11] -> [1, 12]
        updateDateLabels(); // Update the date labels (in the view)
    }

    // ------------------------------ Methods ------------------------------
    
    /**
     * Update the date labels (in the view)
     */
    private void updateDateLabels() {
        dayLabel.setText(calendar.getDayName());
        dateLabel.setText(String.valueOf(calendar.getDayNumber()));
        monthComboBox.getSelectionModel().select(calendar.getMonthName());
        yearLabel.setText(String.valueOf(calendar.getYear()));
    }
}
