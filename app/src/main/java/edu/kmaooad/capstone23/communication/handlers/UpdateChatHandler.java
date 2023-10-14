package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.UpdateChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.dal.repositories.ChatRepository;
import edu.kmaooad.capstone23.communication.events.ChatUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateChatHandler implements CommandHandler<UpdateChat, ChatUpdated> {
    @Inject
    ChatRepository chatRepository;

    @Override
    public Result<ChatUpdated> handle(UpdateChat command) {
        Chat chat = chatRepository.findById(command.getId()).orElse(null);
        if (chat == null) {
            return new Result<ChatUpdated>(ErrorCode.NOT_FOUND, "Chat not found");
        }

        chat.name = command.getName();
        chat.description = command.getDescription();
        chat.accessType = Chat.AccessType.valueOf(command.getAccessType());

        chatRepository.update(chat);

        return new Result<ChatUpdated>(new ChatUpdated(chat.id.toHexString()));
    }
}
