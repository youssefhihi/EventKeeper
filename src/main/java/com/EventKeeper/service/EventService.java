package com.EventKeeper.service;

import java.util.List;

import com.EventKeeper.entity.Event;

public interface EventService {
    public void addEvent(Event event);
    public boolean updateEvent(int id,Event event);
    public boolean deleteEvent(int id);
    public Event getEvent(int id);
    public List<Event> getEvents();
    public List<Event> getEventsByType(String type);
    public List<Event> getEventsByDate(String date);
    public List<Event> getEventsByLocalisation(String localisation);
}
