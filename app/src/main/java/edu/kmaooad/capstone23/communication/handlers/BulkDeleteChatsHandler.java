package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.BulkDeleteChats;
import edu.kmaooad.capstone23.communication.commands.DeleteChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.dal.repositories.ChatRepository;
import edu.kmaooad.capstone23.communication.events.ChatDeleted;
import edu.kmaooad.capstone23.communication.events.ChatsBulkDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
@RequestScoped
public class BulkDeleteChatsHandler implements CommandHandler<BulkDeleteChats, ChatsBulkDeleted> {
    @Inject
    ChatRepository chatRepository;

    private List<Chat> chats;

    private ChatsBulkDeleted deletedChats;

    @Override
    public Result<ChatsBulkDeleted> handle(BulkDeleteChats command) {
        boolean isCommandValid = validateCommand(command);

        if (!isCommandValid) {
            return new Result<ChatsBulkDeleted>(ErrorCode.VALIDATION_FAILED, "No chats to create");
        }

        initChats(command);

        chatRepository.bulkDelete(chats);

        initResponse();

        return new Result<ChatsBulkDeleted>(deletedChats);
    }

    private boolean validateCommand(BulkDeleteChats command) {
        return !command.getChats().isEmpty();
    }

    private void initChats(BulkDeleteChats command) {
        chats = new ArrayList<>();

        List<DeleteChat> childCommands = command.getChats();

        chats = childCommands
                .stream()
                .map((childCommand) -> {
                    Chat chat = new Chat();
                    chat.id = childCommand.getChatId();
                    return chat;
                })
                .toList();
    }

    private void initResponse() {
        List<ChatDeleted> chatsMappedToResult = chats
                .stream()
                .map((chat) -> new ChatDeleted(chat.id.toHexString()))
                .toList();

        deletedChats = new ChatsBulkDeleted(chatsMappedToResult);
    }
}
