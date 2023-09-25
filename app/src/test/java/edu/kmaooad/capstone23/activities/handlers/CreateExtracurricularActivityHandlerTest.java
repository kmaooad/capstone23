package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.CreateExtracurricularActivity;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateExtracurricularActivityHandlerTest {
    @Inject
    CommandHandler<CreateExtracurricularActivity, ExtracurricularActivityCreated> handler;

    @Test
    @DisplayName("Handle Create Extracurricular Activity command")
    void testSuccessfulCreate() {
        var activityName = "Smoking";
        CreateExtracurricularActivity command = new CreateExtracurricularActivity();
        command.setExtracurricularActivityName(activityName);
        Result<ExtracurricularActivityCreated> result = handler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getExtracurricularActivityId().isEmpty());
    }

    @Test
    @DisplayName("Handle Create Extracurricular Activity with validation")
    void testNameValidation() {
        var courseName = "";
        CreateExtracurricularActivity command = new CreateExtracurricularActivity();
        command.setExtracurricularActivityName(courseName);
        Result<ExtracurricularActivityCreated> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }
}
