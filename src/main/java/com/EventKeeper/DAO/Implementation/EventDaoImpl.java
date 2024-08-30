package com.EventKeeper.DAO.Implementation;
import com.EventKeeper.DAO.EventDAO;
import com.EventKeeper.entity.Event;
import com.EventKeeper.enums.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventDaoImpl implements EventDAO {

    private Map<Integer, Event> events = new HashMap<>();

    @Override
    public void addEvent(Event event) {
        try {
            events.put(event.getId(), event);
        }catch (Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEvent(Event event) {
        try {
            events.replace(event.getId(), event);
        }catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEvent(int id) {
        try {
            events.remove(id);
        }catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public Event getEvent(int id) {
        try{
            return events.get(id);
        }catch ( Exception e ){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Event> getEvents() {
        return new ArrayList<>(events.values());
    }

    @Override
    public List<Event> getEventsByType(type type) {
        try {
            return getEvents().stream().filter(e -> e.getType().equals(type)).collect(Collectors.toList());
        }catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Event> getEventsByDate(String date) {
        try{
            return getEvents().stream().filter(e -> e.getDate().equals(date)).collect(Collectors.toList());
        }catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Event> getEventsByLocalisation(String localisation) {
        try {
            return getEvents().stream().filter(e -> e.getLocation().equals(localisation)).collect(Collectors.toList());
        }catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }
}
