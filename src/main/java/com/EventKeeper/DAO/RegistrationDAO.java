package com.EventKeeper.DAO;

import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;

import java.util.List;

public interface RegistrationDAO {

    public boolean register(Event event, Participant participant );

    public void unregister(int eventID, int participantID);

    public List <Event> registration(int participantID);

    public List <Event> getReportOfParticipant(String username);

    public List <Participant> getReportOfEvent(int eventID);
}
