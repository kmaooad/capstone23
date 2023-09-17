package edu.kmaooad.capstone23.activities.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import edu.kmaooad.capstone23.activities.commands.CreateActivity;
import edu.kmaooad.capstone23.activities.commands.UnassignActivityFromStudent;
import edu.kmaooad.capstone23.activities.events.ActivityCreated;
import edu.kmaooad.capstone23.activities.events.UnassignActivityFromStudentEvent;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.events.StudentCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class UnassignActivityFromStudentHandlerTest {
        @Inject
    CommandHandler<CreateStudent, StudentCreated> studentHandler;

    @Inject
    CommandHandler<CreateActivity, ActivityCreated> activityHandler;

    @Inject
    CommandHandler<UnassignActivityFromStudent, UnassignActivityFromStudentEvent> handler;

    @Test
    @DisplayName("Unassign Activity To a Group")
    public void testSuccessfulGroupHandling() {
        UnassignActivityFromStudent command = new UnassignActivityFromStudent();
        CreateStudent studentCommand = new CreateStudent();
        studentCommand.setStudentName("testGroup");

        Result<StudentCreated> studentResult = studentHandler.handle(studentCommand);

        CreateActivity createActivityCommand = new CreateActivity();
        createActivityCommand.setActivitytName("Matematika");
        Result<ActivityCreated> activityResult = activityHandler.handle(createActivityCommand);

        command.setStudentId(studentResult.getValue().getStudentId());
        command.setActivityId(activityResult.getValue().getId());

        Result<UnassignActivityFromStudentEvent> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(studentResult.getValue().getStudentId().isEmpty());
    }

    @Test
    @DisplayName("Unassign Activity To a Group")
    public void testUnsuccessfulGroupHandling() {
        UnassignActivityFromStudent command = new UnassignActivityFromStudent();
        CreateStudent studentCommand = new CreateStudent();
        studentCommand.setStudentName("testGroupB");

        Result<StudentCreated> studentResult = studentHandler.handle(studentCommand);

        CreateActivity createActivityCommand = new CreateActivity();
        createActivityCommand.setActivitytName("Germany");
        Result<ActivityCreated> activityResult = activityHandler.handle(createActivityCommand);

        command.setStudentId(studentResult.getValue().getStudentId());
        command.setActivityId(activityResult.getValue().getId());

        Result<UnassignActivityFromStudentEvent> result = handler.handle(command);

        Assertions.assertFalse(command.getActivityId().isEmpty());
        Assertions.assertFalse(command.getStudentId().isEmpty());
    }
}
