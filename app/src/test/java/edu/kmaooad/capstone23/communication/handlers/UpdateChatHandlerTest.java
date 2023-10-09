package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.HandlerTest;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.UpdateChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ChatUpdated;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateChatHandlerTest extends HandlerTest<Chat, UpdateChat, ChatUpdated> {

    @Test
    @DisplayName("Should deny command if chat ID is not found")
    public void shouldDenyCommandIfChatNotFound() {
        Result<ChatUpdated> result = run(ChatMocks.chatWithNoName());

        assertDenied(result);
    }

    @Override
    protected void initCommand() {
        command = new UpdateChat();
    }

    @Override
    protected void insertPayload(Chat entity) {
        command.setName(entity.name);
        command.setDescription(entity.description);
        command.setAccessType(entity.accessType.toString());
    }
}
