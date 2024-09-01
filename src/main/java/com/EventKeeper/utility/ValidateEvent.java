package com.EventKeeper.utility;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateEvent {

    public  boolean validateTitle(String title){
        return title != null && !title.trim().isEmpty() && title.length() >= 3;
    }

    public  boolean validateDescription(String description) {
        return description != null && !description.trim().isEmpty() && description.length() >= 10 && description.length() <= 500;
    }

    public  boolean validateLocation(String location) {
        return location != null && !location.trim().isEmpty() && location.length() >= 3;
    }

    public  boolean isValidType( String eventType) {
        return eventType != null;
    }

    public  boolean validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate dateFormated = LocalDate.parse(date, formatter);
            return !dateFormated.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }


}
