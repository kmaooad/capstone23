package edu.kmaooad.capstone23.orgs.handlers;

import org.junit.jupiter.api.Assertions;
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
    void testSuccessfulHandling() {
        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA");
        command.setOrgEmail("NaUKMA@gmail.com");

        Result<OrgCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getOrgId().isEmpty());
    }

    @Test
    void testNameValidation() {
        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA_2023");

        Result<OrgCreated> result = handler.handle(command);
        
        Assertions.assertFalse(result.isSuccess());
    }
}
