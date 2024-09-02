package com.EventKeeper.entity;

import com.EventKeeper.enums.role;

public class Participant extends User {
    private static int uniqueID = 0;
    private int participantID;

    public Participant(String username, String password, role role) {
        super( username, password, role);
        this.participantID = uniqueID++;
    }

    public int getParticipantID() {
        return participantID;
    }
}
