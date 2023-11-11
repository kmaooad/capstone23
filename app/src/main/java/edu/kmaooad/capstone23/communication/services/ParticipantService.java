package edu.kmaooad.capstone23.communication.services;

import edu.kmaooad.capstone23.communication.dal.entities.Participant;
import edu.kmaooad.capstone23.communication.dal.repositories.ParticipantRepository;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

public interface ParticipantService {
  boolean validateChatAndUser(String chatId, String userId);

  Participant insert(Participant participant);
}
