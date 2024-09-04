package com.EventKeeper.service;

import java.util.List;

import com.EventKeeper.entity.Event;

public interface EventService {
     void addEvent(Event event);
     boolean updateEvent(int id,Event event);
     boolean deleteEvent(int id);
     Event getEvent(int id);
     List<Event> getEvents();
     List<Event> getEventsByType(String type);
     List<Event> getEventsByDate(String date);
     List<Event> getEventsByLocalisation(String localisation);
}
