package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.commands.UpdateOrg;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import edu.kmaooad.capstone23.orgs.events.OrgUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
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
        UpdateOrg command = new UpdateOrg();
        command.orgId = this.createdOrgId;
        command.orgName = "KPI";
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";

        Result<OrgUpdated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
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

        Assertions.assertNull(orgsRepository.findByEmailDomain("gmail.com"));

        Result<OrgUpdated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals("gmail.com", orgsRepository.findByEmailDomain("gmail.com").emailDomain);
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
}
