package com.EventKeeper.DAO;

import com.EventKeeper.entity.Admin;

public interface AdminDAO {

    public Admin login(String username, String password);
    
} 