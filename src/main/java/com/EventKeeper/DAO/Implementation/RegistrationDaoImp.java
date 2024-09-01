package com.EventKeeper.DAO.Implementation;

import com.EventKeeper.DAO.RegistrationDAO;
import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.entity.Registration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class RegistrationDaoImp implements RegistrationDAO {
    private Map<Integer, Registration> registrations = new HashMap<>();
    private int idCounter = 1;

    @Override
    public boolean register(Event event, Participant participant) {
        try{
            Registration registration = new Registration(idCounter++, event, participant);
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
                        .filter(r -> r.getParticipant().getId() == participantID && r.getEvent().getId() == eventID)
                        .findFirst();
            registrationRem.ifPresent(registration -> {
                registrations.remove(registration.getId());
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List <Registration> registration(int participantID){
        try{
             return registrations.values().stream().filter(r -> r.getParticipant().getId() == participantID).collect(Collectors.toList());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
