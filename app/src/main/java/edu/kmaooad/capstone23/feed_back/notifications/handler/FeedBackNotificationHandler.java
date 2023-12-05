package edu.kmaooad.capstone23.feed_back.notifications.handler;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.feed_back.dal.FeedBackRepository;
import edu.kmaooad.capstone23.feed_back.notifications.command.CreateFeedBackNotification;
import edu.kmaooad.capstone23.feed_back.notifications.dal.FeedBackNotificationRepository;
import edu.kmaooad.capstone23.feed_back.notifications.dal.Notification;
import edu.kmaooad.capstone23.feed_back.notifications.event.FeedBackNotificationCreated;
import jakarta.inject.Inject;

public class FeedBackNotificationHandler implements CommandHandler<CreateFeedBackNotification, FeedBackNotificationCreated> {

    @Inject
    FeedBackRepository feedBackRepository;

    @Inject
    FeedBackNotificationRepository feedBackNotificationRepository;

    @Override
    public Result<FeedBackNotificationCreated> handle(CreateFeedBackNotification command) {
        if (feedBackRepository.findById(command.getFeedBackId()).isEmpty()) {
            return new Result<>(ErrorCode.NOT_FOUND, "Feed back not found");
        }

        Notification notification = new Notification();
        notification.feedBackId = (command.getFeedBackId());
        notification.type = (command.getType());
        notification.to = (command.getTo());
        feedBackNotificationRepository.insert(notification);

        return new Result<>(new FeedBackNotificationCreated(
                notification.id.toString(),
                notification.feedBackId,
                notification.type,
                notification.to
        ));
    }
}
