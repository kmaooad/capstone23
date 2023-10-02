package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.BulkCreateCourses;
import edu.kmaooad.capstone23.activities.commands.BulkUpdateCourses;
import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.commands.UpdateCourse;
import edu.kmaooad.capstone23.activities.events.BulkCoursesCreated;
import edu.kmaooad.capstone23.activities.events.BulkCoursesUpdated;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.activities.events.CourseUpdated;
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
public class BulkUpdateCourseHandlerTest {

    @Inject
    CommandHandler<BulkUpdateCourses, BulkCoursesUpdated> handler;
    @Inject
    CommandHandler<BulkCreateCourses, BulkCoursesCreated> createCoursesHandler;

    @Test
    @DisplayName("Handle Bulk Create Course command")
    void testSuccessfulCreate() {
        List<String> initialCourseNames = Arrays.asList("Test1", "Test2", "Test3");
        BulkCreateCourses bulkCreateCourses = generateBulkCourseCreateCommand(initialCourseNames);

        Result<BulkCoursesCreated> createdCourses = createCoursesHandler.handle(bulkCreateCourses);

        List<String> courseNames = Arrays.asList("Linear algebra", "Physics", "History");
        BulkUpdateCourses bulkUpdateCourses = generateBulkUpdateCoursesCommand(courseNames, createdCourses.getValue());
        Result<BulkCoursesUpdated> result = handler.handle(bulkUpdateCourses);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        var updatedCourses = result.getValue().getCoursesList();
        Assertions.assertEquals(courseNames.size(), updatedCourses.size());

        for (CourseUpdated courseUpdated:
            updatedCourses) {
            Assertions.assertTrue(courseNames.contains(courseUpdated.getName()));
        }
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

    private BulkUpdateCourses generateBulkUpdateCoursesCommand(List<String> names, BulkCoursesCreated courseIds) {
        List<UpdateCourse> updateCourses = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            var updateCourse = new UpdateCourse();
            updateCourse.setName(names.get(i));
            updateCourse.setId(courseIds.getCoursesList().get(i).getId());
            updateCourses.add(updateCourse);
        }

        BulkUpdateCourses bulkUpdateCourses = new BulkUpdateCourses();
        bulkUpdateCourses.setCoursesList(updateCourses);
        return bulkUpdateCourses;
    }
}
