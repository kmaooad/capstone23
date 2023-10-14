package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.ArchiveChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.dal.repositories.ChatRepository;
import edu.kmaooad.capstone23.communication.events.ChatArchived;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ArchiveChatHandler implements CommandHandler<ArchiveChat, ChatArchived> {

    @Inject
    ChatRepository chatRepository;

    @Override
    public Result<ChatArchived> handle(ArchiveChat command) {
        Chat chat = chatRepository.findById(command.getChatId());
        if(chat == null) {
            return new Result<ChatArchived>(ErrorCode.NOT_FOUND, "Chat not found");
        }

        chat.archived = true;
        chatRepository.update(chat);

        return new Result<ChatArchived>(new ChatArchived(command.getChatId()));
    }
}
