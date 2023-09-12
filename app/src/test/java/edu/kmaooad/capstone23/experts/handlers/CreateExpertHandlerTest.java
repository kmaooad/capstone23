package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateExpertHandlerTest {
    @Inject
    CommandHandler<CreateExpert, ExpertCreated> expertHandler;
    @Inject
    CommandHandler<CreateOrg, OrgCreated> orgHandler;

    @Test
    public void testSuccessfulHandling() {
        String orgName = "Super Duper Create Team";

        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName(orgName);
        orgHandler.handle(orgCommand);

        CreateExpert command = new CreateExpert();
        command.setExpertName("Arkhypchuk Stepanenko");
        command.setOrgName(orgName);

        Result<ExpertCreated> result = expertHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getExpertId().isEmpty());
    }

    @Test
    public void testNameValidation() {
        String orgName = "Super Duper Mega Create Team";

        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName(orgName);
        orgHandler.handle(orgCommand);

        CreateExpert command = new CreateExpert();
        command.setExpertName("Arkhypchuk Stepanenko œ∑´");
        command.setOrgName(orgName);

        Result<ExpertCreated> result = expertHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
