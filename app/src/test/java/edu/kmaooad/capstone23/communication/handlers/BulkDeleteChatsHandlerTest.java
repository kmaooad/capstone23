package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.BulkDeleteChats;
import edu.kmaooad.capstone23.communication.commands.DeleteChat;
import edu.kmaooad.capstone23.communication.events.ChatsBulkDeleted;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import edu.kmaooad.capstone23.communication.utils.ChatsListWrapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class BulkDeleteChatsHandlerTest extends HandlerTest<
        ChatsListWrapper,
        BulkDeleteChats,
        ChatsBulkDeleted
        > {
    @Test
    @DisplayName("Should succeed if command is valid")
    public void shouldSucceedIfValidCommand() {
        Result<ChatsBulkDeleted> result = run(ChatMocks.validDeleteChat());

        assertHandled(result);
    }

    @Test
    @DisplayName("Should succeed if command is valid")
    public void shouldDenyCommandIfEmptyChatsList() {
        Result<ChatsBulkDeleted> result = run(ChatMocks.noChats());

        assertDenied(result);
    }

    @Override
    protected void initCommand() {
        System.out.println("Called?");
        command = new BulkDeleteChats();
        System.out.println(command);
    }

    @Override
    protected void insertPayload(ChatsListWrapper wrapper) {
        List<DeleteChat> childCommands = wrapper.getChats()
                .stream()
                .map((chat) -> {
                    DeleteChat childCommand = new DeleteChat();

                    childCommand.setChatId(chat.id);

                    return childCommand;
                })
                .toList();

        command.setChats(childCommands);
    }
}
