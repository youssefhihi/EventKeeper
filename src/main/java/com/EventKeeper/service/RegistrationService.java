package com.EventKeeper.service;

import java.util.List;

import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.entity.Registration;

public interface RegistrationService {

    public boolean register(Event event, Participant participant );

    public void unregister(int eventID, int participantID);

    public List <Event> registration(int participantID);

    public List <Registration> getReportOfParticipant(String username);

    public List <Registration> getReportOfEvent(int eventID);
    
}
