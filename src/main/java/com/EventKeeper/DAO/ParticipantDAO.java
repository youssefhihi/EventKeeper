package com.EventKeeper.DAO;
import com.EventKeeper.entity.Participant;
import java.util.List;

public interface ParticipantDAO {
    public void addParticipant(Participant participant);
    public void updateParticipant(Participant participant);
    public void deleteParticipant(Participant participant);
    public List<Participant> getParticipants();
}
