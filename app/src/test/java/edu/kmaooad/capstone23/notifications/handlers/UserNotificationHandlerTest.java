package edu.kmaooad.capstone23.notifications.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifications.commands.UserNotificationTrigger;
import edu.kmaooad.capstone23.notifications.dal.UserNotification;
import edu.kmaooad.capstone23.notifications.dal.UserNotificationsRepository;
import edu.kmaooad.capstone23.notifications.events.UserNotificationTriggered;
import edu.kmaooad.capstone23.notifications.services.UserNotificationsService;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.events.UserCreated;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class UserNotificationHandlerTest {

    @Inject
    CommandHandler<CreateUser, UserCreated> createUserHandler;

    @Inject
    CommandHandler<UserNotificationTrigger, UserNotificationTriggered> notificationTriggerHandler;

    @Inject
    UserNotificationsRepository repository;
    
    @Test
    public void basicTypeTest() {
        var userId = createUser();
        var request = new UserNotificationTrigger();

        request.setMethod(UserNotificationTrigger.TELEGRAM);
        request.setMethodInfo("@some_user");
        request.setType(UserNotificationTrigger.JOB_CREATED);
        request.setUserId(userId);

        var response = notificationTriggerHandler.handle(request);
        Assertions.assertTrue(response.isSuccess());

        Assertions.assertTrue(repository.listAll().stream().anyMatch(v ->
                v.notificationType.equals(UserNotificationTrigger.JOB_CREATED) && v.userId.toString().equals(userId)));
    }

    String createUser() {
        var userMock = UserMocks.validUser();
        var createUser = new CreateUser();
        createUser.setEmail(userMock.email);
        createUser.setFirstName(userMock.firstName);
        createUser.setLastName(userMock.lastName);
        return createUserHandler.handle(createUser).getValue().getId();
    }
}