package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.BulkDeleteCourses;
import edu.kmaooad.capstone23.activities.commands.DeleteCourse;
import edu.kmaooad.capstone23.activities.events.BulkCoursesDeleted;
import edu.kmaooad.capstone23.activities.events.CourseDeleted;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class BulkDeleteCourseHandlerTest {
    @Inject
    CommandHandler<BulkDeleteCourses, BulkCoursesDeleted> handler;

    @Test
    @DisplayName("Handle Bulk Delete Course command")
    void testSuccessfulDelete() {
        List<String> courseNames = Arrays.asList("Linear algebra", "Physics", "History");
        BulkDeleteCourses bulkDeleteCourses = generateBulkCourseDeleteCommand(courseNames);

        Result<BulkCoursesDeleted> result = handler.handle(bulkDeleteCourses);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        var deletedCourses = result.getValue().getCoursesList();
        Assertions.assertEquals(courseNames.size(), deletedCourses.size());

    }

    @Test
    @DisplayName("Handle Bulk Delete Course validate at least one course")
    void testEmptyListValidation() {
        BulkDeleteCourses bulkDeleteCourses = new BulkDeleteCourses();
        bulkDeleteCourses.setCoursesList(new ArrayList<>());

        Result<BulkCoursesDeleted> result = handler.handle(bulkDeleteCourses);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Handle Bulk Delete Course with name validation")
    void testNameValidation() {
        List<String> courseNames = Arrays.asList("Linear algebra", "", "History");
        BulkDeleteCourses bulkDeleteCourses = generateBulkCourseDeleteCommand(courseNames);

        Result<BulkCoursesDeleted> result = handler.handle(bulkDeleteCourses);

        Assertions.assertFalse(result.isSuccess());
    }

    private BulkDeleteCourses generateBulkCourseDeleteCommand(List<String> names) {
        List<DeleteCourse> createCourses = new ArrayList<>();
        for (Object id:
             names) {
            var createCourse = new DeleteCourse();
            createCourse.setId((ObjectId) id);
            createCourses.add(createCourse);
        }

        BulkDeleteCourses bulkDeleteCourses = new BulkDeleteCourses();
        bulkDeleteCourses.setCoursesList(createCourses);
        return bulkDeleteCourses;
    }
}
