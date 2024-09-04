package com.EventKeeper.DAO;
import com.EventKeeper.entity.Event;

import java.util.List;

public interface EventDAO {
     void addEvent(Event event);
     boolean updateEvent(int id,Event event);
     boolean deleteEvent(int id);
     Event getEvent(int id);
     List<Event> getEvents();
     List<Event> getEventsByType(String type);
     List<Event> getEventsByDate(String date);
     List<Event> getEventsByLocalisation(String localisation);
}
