package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateCourseHandlerTest {
    @Inject
    CommandHandler<CreateCourse, CourseCreated> handler;

    @Test
    @DisplayName("Handle Create Course command")
    void testSuccessfulCreate() {
        var courseName = "Linear algebra";
        CreateCourse command = new CreateCourse();
        command.setName(courseName);

        Result<CourseCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());
    }

    @Test
    @DisplayName("Handle Create Course with validation")
    void testNameValidation() {
        var courseName = "";
        CreateCourse command = new CreateCourse();
        command.setName(courseName);

        Result<CourseCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
