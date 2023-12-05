package edu.kmaooad.capstone23.notifications.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.notifications.commands.UserNotificationTrigger;
import edu.kmaooad.capstone23.notifications.events.UserNotificationTriggered;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.events.UserCreated;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class NotifyServiceTest {
    @Inject
    CommandHandler<CreateUser, UserCreated> createUserHandler;

    @Inject
    CommandHandler<UserNotificationTrigger, UserNotificationTriggered> userNotificationHandler;

    @Inject
    CommandHandler<CreateOrg, OrgCreated> createOrgHandler;


    @Test
    void NotifyServiceCreateOrgTest() {

        var userId = createUser();

        {
            var request = new UserNotificationTrigger();
            
            request.setMethod(UserNotificationTrigger.TELEGRAM);
            request.setMethodInfo("@some_user");
            request.setType(UserNotificationTrigger.ORG_CREATED);
            request.setUserId(userId);
        }


        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA");
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";
        var response = createOrgHandler.handle(command);

        Assertions.assertTrue(response.isSuccess());


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