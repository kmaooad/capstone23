package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.commands.UpdateOrg;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import edu.kmaooad.capstone23.orgs.events.OrgUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateOrgHandlerTest {

    String createdOrgId;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    CommandHandler<UpdateOrg, OrgUpdated> handler;

    @Inject
    CommandHandler<CreateOrg, OrgCreated> createOrgHandler;

    @Inject
    CommandHandler<BanEntity, EntityBanned> banEntityHandler;

    @BeforeEach
    public void setup() {
        orgsRepository.deleteAll();
        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA");
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";
        this.createdOrgId = this.createOrgHandler.handle(command).getValue().getOrgId();
    }

    @Test
    @DisplayName("Update Org: Basic")
    void testSuccessfulHandling() {
        var result = updateOrg();

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

    Result<OrgUpdated> updateOrg() {
        UpdateOrg command = new UpdateOrg();
        command.orgId = this.createdOrgId;
        command.orgName = "KPI";
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";
        return handler.handle(command);
    }

    @Test
    @DisplayName("Update Org with emailDomain: Basic")
    void testSuccessfulHandlingWithEmailDomain() {
        UpdateOrg command = new UpdateOrg();
        command.orgId = this.createdOrgId;
        command.orgName = "KPI";
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";
        command.emailDomain = "gmail.com";

        Assertions.assertTrue(orgsRepository.findByEmailDomainOptional("gmail.com").isEmpty());

        Result<OrgUpdated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals("gmail.com", orgsRepository.findByEmailDomainOptional("gmail.com").get().emailDomain);
    }

    @Test
    @DisplayName("Update Org: Name validation")
    void testNameValidation() {
        UpdateOrg command = new UpdateOrg();
        command.orgId = this.createdOrgId;
        command.orgName = "KPI_";
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";

        Result<OrgUpdated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Update Org: Banned org")
    void testOrgBanned() {
        var banRequest = new BanEntity();
        banRequest.setReason("Hello there");
        banRequest.setEntityId(new ObjectId(createdOrgId));
        banRequest.setEntityType(BannedEntityType.Organization.name());

        var banResult = banEntityHandler.handle(banRequest);

        Assertions.assertTrue(banResult.isSuccess());
        Assertions.assertNotNull(banResult.getValue());

        var updateResult = updateOrg();

        Assertions.assertFalse(updateResult.isSuccess());
        Assertions.assertNotNull(updateResult.toError());
        Assertions.assertEquals(updateResult.getErrorCode(), ErrorCode.EXCEPTION);
        Assertions.assertEquals(updateResult.getMessage(), "Org is banned");

    }
}
