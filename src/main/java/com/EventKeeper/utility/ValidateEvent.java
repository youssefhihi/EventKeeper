package com.EventKeeper.utility;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateEvent {

    /**
     * Validates the title of an event.
     *
     * @param  title    the title to be validated
     * @return          true if the title is not null, not empty, and has a length of at least 3 characters
     */
    public  boolean validateTitle(String title){
        return title != null && !title.trim().isEmpty() && title.length() >= 3;
    }

    /**
     * Validates the description of an event.
     *
     * @param  description    the description to be validated
     * @return                 true if the description is not null, not empty, and has a length between 10 and 500 characters
     */
    public  boolean validateDescription(String description) {
        return description != null && !description.trim().isEmpty() && description.length() >= 10 && description.length() <= 500;
    }

    /**
     * Validates the location of an event.
     *
     * @param  location    the location to be validated
     * @return          true if the location is not null, not empty, and has a length of at least 3 characters
     */
    public  boolean validateLocation(String location) {
        return location != null && !location.trim().isEmpty() && location.length() >= 3;
    }

    /**
     * Checks if the event type is valid.
     *
     * @param  eventType    the event type to be validated
     * @return          true if the event type is not null, false otherwise
     */
    public  boolean isValidType( String eventType) {
        return eventType != null;
    }

    /**
     * Validates the date of an event.
     *
     * @param  date    the date to be validated in the format "yyyy-MM-dd"
     * @return          true if the date is not before the current date, false otherwise
     */
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
