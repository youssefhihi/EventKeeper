package com.EventKeeper.entity;
import com.EventKeeper.enums.role;

public class Admin extends User {
    private int uniqueID = 0;
    private int AdminID;

    public Admin(String username, String password, role role) {
        super(username, password, role);
        this.AdminID = uniqueID++;
    }

    public int getAdminID() {
        return AdminID;
    }
}
