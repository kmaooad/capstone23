package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.ControllerTest;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@QuarkusTest
public class CreateChatControllerTest extends ControllerTest<Chat> {
  CreateChatControllerTest() {
    super("/chats/create");
  }

  @Test
  @DisplayName("Should succeed if all fields valid")
  public void shouldSucceedIfPayloadValid() {
    assertRequestSucceeds(
        ChatMocks.validChat()
    );
  }

  @Test
  @DisplayName("Should deny request if chat access type is not valid")
  public void shouldDenyRequestIfInvalidAccessType() {
    assertRequestFails(
        ChatMocks.chatWithNoType()
    );
  }

  @Test
  @DisplayName("Should deny request if chat name is exhaustive")
  public void shouldDenyRequestIfExhaustiveName() {
    assertRequestFails(
        ChatMocks.chatWithExhaustiveName()
    );
  }

  @Test
  @DisplayName("Should deny request if chat name is empty")
  public void shouldDenyRequestIfEmptyName() {
    assertRequestFails(
        ChatMocks.chatWithNoName()
    );
  }
}
