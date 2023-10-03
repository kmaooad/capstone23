package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.BulkCreateChats;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.events.ChatsBulkCreated;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import edu.kmaooad.capstone23.communication.utils.ChatsListWrapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class BulkCreateChatsHandlerTest extends HandlerTest<
    ChatsListWrapper,
    BulkCreateChats,
    ChatsBulkCreated
> {
  @Test
  @DisplayName("Should succeed if command is valid")
  public void shouldSucceedIfValidCommand() {
    Result<ChatsBulkCreated> result = run(ChatMocks.validChats());

    assertHandled(result);
  }

  @Test
  @DisplayName("Should succeed if command is valid")
  public void shouldDenyCommandIfEmptyChatsList() {
    Result<ChatsBulkCreated> result = run(ChatMocks.noChats());

    assertDenied(result);
  }

  @Override
  protected void initCommand() {
    System.out.println("Called?");
    command = new BulkCreateChats();
    System.out.println(command);
  }

  @Override
  protected void insertPayload(ChatsListWrapper wrapper) {
    List<CreateChat> childCommands = wrapper.getChats()
        .stream()
        .map((chat) -> {
          CreateChat childCommand = new CreateChat();

          childCommand.setName(chat.name);
          childCommand.setAccessType(chat.accessType);

          return childCommand;
        })
        .toList();

    command.setChats(childCommands);
  }
}
