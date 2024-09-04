package com.EventKeeper.DAO;

import java.util.List;

import com.EventKeeper.entity.Admin;
import com.EventKeeper.entity.Registration;


public interface AdminDAO {

     Admin login(String username, String password);

     List <Registration> getReportOfParticipant(String username);
     List <Registration> getReportOfEvent(int eventID);
    
} 