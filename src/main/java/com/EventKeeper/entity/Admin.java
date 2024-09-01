package com.EventKeeper.entity;
import com.EventKeeper.enums.role;

public class Admin extends User {


    public Admin(int id, String username, String password, role role) {
        super(id, username, password, role);
    }
}
