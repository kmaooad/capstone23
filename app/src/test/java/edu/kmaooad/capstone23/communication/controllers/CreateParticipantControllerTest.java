package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.ControllerTest;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.dal.entities.Participant;
import edu.kmaooad.capstone23.communication.interfaces.ChatRepository;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import edu.kmaooad.capstone23.communication.mocks.ParticipantMocks;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateParticipantControllerTest extends ControllerTest<Participant> {
  @Inject
  ChatRepository chatRepository;

  @Inject
  UserRepository userRepository;

  CreateParticipantControllerTest() {
    super("/participants/create");
  }

  @Test
  @DisplayName("Should succeed if chat and user exist")
  public void shouldSucceedIfChatAndUserExist() {
    Chat chat = chatRepository.insert(ChatMocks.validChat());
    User user = userRepository.insert(UserMocks.validUser());

    assertRequestSucceeds(
        ParticipantMocks.makeParticipant(chat.id, user.id)
    );
  }

  @Test
  @DisplayName("Should deny request if there is no chat or user")
  public void shouldDenyRequestIfNoChatOrUser() {
    assertRequestFails(
        ParticipantMocks.invalidParticipant()
    );
  }
}
