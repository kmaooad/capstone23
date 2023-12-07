package edu.kmaooad.capstone23.notifications.handlers;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.CreateNotificationCommand;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import edu.kmaooad.capstone23.common.ErrorCode;

@RequestScoped
public class CreateNotificationHandler implements CommandHandler<CreateNotificationCommand, NotificationCreated> {

    @Inject
    private UserRepository userRepository;

    @Inject
    private NotificationService notificationService;

	@Override
	public Result<NotificationCreated> handle(CreateNotificationCommand command) {
        if(userRepository.findById(command.getUserId()) == null){
            return new Result<>(ErrorCode.NOT_FOUND, "User not found");
        }
        Notification notification = new Notification();
        notification.user = new ObjectId(command.getUserId());
        notification.event = command.getSource();
        notification.channel = command.getChannel();

        notificationService.insert(notification);

        return new Result<>(new NotificationCreated(notification));
	}

}
