package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.ControllerTest;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import edu.kmaooad.capstone23.communication.utils.ChatsListWrapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class BulkUpdateChatsControllerTest extends ControllerTest<ChatsListWrapper> {
    BulkUpdateChatsControllerTest() {
        super("chats/bulk-update");
    }

    @Test
    @DisplayName("Should succeed if chats list is not empty and valid for update")
    public void shouldSucceedIfNonEmptyChatsList() {
        assertRequestSucceeds(
                ChatMocks.validChats() // assuming validChats are also valid for update
        );
    }

    @Test
    @DisplayName("Should deny request if chats list is empty")
    public void shouldDenyRequestIfEmptyChatsList() {
        assertRequestFails(
                ChatMocks.noChats()
        );
    }
}
