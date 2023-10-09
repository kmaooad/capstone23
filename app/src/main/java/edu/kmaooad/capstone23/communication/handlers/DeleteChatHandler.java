package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.DeleteChat;
import edu.kmaooad.capstone23.communication.dal.repositories.ChatRepository;
import edu.kmaooad.capstone23.communication.events.ChatDeleted;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteChatHandler implements CommandHandler<DeleteChat, ChatDeleted> {
    @Inject
    ChatRepository chatRepository;

    @Override
    public Result<ChatDeleted> handle(DeleteChat command) {
        if(chatRepository.deleteById(command.getChatId())) {
            return new Result<>(new ChatDeleted(command.getChatId()));
        } else {
            return new Result<>(ErrorCode.DELETE_FAILED, "Failed to delete chat");
        }
    }
}
