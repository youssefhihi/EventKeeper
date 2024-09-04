package com.EventKeeper.DAO;
import com.EventKeeper.entity.Participant;
import java.util.List;

public interface ParticipantDAO {
     boolean addParticipant(Participant participant);
     boolean updateParticipant(int id, Participant participant);
     void deleteParticipant(int participantId);
     List<Participant> getParticipants();
     Participant login(String username , String password);
}
