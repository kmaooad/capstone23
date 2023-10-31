package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.UpdateExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityUpdated;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@QuarkusTest
public class UpdateExtracurricularActivityHandlerTest {
    private String idToUpdate;

    @Inject
    UpdateExtracurricularActivityHandler UpdateExtracurricularActivityHandler;
    @Inject
    ExtracurricularActivityRepository repository;

    @BeforeEach
    void setUp() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();
        extracurricularActivity.extracurricularActivityDate = new Date();
        extracurricularActivity.extracurricularActivityName = "Basketball";
        repository.insert(extracurricularActivity);
        idToUpdate = extracurricularActivity.id.toString();
    }

    @Test
    @DisplayName("Update Activity")
    void testSuccessfulHandling() {
        UpdateExtracurricularActivity command = new UpdateExtracurricularActivity();
        command.setId(idToUpdate);
        command.setActivityName("Updated Activity");
        command.setExtracurricularActivityDate(new Date(2323223232L));
        Result<ExtracurricularActivityUpdated> result = UpdateExtracurricularActivityHandler.handle(command);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(Objects.isNull(result.getValue().getExtracurricularActivityDate()));
        Assertions.assertFalse(result.getValue().getName().isEmpty());
        ExtracurricularActivity updatedActivity = repository.findById(idToUpdate);
        Assertions.assertTrue(Objects.nonNull(updatedActivity));
        Assertions.assertEquals("Updated Activity", updatedActivity.extracurricularActivityName);
    }

    @Test
    @DisplayName("Update Activity: Basic not exist activity")
    void testNonExistentActivity() {
        UpdateExtracurricularActivity command = new UpdateExtracurricularActivity();
        command.setId("nonExistentId");
        command.setActivityName("Updated Activity");

        Result<ExtracurricularActivityUpdated> result = UpdateExtracurricularActivityHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }
}
