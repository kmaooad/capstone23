package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import edu.kmaooad.capstone23.orgs.commands.RequestToJoinOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.dal.Request;
import edu.kmaooad.capstone23.orgs.dal.RequestsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RequestToJoinOrgHandlerTest {
    @Inject
    CreateRequestToJoinHandler handler;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    RequestsRepository requestsRepository;

    private String orgId;

    @BeforeEach
    void setUp() {
        Org org = new Org();

        org.name = "Initial Organization";
        orgsRepository.insert(org);

        orgId = org.id.toString();
    }

    @Test
    @DisplayName("Create Request to Join Org: Basic")
    public void testBasicRequestToJoinOrg() {
        String userName = "user1";

        RequestToJoinOrg command = new RequestToJoinOrg();
        command.setUserName(userName);
        command.setOrgId(orgId);

        Result<RequestCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        Request resultRequest = requestsRepository.findById(result.getValue().getId());
        Assertions.assertNotNull(resultRequest);
    }
    @Test
    @DisplayName("Create Request to Join Org: Error handling when department is not found")
    public void testRequestToJoinOrgWithNonExistentOrg() {
        String userName = "user1";
        orgId = "64fbb243275c1111167b87a3";

        RequestToJoinOrg command = new RequestToJoinOrg();
        command.setUserName(userName);
        command.setOrgId(orgId);

        Result<RequestCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals("Org not found", result.getMessage());
    }

}
