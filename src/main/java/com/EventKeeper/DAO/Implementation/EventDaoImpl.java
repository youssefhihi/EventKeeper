package com.EventKeeper.DAO.Implementation;
import com.EventKeeper.DAO.EventDAO;
import com.EventKeeper.entity.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventDaoImpl implements EventDAO {

    private Map<Integer, Event> events = new HashMap<>();
    private static EventDaoImpl instance; 

    public static EventDaoImpl getInstance() {
        if (instance == null) {
            instance = new EventDaoImpl();
        }
        return instance;
    }
    private boolean isIdUnique(int id){
        try{
           return  events.values().stream()
                    .noneMatch(event -> event.getId() == id);
        }catch(Exception e){
            return false;
        }
    }
    
    @Override
    public void addEvent(Event event) {
        try {
            if (!isIdUnique(event.getId())) {
                System.out.println("Invalid event: ID already exists.");
                return;
            }else{
            if (!events.containsKey(event.getId())) {
                events.put(event.getId(), event);
            }else{
                System.out.println("Invalid event: ID already exists.");
            }
            }
        }catch (Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateEvent(Event event) {
        try {
            if ( events.containsKey(event.getId()) ) {
                events.put(event.getId(), event);
                return true;
            }
        }catch ( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteEvent(int id) {
        try {
            if(events.containsKey(id)) {
                events.remove(id);
                return true;
            }
        }catch ( Exception e ) {
            e.printStackTrace();
        }
        return false;
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
        try {
            return new ArrayList<>(events.values());
        }catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Event> getEventsByType(String type) {
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
