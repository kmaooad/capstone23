package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.RejectOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgRejected;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RejectOrgHandlerTest {

    private static final String ORG_NAME = "Random Org";
    private static final String ORG_EMAIL = "Random Email";

    @Inject
    CommandHandler<RejectOrg, OrgRejected> handler;

    @Inject
    private OrgsRepository orgsRepository;

    public String prepareRejectOrg(Boolean isActive) {
        final Org org = new Org();
        org.name = ORG_NAME;
        org.email = ORG_EMAIL;
        org.isActive = isActive;
        orgsRepository.insert(org);
        return org.id.toString();
    }

    @Test
    @DisplayName("Reject Org: Basic")
    void testSuccessfulHandlingRejectOrg() {
        final String id = prepareRejectOrg(true);

        RejectOrg command = new RejectOrg();
        command.id = id;
        command.reason = "Not enough money";

        Result<OrgRejected> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(id, result.getValue().id);
    }

    @Test
    @DisplayName("Reject Org: Id Validate")
    void testBadIdHandlingRejectOrg() {
        RejectOrg command = new RejectOrg();
        command.id = "test";
        command.reason = "Not enough money";

        Result<OrgRejected> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.VALIDATION_FAILED);
    }

    @Test
    @DisplayName("Reject Org: Already rejected")
    void testAlreadyRejectHandlingRejectOrg() {
        final String id = prepareRejectOrg(false);
        RejectOrg command = new RejectOrg();
        command.id = id;
        command.reason = "Not enough money";

        Result<OrgRejected> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.VALIDATION_FAILED);
    }
}
