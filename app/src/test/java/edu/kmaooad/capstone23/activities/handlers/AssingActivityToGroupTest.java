package edu.kmaooad.capstone23.activities.handlers;


import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.activities.commands.AssignActivityToStudent;
import edu.kmaooad.capstone23.activities.dal.Activity;
import edu.kmaooad.capstone23.activities.events.AssignActivityToStudentEvent;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.students.dal.Student;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class AssingActivityToStudentTest {
    private AssignActivityToStudentHandler handler = new AssignActivityToStudentHandler();

    @Test
    @DisplayName("Test assign activity to student")
    void testSuccessfulCreate() {
        Student student = new Student("Jane Doe");
        Activity activity = new Activity("Math");
        StudentRepositoryMock studentRepository = new StudentRepositoryMock();
        ActivityRepositoryMock activityRepository = new ActivityRepositoryMock();
        activityRepository.insert(activity);
        studentRepository.insert(student);

        handler.activityRepository = activityRepository;
        handler.studentRepository = studentRepository;

        AssignActivityToStudent command = new AssignActivityToStudent();
        command.setActivityId(activity.objectId);
        command.setStudentId(student.objectId);

        Result<AssignActivityToStudentEvent> result =  handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertTrue(student.getAssignedActivities().contains(activity));
    }
}   

class StudentRepositoryMock implements PanacheMongoRepository<Student> {
    private final List<Student> students = new ArrayList<>();

    public Student findByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }

        return null;
    }

    public Student insert(Student student) {
        students.add(student);

        return student;
    }
}

class ActivityRepositoryMock implements PanacheMongoRepository<Activity> {
    private final List<Activity> activities = new ArrayList<>();

    public Activity findByName(String name) {
        for (Activity activity : activities) {
            if (activity.getName().equals(name)) {
                return activity;
            }
        }

        return null;
    }

    public Activity insert(Activity activity) {
        activities.add(activity);

        return activity;
    }
}