package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.commands.UpdateExpert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.experts.events.ExpertUpdated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateExpertHandlerTest {
    private Org org;
    @Inject
    CommandHandler<UpdateExpert, ExpertUpdated> updateHandler;
    @Inject
    CommandHandler<CreateExpert, ExpertCreated> createHandler;
    @Inject
    CommandHandler<CreateOrg, OrgCreated> orgHandler;
    @Inject
    ExpertsRepository expertsRepository;

    @Test
    public void testSuccessfulHandling() {
        String expertId = createTestExpert();
        String name = "Kvasolia Barabolia";

        UpdateExpert command = new UpdateExpert();
        command.setId(expertsRepository.findByName("Redyska Sosyska").id.toString());
        command.setExpertName(name);
        command.setOrgId(org.id.toString());
        command.setOrgName(org.name);

        Result<ExpertUpdated> result = updateHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(result.getValue().getExpert().id.toString(), expertId);
        Assertions.assertEquals(result.getValue().getExpert().name, name);
    }

    private String createTestExpert() {
        String orgName = "Super Duper Update Team";

        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName(orgName);
        orgHandler.handle(orgCommand);

        CreateExpert command = new CreateExpert();
        command.setExpertName("Redyska Sosyska");
        command.setOrgName(orgName);

        Result<ExpertCreated> result = createHandler.handle(command);

        org = result.getValue().getOrg();

        return result.getValue().getExpertId();
    }
}
