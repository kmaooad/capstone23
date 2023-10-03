package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.dal.entities.Participant;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.communication.mocks.ParticipantMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateParticipantHandlerTest extends HandlerTest<Participant, CreateParticipant, ParticipantCreated> {
  @Inject
  CreateChatHandler createChatHandler;

  @BeforeEach
  public void initCommand() {
    command = new CreateParticipant();
  }

  /*
    @Test
    @DisplayName("Should succeed if command is valid")
    // TODO: requires user creation flow, coming in next PRs
  */

  @Test
  @DisplayName("Should succeed if command is valid")
  public void shouldDenyCommandIfNoChatOrUser() {
    Result<ParticipantCreated> result = handleCommandWithPayload(
        ParticipantMocks.invalidParticipant()
    );

    assertDenied(result);
  }

  @Override
  protected Result<ParticipantCreated> handleCommandWithPayload(Participant participant) {
    command.setChatId(participant.chatId.toHexString());
    command.setUserId(participant.userId.toHexString());

    return handler.handle(command);
  }
}
