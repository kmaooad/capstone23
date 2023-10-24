package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.BulkDeleteChats;
import edu.kmaooad.capstone23.communication.commands.DeleteChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ChatDeleted;
import edu.kmaooad.capstone23.communication.events.ChatsBulkDeleted;
import edu.kmaooad.capstone23.communication.services.ChatService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class BulkDeleteChatsHandler implements CommandHandler<BulkDeleteChats, ChatsBulkDeleted> {
    @Inject
    ChatService chatService;

    @Override
    public Result<ChatsBulkDeleted> handle(BulkDeleteChats command) {
        if (!command.getChats().isEmpty()) {
            return new Result<ChatsBulkDeleted>(ErrorCode.VALIDATION_FAILED, "No chats to delete");
        }

        var chats = bulkMapDeleteChat(command);

        if (!chatService.bulkDelete(chats)) {
          return new Result<ChatsBulkDeleted>(ErrorCode.EXCEPTION, "Failed to delete chats");
        }

        var deletedChats = mapDeletedChatResponse(chats);

        return new Result<ChatsBulkDeleted>(deletedChats);
    }

    private List<Chat> bulkMapDeleteChat(BulkDeleteChats bulkCommand) {
        return bulkCommand.getChats()
                .stream()
                .map((childCommand) -> mapDeleteChat(childCommand))
                .toList();
    }

    private Chat mapDeleteChat(DeleteChat command) {
        Chat chat = new Chat();
        chat.id = command.getChatId();
        return chat;
    }

    private ChatsBulkDeleted mapDeletedChatResponse(List<Chat> chats) {
        List<ChatDeleted> chatsMappedToResult = chats
                .stream()
                .map((chat) -> new ChatDeleted(chat.id.toHexString()))
                .toList();

        return new ChatsBulkDeleted(chatsMappedToResult);
    }
}
