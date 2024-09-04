package com.EventKeeper.service.impl;

import java.util.List;

import com.EventKeeper.DAO.AdminDAO;
import com.EventKeeper.DAO.Implementation.AdminDaoImpl;
import com.EventKeeper.entity.Admin;
import com.EventKeeper.entity.Registration;
import com.EventKeeper.service.AdminService;

public class AdminServiceImpl  implements AdminService{
    private final AdminDAO adminDAO;

    public AdminServiceImpl() {
        this.adminDAO = new AdminDaoImpl();
    }

    @Override
    public Admin login(String username, String password) {
        return adminDAO.login(username, password);
    }

    @Override
    public List <Registration> getReportOfParticipant(String username) {
        return adminDAO.getReportOfParticipant(username);
    }

    @Override
    public List <Registration> getReportOfEvent(int eventID) {
        return adminDAO.getReportOfEvent(eventID);
    }
}
