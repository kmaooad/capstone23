package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import edu.kmaooad.capstone23.orgs.commands.RequestToJoinOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.dal.Request;
import edu.kmaooad.capstone23.orgs.dal.RequestsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
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

    @Inject
    CommandHandler<BanEntity, EntityBanned> banHandler;

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

    @Test
    @DisplayName("Create Request to Join Org: Error with ban on Org")
    public void testRequestToJoinOrgWithBan() {
        BanEntity banRequest = new BanEntity();
        banRequest.setEntityType(BannedEntityType.Organization.name());
        banRequest.setReason("Hello there");
        banRequest.setEntityId(new ObjectId(orgId));
        var banResult = banHandler.handle(banRequest);
        Assertions.assertTrue(banResult.isSuccess());

        String userName = "user1";

        RequestToJoinOrg command = new RequestToJoinOrg();
        command.setUserName(userName);
        command.setOrgId(orgId);

        Result<RequestCreated> createResult = handler.handle(command);

        Assertions.assertFalse(createResult.isSuccess());
        Assertions.assertNotNull(createResult.getMessage());
        Assertions.assertEquals(createResult.getErrorCode(), ErrorCode.EXCEPTION);
        Assertions.assertEquals(createResult.getMessage(), "Org is banned");
    }
}
