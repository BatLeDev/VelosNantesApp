package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerConverter extends javafx.util.StringConverter<LocalDate> {
    private final DateTimeFormatter formatter;

    public DatePickerConverter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String toString(LocalDate date) {
        if (date != null) {
            return formatter.format(date);
        }
        return "";
    }

    @Override
    public LocalDate fromString(String string) {
        if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, formatter);
        }
        return null;
    }


}