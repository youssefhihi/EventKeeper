package com.EventKeeper.service;

import java.util.List;

import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.entity.Registration;

public interface RegistrationService {

     boolean register(Event event, Participant participant );

     void unregister(int eventID, int participantID);

     List <Event> registration(int participantID);

     List <Registration> getReportOfParticipant(String username);

     List <Registration> getReportOfEvent(int eventID);

     List <Participant> getListOfParticpant(int eventID);
    
}
