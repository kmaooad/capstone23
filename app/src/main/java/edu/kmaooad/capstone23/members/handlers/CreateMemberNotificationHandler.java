package edu.kmaooad.capstone23.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.members.services.UserService;
import edu.kmaooad.capstone23.members.services.impl.NotificationServiceImpl;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class CreateMemberNotificationHandler implements CommandHandler<CreateBasicMember, BasicMemberCreated> {

    @Delegate
    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> commandHandlerDecoratee;
    @Inject
    NotificationServiceImpl notificationService;
    @Inject
    UserService userService;

    @Override
    public Result<BasicMemberCreated> handle(CreateBasicMember command) {
        Result<BasicMemberCreated> result = commandHandlerDecoratee.handle(command);
        var user = userService.findByEmail(command.getEmail());
        if (result.isSuccess() && user.isPresent()) {
            var notificationType = notificationService.findByUserAndType(
                    user.get().getId().toString(), "CREATE_ORG_MEMBER"
            );

            notificationType.ifPresent(type -> sendNotification(type.notificationMethod));
        }
        return result;
    }

    private void sendNotification(String notificationMethod) {
        //Send appropriate notification. Notification method represent sms, email, etc.
    }
}
