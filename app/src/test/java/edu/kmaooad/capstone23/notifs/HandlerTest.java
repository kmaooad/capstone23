package edu.kmaooad.capstone23.notifs;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifs.commands.NotifCommand;
import edu.kmaooad.capstone23.notifs.dal.Notif;
import edu.kmaooad.capstone23.notifs.events.NotifEvent;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class HandlerTest {
    @Inject
    private CommandHandler<NotifCommand, NotifEvent> handler;
    @Inject
    private UserRepository repository;

    @Test
    @DisplayName("Add notification: Starter test")
    public void addNotificationTest(){
        User user = UserMocks.validUser();
        repository.insert(user);
        NotifCommand command = new NotifCommand();
        command.setUserId(user.id.toString());
        command.setNotificationType("Subscribed_to_notifications");
        command.setNotificationMethod("sms");
        Assertions.assertTrue(handler.handle(command).isSuccess());
    }
}