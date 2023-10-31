package edu.kmaooad.capstone23.communication.services;

import edu.kmaooad.capstone23.communication.dal.entities.Participant;
import edu.kmaooad.capstone23.communication.interfaces.repositories.ParticipantRepository;
import edu.kmaooad.capstone23.communication.interfaces.services.ChatService;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ParticipantService {
  @Inject
  ChatService chatService;

  @Inject
  UserService userService;

  @Inject
  ParticipantRepository participantRepository;

  public boolean validateChatAndUser(String chatId, String userId) {
    boolean isChatValid = chatService.isChatPresent(chatId);
    boolean isUserValid = userService.isUserPresent(userId);

    return isChatValid && isUserValid;
  }

  public Participant insert(Participant participant) {
    return this.participantRepository.insert(participant);
  }
}
