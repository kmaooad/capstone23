package edu.kmaooad.capstone23.communication.services;

import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ParticipantService {
  @Inject
  ChatService chatService;

  @Inject
  UserService userService;

  public boolean validateChatAndUser(String chatId, String userId) {
    boolean isChatValid = chatService.isChatPresent(chatId);
    boolean isUserValid = userService.isUserPresent(userId);

    return isChatValid && isUserValid;
  }
}
