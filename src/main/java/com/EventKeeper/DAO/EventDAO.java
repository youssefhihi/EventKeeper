package com.EventKeeper.DAO;
import com.EventKeeper.entity.Event;
import com.EventKeeper.enums.type;
import java.util.List;

public interface EventDAO {
    public void addEvent(Event event);
    public void updateEvent(Event event);
    public void deleteEvent(int id);
    public Event getEvent(int id);
    public List<Event> getEvents();
    public List<Event> getEventsByType(type type);
    public List<Event> getEventsByDate(String date);
    public List<Event> getEventsByLocalisation(String localisation);
}
