package edu.kmaooad.capstone23.notifications.handlers;

import edu.kmaooad.capstone23.activities.commands.CreateExtracurricularActivity;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifications.commands.CreateNotification;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static edu.kmaooad.capstone23.common.Mocks.mockValidEmail;

@QuarkusTest
public class ActivityCreatedNotificationHandlerTest {
    @Inject
    private CommandHandler<CreateNotification, NotificationCreated> notificationHandler;
    @Inject
    private CommandHandler<CreateExtracurricularActivity, ExtracurricularActivityCreated> activityHandler;
    @Inject
    private UserRepository userRepository;

    @Test
    @DisplayName("Activity created notification")
    public void addNotificationTest(){
        User user = new User();
        user.firstName = "Peter";
        user.lastName = "Pan";
        user.email = mockValidEmail();
        userRepository.insert(user);

        CreateNotification notificationCommand = new CreateNotification();
        notificationCommand.setUserId(user.id.toString());
        notificationCommand.setNotificationMethod("EMAIL");
        notificationCommand.setEventType("ACTIVITY_CREATED");
        notificationHandler.handle(notificationCommand);

        CreateExtracurricularActivity activityCommand = new CreateExtracurricularActivity();
        activityCommand.setExtracurricularActivityName("New Activity!");

        Assertions.assertTrue(activityHandler.handle(activityCommand).isSuccess());
    }
}
