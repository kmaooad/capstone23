package edu.kmaooad.capstone23.messages.service;


import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingSystem;
import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingType;
import edu.kmaooad.capstone23.messages.events.UserMessagingSystemSelected;
import edu.kmaooad.capstone23.messages.events.UserMessagingTypeSelected;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.events.UserCreated;
import edu.kmaooad.capstone23.users.handlers.CreateUserHandler;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class NotifyEventServiceTest {
    @Inject
    CommandHandler<CreateUser, UserCreated> createUserHandler;
    @Inject
    CommandHandler<SelectUserMessagingType, UserMessagingTypeSelected> selectTypeHandler;
    @Inject
    CommandHandler<SelectUserMessagingSystem, UserMessagingSystemSelected> selectSystemHandler;

    @Inject
    CommandHandler<CreateOrg, OrgCreated> createOrgHandler;


    @Test
    void basicTest() {

        var userId = createUser();

        {
            var type = new SelectUserMessagingType();
            type.setMessageType(SelectUserMessagingType.CREATE_ORG_MESSAGE);
            type.setUserId(userId);
            type.setCreate(true);
            selectTypeHandler.handle(type);
        }
        {
            var system = new SelectUserMessagingSystem();
            system.setCreate(true);
            system.setUserId(userId);
            system.setSystemType(SelectUserMessagingSystem.SYSTEM_EMAIL);
            system.setSystemInfo("hello@duck.com");
            selectSystemHandler.handle(system);
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
