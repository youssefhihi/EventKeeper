package com.EventKeeper.entity;

import com.EventKeeper.enums.role;

public class Participant extends User {
    private int id;

    public Participant(int id, String username, String password, role role) {
        super(id, username, password, role);
    }
}
