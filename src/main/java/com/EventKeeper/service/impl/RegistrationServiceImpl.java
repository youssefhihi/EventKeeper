package com.EventKeeper.service.impl;

import java.util.List;

import com.EventKeeper.DAO.RegistrationDAO;
import com.EventKeeper.DAO.Implementation.RegistrationDaoImp;
import com.EventKeeper.entity.Event;
import com.EventKeeper.entity.Participant;
import com.EventKeeper.entity.Registration;
import com.EventKeeper.service.RegistrationService;

public class RegistrationServiceImpl  implements RegistrationService{
  private final RegistrationDAO registrationDAO;

  public RegistrationServiceImpl() {
    this.registrationDAO = RegistrationDaoImp.getInstance();

  }

  @Override
  public boolean register(Event event, Participant participant) {
    return registrationDAO.register(event, participant);
  }

  @Override
  public void unregister(int eventID, int participantID) {
    registrationDAO.unregister(eventID, participantID);
  }

  @Override
  public List <Event> registration(int participantID) {
    return registrationDAO.registration(participantID);
  }

  @Override
  public List <Registration> getReportOfParticipant(String username) {
    return registrationDAO.getReportOfParticipant(username);
  }

  @Override
  public List <Registration> getReportOfEvent(int eventID) {
    return registrationDAO.getReportOfEvent(eventID);
  }

    
}
