package edu.kmaooad.capstone23.notification.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.communication.commands.CreateChat;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.events.ChatCreated;
import edu.kmaooad.capstone23.communication.mocks.ChatMocks;
import edu.kmaooad.capstone23.notification.clients.NotificationDeliveryClient;
import edu.kmaooad.capstone23.notification.dto.Notification;
import edu.kmaooad.capstone23.notification.listeners.NotificationListener;
import edu.kmaooad.capstone23.notification.publishers.NotificationPublisher;
import edu.kmaooad.capstone23.notification.typedefs.NotificationChannel;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class NotifyChatCreatedHandlerTest {
  @Inject
  NotificationPublisher publisher;

  @Inject
  CommandHandler<CreateChat, ChatCreated> createChatHandler;

  @Inject
  SubscribeToNotificationHandler subscribeToNotificationHandler;

  @Inject
  NotificationDeliveryClient client;

  @BeforeEach
  public void beforeEach() {
    for (NotificationChannel channel : NotificationChannel.values()) {
      NotificationListener listener = subscribeToNotificationHandler.mapToListener(channel);

      publisher.subscribe(listener);
    }
  }

  @AfterEach
  public void afterEach() {
    client.purge();
    publisher.purge();
  }

  @Test
  public void testAllChannelsAreNotified() {
    Chat chat = ChatMocks.validChat();
    CreateChat command = new CreateChat();

    command.setName(chat.name);
    command.setAccessType(chat.accessType);

    createChatHandler.handle(command);

    Map<NotificationChannel, Notification> sentMessages = client.getAllSent();

    assertNotNull(sentMessages.get(NotificationChannel.SMS));
    assertNotNull(sentMessages.get(NotificationChannel.EMAIL));
    assertNotNull(sentMessages.get(NotificationChannel.TELEGRAM));
  }
}
