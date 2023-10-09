package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.BulkCreateChats;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.commands.DeleteChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ChatDeleted;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import edu.kmaooad.capstone23.communication.utils.ChatsListWrapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class DeleteChatHandlerTest extends HandlerTest<Chat, DeleteChat, ChatDeleted> {

    @Test
    @DisplayName("Should deny command if chat ID is invalid")
    public void shouldDenyCommandIfInvalidChatId() {
        Result<ChatDeleted> result = run(ChatMocks.invalidChatId());

        assertDenied(result);
    }

    @Override
    public void initCommand() {
        command = new DeleteChat();
    }

    @Override
    protected void insertPayload(Chat chat) {
        command.setChatId(chat.id);
    }
}
