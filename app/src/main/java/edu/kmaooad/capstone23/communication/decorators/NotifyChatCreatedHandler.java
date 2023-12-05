package edu.kmaooad.capstone23.communication.decorators;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.events.ChatCreated;
import edu.kmaooad.capstone23.notification.dto.Notification;
import edu.kmaooad.capstone23.notification.publishers.NotificationPublisher;
import edu.kmaooad.capstone23.notification.typedefs.NotificationType;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

@Decorator
public class NotifyChatCreatedHandler implements CommandHandler<CreateChat, ChatCreated> {
  @Inject
  @Delegate
  CommandHandler<CreateChat, ChatCreated> handler;

  @Inject
  NotificationPublisher publisher;

  @Override
  public Result<ChatCreated> handle(CreateChat command) {
    Result<ChatCreated> result = handler.handle(command);

    notifyChannels(result);

    return result;
  }

  private void notifyChannels(Result<ChatCreated> result) {
    Notification notification = initNotification(result);

    publisher.notify(notification);
  }

  private Notification initNotification(Result<ChatCreated> result) {
    String chatId = result.getValue().getId();

    if (result.isSuccess()) {
      return new Notification(NotificationType.SUCCESS, "Chat #" + chatId + " created");
    }

    return new Notification(NotificationType.FAILURE, "Cannot create chat #" + chatId);
  }
}
