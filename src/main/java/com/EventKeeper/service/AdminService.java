package com.EventKeeper.service;

import java.util.List;

import com.EventKeeper.entity.Admin;
import com.EventKeeper.entity.Registration;

public interface AdminService {

     Admin login(String username, String password);
     List <Registration> getReportOfParticipant(String username);
     List <Registration> getReportOfEvent(int eventID);
}
