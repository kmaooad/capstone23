package edu.kmaooad.capstone23.orgs.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.common.*;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class CreateOrgHandlerTest {

    @Inject
    CommandHandler<CreateOrg, OrgCreated> handler;

    @Test
    @DisplayName("Create Org: Basic")
    void testSuccessfulHandling() {
        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA");
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";

        Result<OrgCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getOrgId().isEmpty());
    }

    @Test
    @DisplayName("Create Org: Name validation")
    void testNameValidation() {
        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA_2023");
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";

        Result<OrgCreated> result = handler.handle(command);
        
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Create Org: Website validation")
    void testWebsiteValidation() {
        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA");
        command.industry = "Education";
        command.website = "";

        Result<OrgCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
