package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.commands.UpdateExpert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.experts.events.ExpertUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateExpertHandlerTest {
    @Inject
    CommandHandler<UpdateExpert, ExpertUpdated> updateHandler;
    @Inject
    CommandHandler<CreateExpert, ExpertCreated> createHandler;
    @Inject
    ExpertsRepository expertsRepository;

    @Test
    public void testSuccessfulHandling() {
        String expertId = createTestExpert();
        String name = "Kvasolia Barabolia";

        UpdateExpert command = new UpdateExpert();
        command.setId(expertsRepository.findByName("Redyska Sosyska").id);
        command.setExpertName(name);

        Result<ExpertUpdated> result = updateHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(result.getValue().getExpertId().id.toString(), expertId);
        Assertions.assertEquals(result.getValue().getExpertId().name, name);

        expertsRepository.deleteExpert(expertsRepository.findByName(name));
    }

    private String createTestExpert() {
        CreateExpert command = new CreateExpert();
        command.setExpertName("Redyska Sosyska");

        Result<ExpertCreated> result = createHandler.handle(command);

        return result.getValue().getExpertId();
    }
}
