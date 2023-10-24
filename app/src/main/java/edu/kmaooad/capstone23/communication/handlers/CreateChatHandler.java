package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ChatCreated;
import edu.kmaooad.capstone23.communication.services.ChatService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateChatHandler implements CommandHandler<CreateChat, ChatCreated> {
  @Inject
  ChatService chatService;

  @Override
  public Result<ChatCreated> handle(CreateChat command) {
    var chat = mapChatCommand(command);

    var insertedChat = chatService.insert(chat);

    ChatCreated createdChat = new ChatCreated(insertedChat.id.toHexString());

    return new Result<ChatCreated>(createdChat);
  }

  private Chat mapChatCommand(CreateChat command) {
    var chat = new Chat();

    chat.name = command.getName();
    chat.description = command.getDescription();
    chat.accessType = Chat.AccessType.valueOf(command.getAccessType());

    return chat;
  }
}
