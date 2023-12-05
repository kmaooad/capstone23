package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.commands.SendNotificationCommand;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.events.NotificationSent;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import edu.kmaooad.capstone23.notifications.services.impl.EmailNotificationChannelService;
import edu.kmaooad.capstone23.notifications.services.impl.SMSNotificationChannelService;
import edu.kmaooad.capstone23.notifications.services.impl.TelegramNotificationChannelService;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import edu.kmaooad.capstone23.common.ErrorCode;

@RequestScoped
public class SendNotificationHandler implements CommandHandler<SendNotificationCommand, NotificationSent> {

    @Inject
    private UserRepository userRepository;

    @Inject
    private NotificationService notificationService;

    @Inject
    private EmailNotificationChannelService emailService;

    @Inject
    private SMSNotificationChannelService smsService;

    @Inject
    private TelegramNotificationChannelService telegramService;

	@Override
	public Result<NotificationSent> handle(SendNotificationCommand command) {
        if(userRepository.findById(command.getNotification().user) == null){
            return new Result<>(ErrorCode.NOT_FOUND, "User not found");
        }
        Notification notification = command.getNotification();

        switch(notification.channel){
			case email:
                emailService.sendNotification(notification);
				break;
			case sms:
                smsService.sendNotification(notification);
				break;
			case telegram:
                telegramService.sendNotification(notification);
				break;
			default:
				break;

        }

        notification.sent = true;

        notificationService.insert(notification);

        return new Result<>(new NotificationSent(notification));

	}

}
