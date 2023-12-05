package edu.kmaooad.capstone23.messages.handler;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingSystem;
import edu.kmaooad.capstone23.messages.dal.UserMessagingSystemRepository;
import edu.kmaooad.capstone23.messages.events.UserMessagingSystemSelected;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.events.UserCreated;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SelectUserMessagingSystemHandlerTest {


    @Inject
    CommandHandler<CreateUser, UserCreated> createUserHandler;

    @Inject
    CommandHandler<SelectUserMessagingSystem, UserMessagingSystemSelected> selectTypeHandler;

    @Inject
    UserMessagingSystemRepository messageTypesRepository;

    @Test
    public void basicSystemTest() {
        var userId = createUser();
        var request = new SelectUserMessagingSystem();
        request.setCreate(true);
        request.setUserId(userId);
        request.setSystemType(SelectUserMessagingSystem.SYSTEM_EMAIL);
        request.setSystemInfo("test@duck.com");
        var response = selectTypeHandler.handle(request);
        Assertions.assertTrue(response.isSuccess());

        Assertions.assertTrue(messageTypesRepository.listAll().stream().anyMatch(v ->
                v.messageSystemType.equals(SelectUserMessagingSystem.SYSTEM_EMAIL)
                        && v.messageSystemInfo.equals("test@duck.com")
                        && v.userId.toString().equals(userId)));
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
