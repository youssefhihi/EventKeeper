package com.EventKeeper.utility;
import com.EventKeeper.enums.type;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class validateEvent {

    public static boolean validateTitle(String title){
        return title != null && !title.trim().isEmpty() && title.length() >= 3;
    }

    public static boolean validateDescription(String description) {
        return description != null && !description.trim().isEmpty() && description.length() >= 10 && description.length() <= 500;
    }
    public static boolean validateLocation(String location) {
        return location != null && !location.trim().isEmpty() && location.length() >= 3;
    }

    public static boolean isValidType( type eventType) {
        return eventType != null;
    }

    public static boolean validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate dateFormated = LocalDate.parse(date, formatter);
            return !dateFormated.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
