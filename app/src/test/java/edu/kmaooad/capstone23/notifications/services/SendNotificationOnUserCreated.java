package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.notifications.models.Event;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.events.UserCreated;
import edu.kmaooad.capstone23.users.handlers.CreateUserHandler;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;


@QuarkusTest
public class SendNotificationOnUserCreated {

    @Produces
    public EmailService mockExternalService() {
        return new EmailService();
    }

    @Inject
    private CreateUserHandler createUserHandler;


    private boolean isEmailSent = false;


    @Test
    @DisplayName("Should send notification when user created")
    public void shouldSendNotificationWhenUserCreated() {
        QuarkusMock.installMockForType(
                new EmailService() {
                    @Override
                    public void sendEmail(String email, Event event, String message) {
                        isEmailSent = true;
                    }
                }, EmailService.class);
        User user = new UserMocks().validUser();
        CreateUser createUserCommand = new CreateUser();
        createUserCommand.setFirstName(user.firstName);
        createUserCommand.setLastName(user.lastName);
        createUserCommand.setEmail(user.email);
        createUserCommand.setPhoneNumber(user.phoneNumber);
        createUserCommand.setNotificationTypes(new ArrayList<>(Collections.singletonList(NotificationType.EMAIL)));
        Result<UserCreated> result = createUserHandler.handle(createUserCommand);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(isEmailSent);
    }
}
