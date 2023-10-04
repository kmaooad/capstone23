package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.BulkCreateChats;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.dal.repositories.ChatRepository;
import edu.kmaooad.capstone23.communication.events.ChatCreated;
import edu.kmaooad.capstone23.communication.events.ChatsBulkCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class BulkCreateChatsHandler implements CommandHandler<BulkCreateChats, ChatsBulkCreated> {
  @Inject
  ChatRepository chatRepository;

  private List<Chat> chats;

  private ChatsBulkCreated createdChats;

  @Override
  public Result<ChatsBulkCreated> handle(BulkCreateChats command) {
    boolean isCommandValid = validateCommand(command);

    if (!isCommandValid) {
      return new Result<ChatsBulkCreated>(ErrorCode.VALIDATION_FAILED, "No chats to create");
    }

    initChats(command);

    chatRepository.bulkInsert(chats);

    initResponse();

    return new Result<ChatsBulkCreated>(createdChats);
  }

  private boolean validateCommand(BulkCreateChats command) {
    return !command.getChats().isEmpty();
  }

  private void initChats(BulkCreateChats command) {
    chats = new ArrayList<Chat>();

    List<CreateChat> childCommands = command.getChats();

    chats = childCommands
        .stream()
        .map((childCommand) -> {
          Chat chat = new Chat();

          chat.name = childCommand.getName();
          chat.description = childCommand.getDescription();
          chat.accessType = Chat.AccessType.valueOf(childCommand.getAccessType());

          return chat;
        })
        .toList();
  }

  private void initResponse() {
    List<ChatCreated> chatsMappedToResult = chats
        .stream()
        .map((chat) -> new ChatCreated(chat.id.toHexString()))
        .toList();

    createdChats = new ChatsBulkCreated(chatsMappedToResult);
  }
}
