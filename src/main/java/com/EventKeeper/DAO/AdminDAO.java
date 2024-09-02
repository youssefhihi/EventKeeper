package com.EventKeeper.DAO;

import java.util.List;

import com.EventKeeper.entity.Admin;
import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;


public interface AdminDAO {

    public Admin login(String username, String password);

    public List <Event> getReportOfParticipant(String username);
    public List <Participant> getReportOfEvent(int eventID);
    
} 