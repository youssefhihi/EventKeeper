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
    /**
     * Logs in an admin user with the given username and password.
     *
     * @param  username  the username of the admin user
     * @param  password  the password of the admin user
     * @return           the admin user if the login is successful, null otherwise
     */
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

    /**
     * Retrieves a list of registrations for a specific participant.
     *
     * @param  username  the username of the participant
     * @return          a list of registrations for the specified participant
     */
    @Override
    public List<Registration> getReportOfParticipant(String username) {
       return registrationDAO.getReportOfParticipant(username);
    }

    /**
     * Retrieves a list of registrations for a specific event.
     *
     * @param eventID    the ID of the event
     * @return          a list of registrations for the specified event
     */
    @Override
    public List <Registration>  getReportOfEvent(int eventID) {
         return registrationDAO.getReportOfEvent(eventID);
    }
    
}
