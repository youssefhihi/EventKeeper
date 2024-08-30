package com.EventKeeper.DAO;

import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;

public interface RegistrationDAO {

    public void register(Participant participant, Event event);

    public void unregister(Participant participant, Event event);

    public void registration(Event event);

}
