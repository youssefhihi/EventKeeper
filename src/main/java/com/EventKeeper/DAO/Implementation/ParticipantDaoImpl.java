package com.EventKeeper.DAO.Implementation;

import com.EventKeeper.DAO.ParticipantDAO;
import com.EventKeeper.entity.Participant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class ParticipantDaoImpl implements ParticipantDAO {
    private static ParticipantDaoImpl instance;
    private Map<Integer, Participant> participants = new HashMap<>();


    /**
     * Returns the singleton instance of the ParticipantDaoImpl class.
     *
     * @return  the singleton instance of ParticipantDaoImpl
     */
    public static ParticipantDaoImpl getInstance() {
        if (instance == null) {
            instance = new ParticipantDaoImpl();
        }
        return instance;
    }
    /**
     * Checks whether a given username is unique among all participants.
     *
     * @param username the username to check for uniqueness
     * @return true if the username is unique, false otherwise
     */
    private boolean isUsernameUnique(String username){
        try{
           return  participants.values().stream()
                    .noneMatch(participant -> participant.getUsername().equals(username));
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Attempts to log in a participant with the given username and password.
     *
     * @param username  the username of the participant
     * @param password  the password of the participant
     * @return          the participant object if the login is successful, null otherwise
     */
    public Participant login(String username, String password) {
        try {

            Optional<Participant> participantExist = participants.values().stream()
                    .filter(p -> p.getUsername() != null && p.getPassword() != null) 
                    .filter(p -> p.getUsername().equals(username) && p.getPassword().equals(password))
                    .findFirst();
    
            return participantExist.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

    /**
     * Attempts to add a new participant to the system.
     *
     * @param participant  the participant object to be added
     * @return          true if the participant is added successfully, false otherwise
     */
    @Override
    public boolean addParticipant(Participant participant) {
        try{
            if (!isUsernameUnique(participant.getUsername())) {
                System.out.println("Invalid participant: Username already exists.");
                return false;
            }else{
                participants.put(participant.getParticipantID(), participant);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates a participant in the system with the given participant ID and new participant information.
     *
     * @param  id         the ID of the participant to update
     * @param  participant the new participant information to update with
     * @return             true if the participant was successfully updated, false otherwise
     */
    @Override
    public boolean updateParticipant(int id,Participant participant) {
        try{
            if (!isUsernameUnique(participant.getUsername())) {
                System.out.println("Invalid participant: Username already exists.");
                return false;
            }else {
                if (participants.containsKey(id)) {
                    participants.put(id, participant);
                    return true;
                }
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a participant from the system with the given participant ID.
     *
     * @param  participantId  the ID of the participant to delete
     */
    @Override
    public void deleteParticipant(int participantId) {
        try{
            participants.remove(participantId);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of all participants in the system.
     *
     * @return  a list of Participant objects representing all participants in the system
     */
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
