package edu.kmaooad.capstone23.notifs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifs.commands.NotifCommand;
import edu.kmaooad.capstone23.notifs.dal.Notif;
import edu.kmaooad.capstone23.notifs.events.NotifEvent;
import edu.kmaooad.capstone23.notifs.service.NotifService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class NotifHandler implements CommandHandler<NotifCommand, NotifEvent> {

    @Inject
    private NotifService notificationService;
    @Inject
    private UserRepository userRepository;

    @Override
    public Result<NotifEvent> handle(NotifCommand command) {
        if(userRepository.findById(command.getUserId()).isEmpty())
            return new Result<>(ErrorCode.ENTITY_NOT_FOUND, "No user found");
        Notif notification = new Notif();
        notification.userId = new ObjectId(command.getUserId());
        notification.notificationType = command.getNotificationType();
        notification.notificationMethod = command.getNotificationMethod();
        notificationService.insert(notification);

        return new Result<>(new NotifEvent(
                notification.userId.toString(),
                notification.notificationType.toString(),
                notification.notificationMethod.toString()
        ));
    }
}