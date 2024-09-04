package com.EventKeeper.service;

import java.util.List;

import com.EventKeeper.entity.Admin;
import com.EventKeeper.entity.Registration;

public interface AdminService {

    public Admin login(String username, String password);
    public List <Registration> getReportOfParticipant(String username);
    public List <Registration> getReportOfEvent(int eventID);
}
