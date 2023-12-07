package edu.kmaooad.capstone23.messages.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingSystem;
import edu.kmaooad.capstone23.messages.dal.UserMessagingSystem;
import edu.kmaooad.capstone23.messages.dal.UserMessagingSystemRepository;
import edu.kmaooad.capstone23.messages.events.UserMessagingSystemSelected;
import edu.kmaooad.capstone23.messages.utils.EmailValidator;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidatorContext;
import org.bson.types.ObjectId;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

@RequestScoped
public class SelectUserMessagingSystemHandler implements CommandHandler<SelectUserMessagingSystem, UserMessagingSystemSelected> {
    @Inject
    UserMessagingSystemRepository messageRepository;

    @Inject
    EmailValidator emailValidator;

    @Override
    public Result<UserMessagingSystemSelected> handle(SelectUserMessagingSystem command) {
        try {
            validateSystem(command.getSystemType(), command.getSystemInfo());


            var messagingType = new UserMessagingSystem();
            messagingType.userId = new ObjectId(command.getUserId());
            messagingType.messageSystemInfo = command.getSystemInfo();
            messagingType.messageSystemType = command.getSystemType();

            if (command.isCreate()) {
                messageRepository.insert(messagingType);

            } else if (!command.isCreate()) {
                messageRepository.remove(messagingType);
            }
            return new Result<>(new UserMessagingSystemSelected());
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, e.getMessage());
        }
    }

    private void validateSystem(String system, String systemInfo) throws IllegalArgumentException {
        switch (system) {
            case SelectUserMessagingSystem.SYSTEM_TELEGRAM -> {
                if (!systemInfo.startsWith("@"))
                    throw new IllegalArgumentException("Illegal telegram account");
            }
            case SelectUserMessagingSystem.SYSTEM_EMAIL -> {
                if (!emailValidator.isValid(systemInfo))
                    throw new IllegalArgumentException("Illegal email");
            }
            default -> throw new IllegalArgumentException("Illegal system " + system);
        }
    }

}
