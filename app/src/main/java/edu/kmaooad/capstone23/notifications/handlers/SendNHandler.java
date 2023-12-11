package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.SendNotification;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NSent;
import edu.kmaooad.capstone23.notifications.services.NService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class SendNHandler implements CommandHandler<SendNotification, NotificationSent> {
    @Inject
    NotificationService nService;
    @Inject
    UserRepository uRepository;

    @Override
    public Result<NotificationSent> handle(SendNotification command) {
        if (uRepository.findById(command.getUserId()).isEmpty()) {
            return new Result<>(ErrorCode.NOT_FOUND, "Couldn't find the user with id: " + command.getUID());
        }
        Notification n = new Notification();
        n.nType = command.getNType();
        n.userId = new ObjectId(command.getUID());
        n.event = command.getNEvent();
        nService.insert(n);
        return new Result<>(new NotificationSent(n.id.toString()));
    }
}