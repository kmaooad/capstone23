package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.BulkCreateCourses;
import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.events.BulkCoursesCreated;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class BulkCreateCourseHandlerTest {
    @Inject
    CommandHandler<BulkCreateCourses, BulkCoursesCreated> handler;

    @Test
    @DisplayName("Handle Bulk Create Course command")
    void testSuccessfulCreate() {
        List<String> courseNames = Arrays.asList("Linear algebra", "Physics", "History");
        BulkCreateCourses bulkCreateCourses = generateBulkCourseCreateCommand(courseNames);

        Result<BulkCoursesCreated> result = handler.handle(bulkCreateCourses);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        var createdCourses = result.getValue().getCoursesList();
        Assertions.assertEquals(courseNames.size(), createdCourses.size());

        for (CourseCreated courseCreated:
            createdCourses) {
            Assertions.assertFalse(courseCreated.getId().isEmpty());
        }
    }

    @Test
    @DisplayName("Handle Bulk Create Course validate at least one course")
    void testEmptyListValidation() {
        BulkCreateCourses bulkCreateCourses = new BulkCreateCourses();
        bulkCreateCourses.setCoursesList(new ArrayList<>());

        Result<BulkCoursesCreated> result = handler.handle(bulkCreateCourses);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Handle Bulk Create Course with name validation")
    void testNameValidation() {
        List<String> courseNames = Arrays.asList("Linear algebra", "", "History");
        BulkCreateCourses bulkCreateCourses = generateBulkCourseCreateCommand(courseNames);

        Result<BulkCoursesCreated> result = handler.handle(bulkCreateCourses);

        Assertions.assertFalse(result.isSuccess());
    }

    private BulkCreateCourses generateBulkCourseCreateCommand(List<String> names) {
        List<CreateCourse> createCourses = new ArrayList<>();
        for (String name:
             names) {
            var createCourse = new CreateCourse();
            createCourse.setName(name);
            createCourses.add(createCourse);
        }

        BulkCreateCourses bulkCreateCourses = new BulkCreateCourses();
        bulkCreateCourses.setCoursesList(createCourses);
        return bulkCreateCourses;
    }
}
