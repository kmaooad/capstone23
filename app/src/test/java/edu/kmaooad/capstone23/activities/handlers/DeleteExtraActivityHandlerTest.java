package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.CreateExtraActivity;
import edu.kmaooad.capstone23.activities.commands.DeleteExtraActivity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.activities.dal.ExtraActivity;
import edu.kmaooad.capstone23.activities.events.ExtraActivityCreated;
import edu.kmaooad.capstone23.activities.events.ExtraActivityDeleted;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class DeleteExtraActivityHandlerTest {
    private ObjectId idToDelete;


    @Inject
    ActivityRepository courseRepository;

    @Inject
    CommandHandler<DeleteExtraActivity, ExtraActivityDeleted> handler;

    @BeforeEach
    void setUp() {
        ExtraActivity course = new ExtraActivity();
        course.name = "Initial Course";
        courseRepository.insert(course);
        idToDelete = course.id;

    }

    @Test
    public void testDeleteExtraActivitySuccess() {
        DeleteExtraActivity command = new DeleteExtraActivity();
        command.setId(idToDelete);

        Result<ExtraActivityDeleted> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(result.getValue().getCVId(), idToDelete);
    }


    @Test
    public void testDeleteExtraActivityFail() {
        DeleteExtraActivity command = new DeleteExtraActivity();
        command.setId(new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa"));

        Result<ExtraActivityDeleted> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNull(result.getValue());
    }

}