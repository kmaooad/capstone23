package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.ArchiveChat;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ChatArchived;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ArchiveChatHandlerTest extends HandlerTest<Chat, ArchiveChat, ChatArchived> {

    @Test
    @DisplayName("Should deny command if chat ID is invalid")
    public void shouldDenyCommandIfInvalidChatId() {
        Result<ChatArchived> result = run(ChatMocks.invalidChatId());

        assertDenied(result);
    }

    @Override
    public void initCommand() {
        command = new ArchiveChat();
    }

    @Override
    protected void insertPayload(Chat chat) {
        command.setChatId(chat.id);
    }
}
