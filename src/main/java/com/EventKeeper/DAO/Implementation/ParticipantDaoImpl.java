package com.EventKeeper.DAO.Implementation;

import com.EventKeeper.DAO.ParticipantDAO;
import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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

    public Participant login(String username, String password) {
        try {
            Optional<Participant> participantExist = participants.values().stream()
                    .filter(p -> p.getUsername().equals(username) && p.getPassword().equals(password))
                    .findFirst();
    
            return participantExist.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }
    

    @Override
    public boolean addParticipant(Participant participant) {
        try{
            if (!isUsernameUnique(participant.getUsername())) {
                System.out.println("Invalid participant: Username already exists.");
                return false;
            }else{
                participants.put(participant.getId(), participant);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateParticipant(Participant participant) {
        try{
            if (!isUsernameUnique(participant.getUsername())) {
                System.out.println("Invalid participant: Username already exists.");
                return false;
            }else {
                if (participants.containsKey(participant.getId())) {
                    participants.put(participant.getId(), participant);
                    return true;
                }
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteParticipant(int participantId) {
        try{
            participants.remove(participantId);
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
