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

    public static RegistrationDaoImp getInstance() {
        if (instance == null) {
            instance = new RegistrationDaoImp();
        }
        return instance;
    }

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

}
