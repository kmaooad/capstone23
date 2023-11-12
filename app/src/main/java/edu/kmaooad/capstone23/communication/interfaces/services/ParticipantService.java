package edu.kmaooad.capstone23.communication.interfaces.services;

import edu.kmaooad.capstone23.communication.dal.entities.Participant;

public interface ParticipantService {
  boolean validateChatAndUser(String chatId, String userId);

  Participant insert(Participant participant);
}
