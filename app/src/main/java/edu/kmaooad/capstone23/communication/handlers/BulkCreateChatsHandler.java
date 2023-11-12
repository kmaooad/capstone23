package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.BulkCreateChats;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ChatCreated;
import edu.kmaooad.capstone23.communication.events.ChatsBulkCreated;
import edu.kmaooad.capstone23.communication.interfaces.services.ChatService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class BulkCreateChatsHandler implements CommandHandler<BulkCreateChats, ChatsBulkCreated> {
  @Inject
  ChatService chatService;

  @Override
  public Result<ChatsBulkCreated> handle(BulkCreateChats command) {
    if (!command.getChats().isEmpty()) {
      return new Result<ChatsBulkCreated>(ErrorCode.VALIDATION_FAILED, "No chats to create");
    }

    var mappedChats = this.bulkMapChats(command);

    var chats = chatService.bulkInsert(mappedChats);

    var createdChats = new ChatsBulkCreated(mapChatsForResponce(chats));

    return new Result<ChatsBulkCreated>(createdChats);
  }

  private List<ChatCreated> mapChatsForResponce(List<Chat> chats) {
    return chats
        .stream()
        .map((chat) -> new ChatCreated(chat.id.toHexString()))
        .toList();
  }

  private List<Chat> bulkMapChats(BulkCreateChats bulkCommand) {
    return bulkCommand.getChats()
        .stream()
        .map((command) -> mapChat(command))
        .toList();
  }

  private Chat mapChat(CreateChat command) {
    Chat chat = new Chat();

    chat.name = command.getName();
    chat.description = command.getDescription();
    chat.accessType = Chat.AccessType.valueOf(command.getAccessType());

    return chat;
  }
}
