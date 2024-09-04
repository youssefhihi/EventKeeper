package com.EventKeeper.service;

import java.util.List;

import com.EventKeeper.entity.Participant;

public interface ParticipantService {
    
    public boolean addParticipant(Participant participant);
    public boolean updateParticipant(int id, Participant participant);
    public void deleteParticipant(int participantId);
    public List<Participant> getParticipants();
    public Participant login(String username , String password);

} 
