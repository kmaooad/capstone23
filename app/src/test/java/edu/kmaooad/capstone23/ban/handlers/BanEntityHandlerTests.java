package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class BanEntityHandlerTests {

    @Inject
    CommandHandler<BanEntity, EntityBanned> banEntityHandler;

    @Inject
    CommandHandler<CreateOrg, OrgCreated> createOrgHandler;

    @Test
    @DisplayName("Ban Org Handler: Basic Test")
    void testSuccessfulHandling() {
        var orgId = createOrg();
        var command = new BanEntity();
        command.setEntityType(BannedEntityType.Organization.toString());
        command.setEntityId(new ObjectId(orgId));
        command.setReason("Hello there");

        var result = banEntityHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

    @Test
    @DisplayName("Ban Org Handler: Org doesn't exist test")
    void testUnsuccessfulHandling() {
        var command = new BanEntity();
        command.setEntityType(BannedEntityType.Organization.toString());
        command.setEntityId(new ObjectId());
        command.setReason("Hello there");

        var result = banEntityHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNotNull(result.toError());
    }

    String createOrg() {
        var command = new CreateOrg();
        command.setOrgName("NaUKMA");

        Result<OrgCreated> result = createOrgHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getOrgId().isEmpty());
        return result.getValue().getOrgId();
    }
}
