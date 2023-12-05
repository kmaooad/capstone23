package edu.kmaooad.capstone23.notifications.handler;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import com.google.inject.Inject;
import com.mongodb.assertions.Assertions;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.notifications.commands.CreateNotificationCommand;
import edu.kmaooad.capstone23.notifications.dal.NotificationChannel;
import edu.kmaooad.capstone23.notifications.dal.NotificationSourceEvent;
import edu.kmaooad.capstone23.notifications.events.NotificationCreated;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CreateNotificationHandlerTest{

    @Inject
    private CommandHandler<CreateNotificationCommand, NotificationCreated> handler;

    @Inject
    private UserRepository userRepository;
    
    @Test
    public void createNotificationTest(){
        User user = new User();
        user.id = new ObjectId();
        user.firstName = "AAA";
        user.lastName = "BBB";
        user.email = "email@gaaa.com";

        userRepository.insert(user);

        CreateNotificationCommand cmd = new CreateNotificationCommand(user.id.toString(), NotificationSourceEvent.DEPARTMENT_DELETED, NotificationChannel.sms);

        var result = handler.handle(cmd);

        Assertions.assertTrue(result.isSuccess());
    }
}
