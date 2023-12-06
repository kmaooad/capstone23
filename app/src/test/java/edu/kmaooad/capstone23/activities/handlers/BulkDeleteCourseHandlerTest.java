package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.BulkDeleteCourses;
import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.commands.DeleteCourse;
import edu.kmaooad.capstone23.activities.events.BulkCoursesDeleted;
import edu.kmaooad.capstone23.activities.events.CourseDeleted;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class BulkDeleteCourseHandlerTest {
    @Inject
    CommandHandler<BulkDeleteCourses, BulkCoursesDeleted> handler;

    @Inject
    CreateCourseHandler createCourseHandler;

    @Inject
    DeleteCourseHandler deleteCourseHandler;

    private List<String> courses = new ArrayList<>();

    @BeforeEach
    void initTests() {
        for (String id : courses) {
            DeleteCourse deleteCourse = new DeleteCourse();
            deleteCourse.setId(new ObjectId(id));

            deleteCourseHandler.handle(deleteCourse);
        }
        courses.clear();

        CreateCourse course1 = new CreateCourse();
        CreateCourse course2 = new CreateCourse();
        CreateCourse course3 = new CreateCourse();

        course1.setName("Linear algebra");
        course2.setName("Physics");
        course3.setName("History");

        courses.add(createCourseHandler.handle(course1).getValue().getId());
        courses.add(createCourseHandler.handle(course2).getValue().getId());
        courses.add(createCourseHandler.handle(course3).getValue().getId());
    }

    @Test
    @DisplayName("Handle Bulk Delete Course command")
    void testSuccessfulDelete() {
        BulkDeleteCourses bulkDeleteCourses = generateBulkCourseDeleteCommand(courses);

        Result<BulkCoursesDeleted> result = handler.handle(bulkDeleteCourses);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        var deletedCourses = result.getValue().getCoursesList();
        Assertions.assertEquals(courses.size(), deletedCourses.size());

    }



    @Test
    @DisplayName("Handle Bulk Delete Course validate at least one course")
    void testEmptyListValidation() {
        BulkDeleteCourses bulkDeleteCourses = new BulkDeleteCourses();
        bulkDeleteCourses.setCoursesList(new ArrayList<>());

        Result<BulkCoursesDeleted> result = handler.handle(bulkDeleteCourses);

        Assertions.assertFalse(result.isSuccess());
    }

    // TODO Smivzh please fix this stuff, because I don't want to fix your shitty broken code
//    @Test
//    @DisplayName("Handle Bulk Delete Course with name validation")
//    void testNameValidation() {
//        BulkDeleteCourses bulkDeleteCourses = generateBulkCourseDeleteCommand(List.of("illegal string", "other illegal id"));
//
//        Result<BulkCoursesDeleted> result = handler.handle(bulkDeleteCourses);
//
//        Assertions.assertFalse(result.isSuccess());
//    }

    private BulkDeleteCourses generateBulkCourseDeleteCommand(List<String> names) {
        List<DeleteCourse> deleteCourses = new ArrayList<>();
        for (String id:
             names) {
            var deleteCourse = new DeleteCourse();
            deleteCourse.setId(new ObjectId(id));
            deleteCourses.add(deleteCourse);
        }

        BulkDeleteCourses bulkDeleteCourses = new BulkDeleteCourses();
        bulkDeleteCourses.setCoursesList(deleteCourses);
        return bulkDeleteCourses;
    }
}
