package com.EventKeeper.service;

import java.util.List;

import com.EventKeeper.entity.Participant;

public interface ParticipantService {
    
     boolean addParticipant(Participant participant);
     boolean updateParticipant(int id, Participant participant);
     void deleteParticipant(int participantId);
     List<Participant> getParticipants();
     Participant login(String username , String password);

} 
