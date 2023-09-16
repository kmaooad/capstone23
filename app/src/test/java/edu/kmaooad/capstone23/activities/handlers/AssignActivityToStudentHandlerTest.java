package edu.kmaooad.capstone23.activities.handlers;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;


import edu.kmaooad.capstone23.activities.commands.AssignActivityToStudent;
import edu.kmaooad.capstone23.activities.commands.CreateActivity;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.dal.ActivityRepository;
import edu.kmaooad.capstone23.students.commands.CreateStudent;
import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import edu.kmaooad.capstone23.students.events.StudentCreated;
import edu.kmaooad.capstone23.activities.events.ActivityCreated;
import edu.kmaooad.capstone23.activities.events.AssignActivityToStudentEvent;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

@QuarkusTest
public class AssignActivityToStudentHandlerTest {

    @Inject
    CommandHandler<CreateStudent, StudentCreated> studentHandler;

    @Inject
    CommandHandler<CreateActivity, ActivityCreated> activityHandler;

    @Inject
    CommandHandler<AssignActivityToStudent, AssignActivityToStudentEvent> handler;

    @Test
    @DisplayName("Assign Activity To a Student")
    public void testSuccessfulHandling() {
        AssignActivityToStudent command = new AssignActivityToStudent();
        CreateStudent createStudentCommand = new CreateStudent();
        createStudentCommand.setStudentName("Jane Doe");
        Result<StudentCreated> studentResult = studentHandler.handle(createStudentCommand);

        CreateActivity createActivityCommand = new CreateActivity();
        createActivityCommand.setActivitytName("Matematika");
        Result<ActivityCreated> activityResult = activityHandler.handle(createActivityCommand);

        command.setStudentId(studentResult.getValue().getStudentId());
        command.setActivityId(activityResult.getValue().getId());

        Result<AssignActivityToStudentEvent> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(studentResult.getValue().getStudentId().isEmpty());
    }
}
