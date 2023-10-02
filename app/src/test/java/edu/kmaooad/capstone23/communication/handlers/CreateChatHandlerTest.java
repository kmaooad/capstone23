package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ChatCreated;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@QuarkusTest
public class CreateChatHandlerTest extends HandlerTest<Chat, CreateChat, ChatCreated> {
  private CreateChat command;

  @Inject
  CreateChatHandler handler;

  @BeforeEach
  public void initCommand() {
    command = new CreateChat();
  }

  @Test
  @DisplayName("Should succeed if command is valid")
  public void shouldSucceedIfCommandValid() {
    Result<ChatCreated> result = handleCommandWithPayload(ChatMocks.validChat());

    assertHandled(result);
  }

  @Test
  @DisplayName("Should deny command if chat access type is invalid")
  public void shouldDenyCommandIfInvalidAccessType() {
    Result<ChatCreated> result = handleCommandWithPayload(ChatMocks.chatWithNoType());

    assertDenied(result);
  }

  @Test
  @DisplayName("Should deny command if chat name is exhaustive")
  public void shouldDenyCommandIfExhaustiveName() {
    Result<ChatCreated> result = handleCommandWithPayload(ChatMocks.chatWithExhaustiveName());

    assertDenied(result);
  }

  @Test
  @DisplayName("Should deny command if chat name is empty")
  public void shouldDenyCommandIfEmptyName() {
    Result<ChatCreated> result = handleCommandWithPayload(ChatMocks.chatWithNoName());

    assertDenied(result);
  }

  @Override
  protected Result<ChatCreated> handleCommandWithPayload(Chat chat) {
    command.setName(chat.name);
    command.setAccessType(chat.accessType);

    return handler.handle(command);
  }
}
