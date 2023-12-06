package edu.kmaooad.capstone23.notifications;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifications.commands.SubscribeNotifications;
import edu.kmaooad.capstone23.notifications.events.NotificationSubscribed;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class NotificationSubscribedHandlerTest {
        @Inject
        private CommandHandler<SubscribeNotifications, NotificationSubscribed> handler;
        @Inject
        private UserRepository repository;

        @Test
        @DisplayName("Subscribe notification test")
        public void addNotificationTest(){
            User user = UserMocks.validUser();
            repository.insert(user);
            SubscribeNotifications command = new SubscribeNotifications();
            command.setUserId(user.id.toString());
            command.setNotificationStatus("Unsubscribed_on_notifications");
            command.setSubscriptionMethod("telegram");
            Assertions.assertTrue(handler.handle(command).isSuccess());
        }
}
