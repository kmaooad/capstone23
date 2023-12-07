package edu.kmaooad.capstone23.messages.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingType;
import edu.kmaooad.capstone23.messages.dal.UserMessageTypes;
import edu.kmaooad.capstone23.messages.dal.UserMessageTypesRepository;
import edu.kmaooad.capstone23.messages.events.UserMessagingTypeSelected;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class SelectUserMessagingTypeHandler implements CommandHandler<SelectUserMessagingType, UserMessagingTypeSelected> {
    @Inject
    UserMessageTypesRepository messageRepository;

    @Override
    public Result<UserMessagingTypeSelected> handle(SelectUserMessagingType command) {
        try {
            validateMessageType(command.getMessageType());


            var messagingType = new UserMessageTypes();
            messagingType.userId = new ObjectId(command.getUserId());
            messagingType.messageType = command.getMessageType();

            if (command.isCreate()) {
                messageRepository.insert(messagingType);

            } else if (!command.isCreate()) {
                messageRepository.remove(messagingType);
            }
            return new Result<>(new UserMessagingTypeSelected());
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, e.getMessage());
        }
    }

    private void validateMessageType(String messageType) throws IllegalArgumentException {
        switch (messageType) {
            case SelectUserMessagingType.CREATE_ORG_MESSAGE, SelectUserMessagingType.UPDATE_ORG_MESSAGE -> {
            }
            default -> throw new IllegalArgumentException("Illegal messageType " + messageType);
        }
    }
}
