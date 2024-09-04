package com.EventKeeper.service.impl;

import java.util.List;

import com.EventKeeper.DAO.EventDAO;
import com.EventKeeper.DAO.Implementation.EventDaoImpl;
import com.EventKeeper.entity.Event;
import com.EventKeeper.service.EventService;

public class EventServiceImpl implements EventService {
    private final EventDAO eventDAO;
    
    public EventServiceImpl() {
        this.eventDAO = EventDaoImpl.getInstance();
    }

    @Override
    public void addEvent(Event event) {
        eventDAO.addEvent(event);
    }

    @Override
    public boolean updateEvent(int id, Event event) {
        return eventDAO.updateEvent(id, event);
    }

    @Override
    public boolean deleteEvent(int id) {
        return eventDAO.deleteEvent(id);
    }
    
    @Override
    public Event getEvent(int id) {
        return eventDAO.getEvent(id);
    }
    @Override
    public List<Event> getEvents() {
        return eventDAO.getEvents();
    }

    public List<Event> getEventsByType(String type) {
        return eventDAO.getEventsByType(type);
    }

    public List<Event> getEventsByDate(String date) {
        return eventDAO.getEventsByDate(date);
    }

    public List<Event> getEventsByLocalisation(String localisation) {
        return eventDAO.getEventsByLocalisation(localisation);
    }

    
}
