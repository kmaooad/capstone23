package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.UpdateExtraActivity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import edu.kmaooad.capstone23.activities.events.ExtraActivityUpdated;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
class UpdateExtraActivityHandlerTest {
    private ObjectId idToUpdate;


    @Inject
    UpdateExtraActivityHandler updateExtraActivityHandler;
    @Inject
    ActivityRepository activityRepository;

    @BeforeEach
    void setUp() {
        ExtraActivity activity = new ExtraActivity();
        activity.name = "Activity";
        activityRepository.insert(activity);
        idToUpdate = activity.id;
    }

    @Test
    @DisplayName("Update Activity: Basic")
    void testSuccessfulBasicHandling() {
        UpdateExtraActivity command = new UpdateExtraActivity();
        command.setId(idToUpdate);
        command.setName("Updated Course");

        Result<ExtraActivityUpdated> result = updateExtraActivityHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertNotNull(result.getValue().getCVId());
        Assertions.assertEquals(result.getValue().getCVId(), idToUpdate);
    }


    @Test
    @DisplayName("Update Activity: Basic not exist activity")
    void testNonExistent() {
        UpdateExtraActivity command = new UpdateExtraActivity();
        command.setId(new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa"));
        command.setName("Updated activity");

        Result<ExtraActivityUpdated> result = updateExtraActivityHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }
}