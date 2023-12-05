package edu.kmaooad.capstone23.notification.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notification.commands.SubscribeToNotification;
import edu.kmaooad.capstone23.notification.events.SubscribedToNotification;
import edu.kmaooad.capstone23.notification.listeners.EmailNotificationSender;
import edu.kmaooad.capstone23.notification.listeners.NotificationListener;
import edu.kmaooad.capstone23.notification.listeners.SmsNotificationSender;
import edu.kmaooad.capstone23.notification.listeners.TelegramNotificationSender;
import edu.kmaooad.capstone23.notification.publishers.NotificationPublisher;
import edu.kmaooad.capstone23.notification.typedefs.NotificationChannel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import edu.kmaooad.capstone23.common.Result;

@RequestScoped
public class SubscribeToNotificationHandler implements CommandHandler<SubscribeToNotification, SubscribedToNotification> {
  @Inject
  NotificationPublisher publisher;

  @Inject
  SmsNotificationSender smsNotificationSender;

  @Inject
  EmailNotificationSender emailNotificationSender;

  @Inject
  TelegramNotificationSender telegramNotificationSender;

  @Override
  public Result<SubscribedToNotification> handle(SubscribeToNotification command) {
    NotificationChannel channel = command.channel();
    NotificationListener listener = mapToListener(channel);
    SubscribedToNotification event = new SubscribedToNotification(channel);

    publisher.subscribe(listener);

    return new Result<>(event);
  }

  public NotificationListener mapToListener(NotificationChannel channel) {
    return switch (channel) {
      case EMAIL -> emailNotificationSender;
      case TELEGRAM -> telegramNotificationSender;
      case SMS -> smsNotificationSender;
    };
  }
}
