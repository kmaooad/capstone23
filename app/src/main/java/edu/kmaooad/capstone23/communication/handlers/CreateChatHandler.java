package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.dal.repositories.ChatRepository;
import edu.kmaooad.capstone23.communication.events.ChatCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateChatHandler implements CommandHandler<CreateChat, ChatCreated> {
  @Inject
  ChatRepository chatRepository;

  private Chat chat;

  @Override
  public Result<ChatCreated> handle(CreateChat command) {
    initChat(command);

    chatRepository.insert(chat);

    ChatCreated createdChat = new ChatCreated(chat.id.toHexString());

    return new Result<ChatCreated>(createdChat);
  }

  private void initChat(CreateChat command) {
    chat = new Chat();

    chat.name = command.getName();
    chat.description = command.getDescription();
    chat.accessType = Chat.AccessType.valueOf(command.getAccessType());
  }
}
