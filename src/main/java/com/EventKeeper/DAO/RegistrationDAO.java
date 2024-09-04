package com.EventKeeper.DAO;

import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.entity.Registration;

import java.util.List;

public interface RegistrationDAO {

     boolean register(Event event, Participant participant );

     void unregister(int eventID, int participantID);

     List <Event> registration(int participantID);

     List <Registration> getReportOfParticipant(String username);

     List <Registration> getReportOfEvent(int eventID);
     
     List <Participant> getListOfParticpant(int eventID);
}
