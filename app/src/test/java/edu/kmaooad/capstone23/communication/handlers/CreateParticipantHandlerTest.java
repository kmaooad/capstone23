package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.commands.CreateParticipant;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.dal.entities.Participant;
import edu.kmaooad.capstone23.communication.events.ChatCreated;
import edu.kmaooad.capstone23.communication.events.ParticipantCreated;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import edu.kmaooad.capstone23.communication.mocks.ParticipantMocks;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.events.UserCreated;
import edu.kmaooad.capstone23.users.handlers.CreateUserHandler;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateParticipantHandlerTest extends HandlerTest<Participant, CreateParticipant, ParticipantCreated> {
  @Inject
  CreateChatHandler createChatHandler;

  @Inject
  CreateUserHandler createUserHandler;

  private String chatId;

  private String userId;

  @Test
  @DisplayName("Should succeed if command is valid")
  public void shouldSucceedIfValidCommand() {
    seedChat();
    seedUser();

    Result<ParticipantCreated> result = run(
        ParticipantMocks.makeParticipant(new ObjectId(chatId), new ObjectId(userId))
    );

    assertHandled(result);
  }

  private void seedChat() {
    Chat chat = ChatMocks.validChat();

    CreateChat createChatCommand = new CreateChat();

    createChatCommand.setName(chat.name);
    createChatCommand.setAccessType(chat.accessType);

    Result<ChatCreated> result = createChatHandler.handle(createChatCommand);

    chatId = result.getValue().getId();
  }

  private void seedUser() {
    User user = UserMocks.validUser();

    CreateUser createUserCommand = new CreateUser();

    createUserCommand.setFirstName(user.firstName);
    createUserCommand.setLastName(user.lastName);
    createUserCommand.setEmail(user.email);

    Result<UserCreated> result = createUserHandler.handle(createUserCommand);

    userId = result.getValue().getId();
  }

  @Test
  @DisplayName("Should succeed if command is valid")
  public void shouldDenyCommandIfNoChatOrUser() {
    Result<ParticipantCreated> result = run(ParticipantMocks.invalidParticipant());

    assertDenied(result);
  }

  @Override
  public void initCommand() {
    command = new CreateParticipant();
  }

  @Override
  protected void insertPayload(Participant participant) {
    command.setChatId(participant.chatId.toHexString());
    command.setUserId(participant.userId.toHexString());
  }
}
