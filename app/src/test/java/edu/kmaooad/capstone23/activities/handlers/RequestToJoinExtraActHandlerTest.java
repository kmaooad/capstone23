package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.RequestToJoinExtraAct;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import edu.kmaooad.capstone23.activities.dal.Request;
import edu.kmaooad.capstone23.activities.dal.RequestsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RequestToJoinExtraActHandlerTest {
    @Inject
    CreateRequestToJoinExtraActHandler handler;

    @Inject
    ExtracurricularActivityRepository extraActRepository;

    @Inject
    RequestsRepository requestsRepository;

    private String extraActId;

    @BeforeEach
    void setUp() {
        ExtracurricularActivity extraAct = new ExtracurricularActivity();

        extraAct.extracurricularActivityName = "Initial Activity";
        extraActRepository.insert(extraAct);

        extraActId = extraAct.id.toString();
    }
  
    @Test
    @DisplayName("Create Request to Join Activity: Basic")
    public void testBasicRequestToJoinActivity() {
        String userName = "person1";

        RequestToJoinExtraAct command = new RequestToJoinExtraAct();
        command.setUserName(userName);
        command.setExtraActId(extraActId);

        Result<RequestCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        Request resultRequest = requestsRepository.findById(result.getValue().getId());
        Assertions.assertNotNull(resultRequest);
    }

    @Test
    @DisplayName("Create Request to Join Activity: Error handling when activity is not found")
    public void testRequestToJoinOrgWithNonExistentActivity() {
        String userName = "person1";
        extraActId = "64fbb243275c1111167b87a3";

        RequestToJoinExtraAct command = new RequestToJoinExtraAct();
        command.setUserName(userName);
        command.setExtraActId(extraActId);

        Result<RequestCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals("Activity not found", result.getMessage());
    }

    @Test
    @DisplayName("Create Request to Join Activity: Error handling when user is already part of the activity")
    public void testRequestToJoinActivityWithExistingUser() {
        String userName = "person1";

        RequestToJoinExtraAct initialCommand = new RequestToJoinExtraAct();
        initialCommand.setUserName(userName);
        initialCommand.setExtraActId(extraActId);
        Result<RequestCreated> initialResult = handler.handle(initialCommand);
        Assertions.assertTrue(initialResult.isSuccess());

        RequestToJoinExtraAct command = new RequestToJoinExtraAct();
        command.setUserName(userName);
        command.setExtraActId(extraActId);
        Result<RequestCreated> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals("User is already part of the activity", result.getMessage());
    }

    @Test
    @DisplayName("Create Request to Join Activity: Error handling when user sends multiple requests for the same activity")
    public void testMultipleRequestsToJoinActivity() {
        String userName = "person2";

        RequestToJoinExtraAct initialCommand = new RequestToJoinExtraAct();
        initialCommand.setUserName(userName);
        initialCommand.setExtraActId(extraActId);
        Result<RequestCreated> initialResult = handler.handle(initialCommand);
        Assertions.assertTrue(initialResult.isSuccess());

        RequestToJoinExtraAct secondCommand = new RequestToJoinExtraAct();
        secondCommand.setUserName(userName);
        secondCommand.setExtraActId(extraActId);
        Result<RequestCreated> secondResult = handler.handle(secondCommand);
        Assertions.assertFalse(secondResult.isSuccess());
        Assertions.assertEquals("User is already part of the activity", secondResult.getMessage());
    }

}
