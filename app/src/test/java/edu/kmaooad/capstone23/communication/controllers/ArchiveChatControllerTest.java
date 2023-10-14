package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.ControllerTest;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ArchiveChatControllerTest extends ControllerTest<Chat> {

    ArchiveChatControllerTest() {
        super("/chats/archive");
    }

    @Test
    @DisplayName("Should deny request if chat ID is invalid")
    public void shouldDenyRequestIfInvalidChatId() {
        assertRequestFails(ChatMocks.invalidChatId());
    }
}
