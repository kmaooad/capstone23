package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.DeleteExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityDeleted;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

@QuarkusTest
public class DeleteExtracurricularActivityHandlerTest {
    private String activityId;

    @Inject
    DeleteExtracurricularActivityHandler handler;

    @Inject
    ExtracurricularActivityRepository extracurricularActivityRepository;

    @BeforeEach
    void setUp() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();
        extracurricularActivity.extracurricularActivityName = "Football to Delete";
        extracurricularActivity.extracurricularActivityDate = new Date();
        extracurricularActivityRepository.insert(extracurricularActivity);

        activityId = extracurricularActivity.id.toString();
    }

    @Test
    @DisplayName("Delete Extracurricular Activity: Successful handling")
    void testSuccessfulHandling() {
        DeleteExtracurricularActivity command = new DeleteExtracurricularActivity();
        command.setId(activityId);

        Result<ExtracurricularActivityDeleted> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(activityId, result.getValue().getId());

        ExtracurricularActivity deletedExtracurricularActivity = extracurricularActivityRepository.findById(activityId);
        Assertions.assertNull(deletedExtracurricularActivity);
    }

    @Test
    @DisplayName("Delete Extracurricular Activity: Handle non-existent department")
    void testHandleWithNonExistentId() {
        String nonexistentId = "64fbb243275c1111167b87a3";

        DeleteExtracurricularActivity command = new DeleteExtracurricularActivity();
        command.setId(nonexistentId);

        Result<ExtracurricularActivityDeleted> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.EXCEPTION, result.getErrorCode());
        Assertions.assertEquals("Extracurricular Activity not found", result.getMessage());
    }
}
