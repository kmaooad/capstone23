package edu.kmaooad.capstone23.communication.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.BulkCreateChats;
import edu.kmaooad.capstone23.communication.commands.BulkUpdateChats;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.dal.repositories.ChatRepository;
import edu.kmaooad.capstone23.communication.events.ChatUpdated;
import edu.kmaooad.capstone23.communication.events.ChatsBulkUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class BulkUpdateChatsHandler implements CommandHandler<BulkUpdateChats, ChatsBulkUpdated> {
    @Inject
    ChatRepository chatRepository;

    private List<Chat> chats;

    private ChatsBulkUpdated updatedChats;

    @Override
    public Result<ChatsBulkUpdated> handle(BulkUpdateChats command) {
        boolean isCommandValid = validateCommand(command);

        if (!isCommandValid) {
            return new Result<ChatsBulkUpdated>(ErrorCode.VALIDATION_FAILED, "No chats to update");
        }

        updateChats(command);

        chatRepository.bulkUpdate(chats);

        initResponse();

        return new Result<ChatsBulkUpdated>(updatedChats);
    }
    // In BulkUpdateChatsHandler class
    private void updateChats(BulkUpdateChats command) {
        chats = command.getChats()
                .stream()
                .map(chatCommand -> {
                    Chat chat = chatRepository.findById(chatCommand.getId());
                    if (chat != null) {
                        chat.name = chatCommand.getName();
                        chat.description = chatCommand.getDescription();
                        chat.accessType = Chat.AccessType.valueOf(chatCommand.getAccessType());
                    }
                    return chat;
                })
                .filter(chat -> chat != null)  // Filtering out nulls
                .collect(Collectors.toList());
    }

    private boolean validateCommand(BulkUpdateChats command) {
        return !command.getChats().isEmpty();
    }

    private void initResponse() {
        List<ChatUpdated> chatsMappedToResult = chats
                .stream()
                .map((chat) -> new ChatUpdated(chat.id.toHexString()))
                .toList();

        updatedChats = new ChatsBulkUpdated(chatsMappedToResult);
    }
}
