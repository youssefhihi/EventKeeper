package com.EventKeeper.DAO.Implementation;
import com.EventKeeper.entity.Admin;
import com.EventKeeper.enums.role;
import com.EventKeeper.DAO.AdminDAO;

public class AdminDaoImpl implements AdminDAO {

    private Admin admin = new Admin(1, "admin", "1234", role.Admin);
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
    
}
