package com.EventKeeper.DAO;

import java.util.List;

import com.EventKeeper.entity.Admin;
import com.EventKeeper.entity.Registration;


public interface AdminDAO {

    public Admin login(String username, String password);

    public List <Registration> getReportOfParticipant(String username);
    public List <Registration> getReportOfEvent(int eventID);
    
} 