package com.EventKeeper.DAO.Implementation;

import com.EventKeeper.entity.Admin;
import com.EventKeeper.entity.Registration;
import com.EventKeeper.enums.role;

import java.util.List;

import com.EventKeeper.DAO.AdminDAO;
import com.EventKeeper.DAO.RegistrationDAO;


public class AdminDaoImpl implements AdminDAO {

    private Admin admin = new Admin("admin", "1234", role.Admin);    
    private static final RegistrationDAO registrationDAO = RegistrationDaoImp.getInstance();
    @Override
    public Admin login(String username, String password) {
        try{

            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }else{
                return null;
            }
           
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Registration> getReportOfParticipant(String username) {
       return registrationDAO.getReportOfParticipant(username);
    }

    @Override
    public List <Registration>  getReportOfEvent(int eventID) {
         return registrationDAO.getReportOfEvent(eventID);
    }
    
}
