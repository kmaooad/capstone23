package edu.kmaooad.capstone23.communication.mocks;

import edu.kmaooad.capstone23.common.Mocks;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.utils.ChatsListWrapper;
import org.bson.types.ObjectId;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ChatMocks extends Mocks {
  public static final int DEFAULT_CHATS_LENGTH = 3;

  public static ChatsListWrapper noChats() {
    return new ChatsListWrapper(Collections.emptyList());
  }

  public static ChatsListWrapper validChats() {
    List<Chat> chats = IntStream.range(0, ChatMocks.DEFAULT_CHATS_LENGTH)
        .mapToObj((noop) -> ChatMocks.validChat())
        .toList();

    return new ChatsListWrapper(chats);
  }

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

    chat.name = mockLongString();
    chat.accessType = Chat.AccessType.Public;

    return chat;
  }

  public static Chat chatWithNoName() {
    Chat chat = new Chat();

    chat.name = "";
    chat.accessType = Chat.AccessType.Public;

    return chat;
  }

  public static Chat validChatId() {
    Chat chat = new Chat();
    chat.id = new ObjectId("55153a8014829a865bbf700d");
    return chat;
  }

  public static Chat invalidChatId() {
    Chat chat = new Chat();
    chat.id = new ObjectId("55153a8014829b865bbf700d");
    return chat;
  }
}
