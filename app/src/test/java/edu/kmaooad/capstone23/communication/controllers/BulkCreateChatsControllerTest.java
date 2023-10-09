package edu.kmaooad.capstone23.communication.controllers;

import edu.kmaooad.capstone23.common.ControllerTest;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import edu.kmaooad.capstone23.communication.utils.ChatsListWrapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class BulkCreateChatsControllerTest extends ControllerTest<ChatsListWrapper> {
  BulkCreateChatsControllerTest() {
    super("chats/bulk-create");
  }

  @Test
  @DisplayName("Should succeed if chats list is not empty")
  public void shouldSucceedIfNonEmptyChatsList() {
    assertRequestSucceeds(
        ChatMocks.validChats()
    );
  }

  @Test
  @DisplayName("Should succeed if chats list is not empty")
  public void shouldDenyRequestIfEmptyChatsList() {
    assertRequestFails(
        ChatMocks.noChats()
    );
  }
}
