package com.EventKeeper.DAO;
import com.EventKeeper.entity.Participant;
import java.util.List;

public interface ParticipantDAO {
    public boolean addParticipant(Participant participant);
    public boolean updateParticipant(Participant participant);
    public void deleteParticipant(int participantId);
    public List<Participant> getParticipants();
    public Participant login(String username , String password);
}
