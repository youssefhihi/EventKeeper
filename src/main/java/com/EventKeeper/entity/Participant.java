package com.EventKeeper.entity;

import com.EventKeeper.enums.role;

public class Participant extends User {
  

    public Participant(int id, String username, String password, role role) {
        super(id, username, password, role);
    }
}
