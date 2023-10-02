package edu.kmaooad.capstone23.communication.mocks;

import edu.kmaooad.capstone23.communication.dal.entities.Chat;

public class ChatMocks {
  public static Chat validChat() {
    Chat chat = new Chat();

    chat.name = "Hello, world!";
    chat.accessType = Chat.AccessType.Public;

    return chat;
  }

  public static Chat chatWithNoType() {
    Chat chat = new Chat();

    chat.name = "Hello, world!";

    return chat;
  }

  public static Chat chatWithExhaustiveName() {
    Chat chat = new Chat();

    chat.name = "Lorem".repeat(50);
    chat.accessType = Chat.AccessType.Public;

    return chat;
  }

  public static Chat chatWithNoName() {
    Chat chat = new Chat();

    chat.name = "";
    chat.accessType = Chat.AccessType.Public;

    return chat;
  }
}
