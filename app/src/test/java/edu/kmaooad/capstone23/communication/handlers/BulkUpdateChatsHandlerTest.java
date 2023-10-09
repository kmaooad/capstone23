package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.BulkUpdateChats;
import edu.kmaooad.capstone23.communication.commands.UpdateChat;
import edu.kmaooad.capstone23.communication.events.ChatsBulkUpdated;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import edu.kmaooad.capstone23.communication.utils.ChatsListWrapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class BulkUpdateChatsHandlerTest extends HandlerTest<
        ChatsListWrapper,
        BulkUpdateChats,
        ChatsBulkUpdated
        > {

    @Test
    @DisplayName("Should deny command if empty chats list")
    public void shouldDenyCommandIfEmptyChatsList() {
        Result<ChatsBulkUpdated> result = run(ChatMocks.noChats());

        assertDenied(result);
    }

    @Override
    protected void initCommand() {
        System.out.println("Called?");
        command = new BulkUpdateChats();
        System.out.println(command);
    }

    @Override
    protected void insertPayload(ChatsListWrapper wrapper) {
        // You'll need to modify the below code to adapt it to the fields in the Update command
        // For example: update the name or other fields as needed
        List<UpdateChat> childCommands = wrapper.getChats()
                .stream()
                .map((chat) -> {
                    UpdateChat childCommand = new UpdateChat();

                    childCommand.setId(chat.id);
                    childCommand.setName(chat.name);
                    childCommand.setDescription(chat.description);
                    childCommand.setAccessType(chat.accessType.toString());

                    return childCommand;
                })
                .toList();

        command.setChats(childCommands);
    }
}
