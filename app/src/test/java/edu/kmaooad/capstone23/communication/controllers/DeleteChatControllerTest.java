package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.ControllerTest;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteChatControllerTest extends ControllerTest<Chat> {
    DeleteChatControllerTest() {
        super("chats/delete");
    }

    @Test
    @DisplayName("Should succeed if chat ID is valid")
    public void shouldSucceedIfValidChatId() {
        assertRequestFails(
                ChatMocks.chatWithExhaustiveName()
        );
    }

    @Test
    @DisplayName("Should deny request if chat ID is invalid")
    public void shouldDenyRequestIfInvalidChatId() {
        assertRequestFails(
                ChatMocks.chatWithNoName()
        );
    }
}
