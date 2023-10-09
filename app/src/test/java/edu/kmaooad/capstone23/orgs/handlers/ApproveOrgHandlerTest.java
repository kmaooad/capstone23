package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.ApproveOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgApproved;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ApproveOrgHandlerTest {

    private static final String testOrg_name = "undefined";
    private static final String testOrg_email = "undefined@ukr.net";

    @Inject
    CommandHandler<ApproveOrg, OrgApproved> handler;

    @Inject
    CommandHandler<BanEntity, EntityBanned> banHandler;

    @Inject
    private OrgsRepository orgsRepo;


    public String testData(Boolean isActive) {
        final Org org = new Org();
        org.name = testOrg_name;
        org.isActive = isActive;
        orgsRepo.insert(org);
        return org.id.toString();
    }

    @Test
    @DisplayName("Approve Org: Basic")
    void testSuccessfulApproveOrg() {
        final String id = testData(false);

        ApproveOrg command = new ApproveOrg();
        command.setOrgId(id);
        command.setOrgEmail(testOrg_email);

        Result<OrgApproved> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(id, result.getValue().getOrgId());
    }

    @Test
    @DisplayName("Approve Org: Id Validate")
    void testBadIdHandlingApproveOrg() {
        ApproveOrg command = new ApproveOrg();
        command.setOrgId("wrong");
        command.setOrgEmail(testOrg_email);

        Result<OrgApproved> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.VALIDATION_FAILED);
    }

    @Test
    @DisplayName("Approve Org: Already approved")
    void testAlreadyRejectHandlingRejectOrg() {
        final String id = testData(true);
        ApproveOrg command = new ApproveOrg();
        command.setOrgId(id);
        command.setOrgEmail(testOrg_email);

        Result<OrgApproved> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.VALIDATION_FAILED);
    }

    @Test
    @DisplayName("Approve Org: Org is banned")
    void testOrgIsBannedRejectOrg() {
        final String id = testData(false);

        BanEntity banRequest = new BanEntity();
        banRequest.setEntityId(new ObjectId(id));
        banRequest.setEntityType(BannedEntityType.Organization.name());
        banRequest.setReason("Hello there");

        var banResult = banHandler.handle(banRequest);

        Assertions.assertTrue(banResult.isSuccess());
        Assertions.assertNotNull(banResult.getValue());

        ApproveOrg command = new ApproveOrg();
        command.setOrgId(id);
        command.setOrgEmail(testOrg_email);

        Result<OrgApproved> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNotNull(result.getMessage());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.EXCEPTION);
        Assertions.assertEquals(result.getMessage(), "Org is banned");
    }
}
