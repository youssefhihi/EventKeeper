package com.EventKeeper.entity;

public class Registration {
    private static int uniqueID = 0;
    private final int id;
    private Event event;
    private Participant participant;

    public Registration(Event event, Participant participant) {
        this.id = uniqueID++;
        this.event = event;
        this.participant = participant;
    }

    public int getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
