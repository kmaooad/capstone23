package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.commands.DeleteExpert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.experts.events.ExpertDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteExpertHandlerTest {

    @Inject
    CommandHandler<DeleteExpert, ExpertDeleted> deleteHandler;
    @Inject
    CommandHandler<CreateExpert, ExpertCreated> createHandler;
    @Inject
    ExpertsRepository expertsRepository;

    @Test
    public void testSuccessfulHandling() {
        String expertId = createTestExpert();

        DeleteExpert command = new DeleteExpert();
        command.setId(expertsRepository.findByName("Arkhypchuk Stepanenko").id);

        Result<ExpertDeleted> result = deleteHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(result.getValue().getExpertId(), expertId);
    }

    @Test
    public void testInvalidInput() {
        DeleteExpert command = new DeleteExpert();
        command.setId(new ObjectId("64fe000000000a0000000000"));

        Result<ExpertDeleted> result = deleteHandler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), ErrorCode.NOT_FOUND);
    }

    private String createTestExpert() {
        CreateExpert command = new CreateExpert();
        command.setExpertName("Arkhypchuk Stepanenko");

        Result<ExpertCreated> result = createHandler.handle(command);

        return result.getValue().getExpertId();
    }
}
