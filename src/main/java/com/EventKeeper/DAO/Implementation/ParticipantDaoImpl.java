package com.EventKeeper.DAO.Implementation;

import com.EventKeeper.DAO.ParticipantDAO;
import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParticipantDaoImpl implements ParticipantDAO {

    private Map<Integer, Participant> participants = new HashMap<>();

    private boolean isUsernameUnique(String username){
        try{
           return  participants.values().stream()
                    .noneMatch(participant -> participant.getUsername().equals(username));
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void addParticipant(Participant participant) {
        try{
            if (!isUsernameUnique(participant.getUsername())) {
                System.out.println("Invalid participant: Username already exists.");
                return;
            }
            participants.put(participant.getId(), participant);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateParticipant(Participant participant) {
        try{
            if (!isUsernameUnique(participant.getUsername())) {
                System.out.println("Invalid participant: Username already exists.");
                return;
            }
            participants.replace(participant.getId(), participant);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteParticipant(Participant participant) {
        try{
            participants.remove(participant.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Participant> getParticipants() {
        try{
            return new ArrayList<>(participants.values());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
