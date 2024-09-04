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

    /**
     * Retrieves the event associated with this registration.
     *
     * @return the event associated with this registration
     */
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Retrieves the participant associated with this registration.
     *
     * @return the participant object
     */
    public Participant getParticipant() {
        return participant;
    }

    /**
     * Sets the participant associated with this registration.
     *
     * @param participant the participant object to be associated with this registration
     */
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
