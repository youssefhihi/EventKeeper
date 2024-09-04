package com.EventKeeper.DAO.Implementation;

import com.EventKeeper.DAO.RegistrationDAO;
import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.entity.Registration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RegistrationDaoImp implements RegistrationDAO {
    private Map<Integer, Registration> registrations = new HashMap<>();
    private static RegistrationDaoImp instance;

    /**
     * Retrieves the singleton instance of the RegistrationDaoImp class.
     *
     * @return          the singleton instance of RegistrationDaoImp
     */
    public static RegistrationDaoImp getInstance() {
        if (instance == null) {
            instance = new RegistrationDaoImp();
        }
        return instance;
    }

    /**
     * Registers a participant for an event.
     *
     * @param event      the event to register for
     * @param participant the participant to register
     * @return true if the registration was successful, false if the participant is already registered for the event
     */
    @Override
    public boolean register(Event event, Participant participant) {
        try{
            Registration registration = new Registration( event, participant);
           boolean matched = registrations.values().stream()
                    .anyMatch(reg -> reg.getEvent().equals(event) && reg.getParticipant().equals(participant));
            if(matched){
                return false;
            }else{
                registrations.put(registration.getId(), registration);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Unregisters a participant from an event.
     *
     * @param eventID    the ID of the event to unregister from
     * @param participantID the ID of the participant to unregister
     */
    @Override
    public void unregister(int eventID, int participantID){
        try{
           Optional<Registration> registrationRem = registrations.values().stream()
                        .filter(r -> r.getParticipant().getParticipantID() == participantID && r.getEvent().getId() == eventID)
                        .findFirst();
            registrationRem.ifPresent(registration -> {
                registrations.remove(registration.getId());
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of events that a participant is registered for.
     *
     * @param  participantID  the ID of the participant
     * @return                 a list of events that the participant is registered for, or null if an error occurs
     */
    @Override
    public List <Event> registration(int participantID){
        try{
            return registrations.values().stream()
                            .filter(r -> r.getParticipant().getParticipantID() == participantID)
                            .map(r -> r.getEvent())
                            .collect(Collectors.toList());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of registrations for a participant with the specified username.
     *
     * @param  username  the username of the participant
     * @return          a list of registrations for the participant, or null if an error occurs
     */
    @Override
    public List <Registration> getReportOfParticipant(String username){
        try{
            return registrations.values().stream()
                            .filter(r -> r.getParticipant().getUsername().equals(username))
                            .collect(Collectors.toList());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of registrations for an event with the specified ID.
     *
     * @param  eventID  the ID of the event
     * @return          a list of registrations for the event, or null if an error occurs
     */
    @Override
    public List <Registration> getReportOfEvent(int eventID){
        try{
            return registrations.values().stream()
                            .filter(r -> r.getEvent().getId() == eventID)
                            .collect(Collectors.toList());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of participants for an event with the specified ID.
     *
     * @param  eventID  the ID of the event
     * @return          a list of participants for the event, or null if an error occurs
     */
    @Override
    public List <Participant> getListOfParticpant(int eventID){
        try{
            return registrations.values().stream()
            .filter(r -> r.getEvent().getId() == eventID)
            .map(r -> r.getParticipant())
            .collect(Collectors.toList());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
