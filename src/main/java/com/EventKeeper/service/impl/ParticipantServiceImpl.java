package com.EventKeeper.service.impl;

import java.util.List;

import com.EventKeeper.DAO.ParticipantDAO;
import com.EventKeeper.DAO.Implementation.ParticipantDaoImpl;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.service.ParticipantService;

public class ParticipantServiceImpl implements ParticipantService {
     private final ParticipantDAO participantDAO;

    public ParticipantServiceImpl() {
        this.participantDAO = ParticipantDaoImpl.getInstance();
    }

    @Override
    public boolean addParticipant(Participant participant) {
        return participantDAO.addParticipant(participant);
    }

    @Override
    public boolean updateParticipant(int id, Participant participant) {
        return participantDAO.updateParticipant(id, participant);
    }

    @Override
    public void deleteParticipant(int participantId) {
        participantDAO.deleteParticipant(participantId);
    }

    @Override   
    public List<Participant> getParticipants() {
        return participantDAO.getParticipants();
    }

    @Override
    public Participant login(String username, String password) {
        return participantDAO.login(username, password);
    }
}
