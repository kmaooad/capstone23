package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.commands.IsEntityBanned;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.ban.events.EntityIsBanned;
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
public class IsBannedHandlerTest {

    @Inject
    CommandHandler<BanEntity, EntityBanned> banEntityHandler;

    @Inject
    CommandHandler<CreateOrg, OrgCreated> createOrgHandler;

    @Inject
    CommandHandler<IsEntityBanned, EntityIsBanned> isBannedHandler;

    @Test
    @DisplayName("Is Banned Handler: Basic Test")
    void testSuccessfulHandling() {
        var org = createOrg();

        banOrg(org);


        var isBannedResult = isBannedHandler.handle(new IsEntityBanned(new ObjectId(org), BannedEntityType.Organization.name()));

        Assertions.assertTrue(isBannedResult.isSuccess());
        Assertions.assertNotNull(isBannedResult.getValue());
        Assertions.assertTrue(isBannedResult.getValue().value());

    }

    @Test
    @DisplayName("Is not Banned Handler: Basic Test")
    void testSuccessfulNotBannedHandling() {
        var org = createOrg();

        var isBannedResult = isBannedHandler.handle(new IsEntityBanned(new ObjectId(org), BannedEntityType.Organization.name()));

        Assertions.assertTrue(isBannedResult.isSuccess());
        Assertions.assertNotNull(isBannedResult.getValue());
        Assertions.assertFalse(isBannedResult.getValue().value());

    }

    String createOrg() {
        var command = new CreateOrg();
        command.setOrgName("NaUKMA");
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";

        Result<OrgCreated> result = createOrgHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getOrgId().isEmpty());
        return result.getValue().getOrgId();
    }

    void banOrg(String orgId) {
        var command = new BanEntity();
        command.setEntityType(BannedEntityType.Organization.name());
        command.setEntityId(new ObjectId(orgId));
        command.setReason("Hello there");

        var result = banEntityHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }
}
