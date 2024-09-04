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

    /**
     * Retrieves the singleton instance of the EventDaoImpl class.
     *
     * @return  the singleton instance of the EventDaoImpl class
     */
    public static EventDaoImpl getInstance() {
        if (instance == null) {
            instance = new EventDaoImpl();
        }
        return instance;
    }
    
    /**
     * Adds a new event to the events collection.
     *
     * @param event  the event to be added
     */
    @Override
    public void addEvent(Event event) {
        try {     
           
            events.put(event.getId(), event);

        }catch (Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing event in the events collection.
     *
     * @param id      the id of the event to be updated
     * @param event   the updated event
     * @return        true if the event is updated successfully, false otherwise
     */
    @Override
    public boolean updateEvent(int id,Event event) {
        try {
            if ( events.containsKey(id) ) {
                events.put(id, event);
                return true;
            }
        }catch ( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes an event from the events collection based on its id.
     *
     * @param id      the id of the event to be deleted
     * @return        true if the event is deleted successfully, false otherwise
     */
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

    /**
     * Retrieves an event from the events collection based on its id.
     *
     * @param  id      the id of the event to be retrieved
     * @return          the event with the specified id, or null if not found
     */
    @Override
    public Event getEvent(int id) {
        try{
            return events.get(id);
        }catch ( Exception e ){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all events from the events collection.
     *
     * @return         	a list of all events in the collection, or null if an error occurs
     */
    @Override
    public List<Event> getEvents() {
        try {
            return new ArrayList<>(events.values());
        }catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all events from the events collection that match the specified type.
     *
     * @param  type      the type of events to be retrieved
     * @return         	a list of events with the specified type, or null if an error occurs
     */
    @Override
    public List<Event> getEventsByType(String type) {
        try {
            return getEvents().stream().filter(e -> e.getType().equals(type)).collect(Collectors.toList());
        }catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of events that match the specified date.
     *
     * @param  date      the date of events to be retrieved
     * @return         	a list of events with the specified date, or null if an error occurs
     */
    @Override
    public List<Event> getEventsByDate(String date) {
        try{
            return getEvents().stream().filter(e -> e.getDate().equals(date)).collect(Collectors.toList());
        }catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of events that match the specified localisation.
     *
     * @param  localisation  the localisation of events to be retrieved
     * @return               a list of events with the specified localisation, or null if an error occurs
     */
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
