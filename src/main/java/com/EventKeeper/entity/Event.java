package com.EventKeeper.entity;
import com.EventKeeper.enums.type;


public class Event {
    private int id;
    private String title;
    private String description;
    private String location;
    private String date;
    private type type;

    public Event(int id, String title, String description, String location, String date, type type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public type getType() {
        return type;
    }

    public void setType(type type) {
        this.type = type;
    }
}
