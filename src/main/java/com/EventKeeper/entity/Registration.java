package com.EventKeeper.entity;

public class Registration {
    private final int id;
    private Event event;
    private Participant participant;

    public Registration(int id, Event event, Participant participant) {
        this.id = id;
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
