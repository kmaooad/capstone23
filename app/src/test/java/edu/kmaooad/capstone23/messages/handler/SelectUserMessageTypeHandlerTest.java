package edu.kmaooad.capstone23.messages.handler;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.messages.commands.SelectUserMessagingType;
import edu.kmaooad.capstone23.messages.dal.UserMessageTypesRepository;
import edu.kmaooad.capstone23.messages.events.UserMessagingTypeSelected;
import edu.kmaooad.capstone23.users.commands.CreateUser;
import edu.kmaooad.capstone23.users.events.UserCreated;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SelectUserMessageTypeHandlerTest {

    @Inject
    CommandHandler<CreateUser, UserCreated> createUserHandler;

    @Inject
    CommandHandler<SelectUserMessagingType, UserMessagingTypeSelected> selectTypeHandler;

    @Inject
    UserMessageTypesRepository messageTypesRepository;

    @Test
    public void basicTypeTest() {
        var userId = createUser();
        var request = new SelectUserMessagingType();
        request.setCreate(true);
        request.setUserId(userId);
        request.setMessageType(SelectUserMessagingType.CREATE_ORG_MESSAGE);
        var response = selectTypeHandler.handle(request);
        Assertions.assertTrue(response.isSuccess());

        Assertions.assertTrue(messageTypesRepository.listAll().stream().anyMatch(v ->
                v.messageType.equals(SelectUserMessagingType.CREATE_ORG_MESSAGE) && v.userId.toString().equals(userId)));
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
