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
}
