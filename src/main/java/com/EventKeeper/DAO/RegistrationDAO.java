package com.EventKeeper.DAO;

import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.entity.Registration;

import java.util.List;

public interface RegistrationDAO {

    public boolean register(Event event, Participant participant );

    public void unregister(int eventID, int participantID);

    public List <Registration> registration(int participantID);

}
