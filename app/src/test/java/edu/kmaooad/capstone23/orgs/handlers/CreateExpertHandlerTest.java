package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateExpertHandlerTest {

    @Inject
    CommandHandler<CreateExpert, ExpertCreated> handler;

    @Test
    void testSuccessfulHandling() {
        CreateExpert command = new CreateExpert();
        command.setExpertName("Arkhypchuk_Stepanenko");

        Result<ExpertCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getExpertId().isEmpty());
    }

    @Test
    void testNameValidation() {
        CreateExpert command = new CreateExpert();
        command.setExpertName("Arkhypchuk_Stepanenko_2");

        Result<ExpertCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
