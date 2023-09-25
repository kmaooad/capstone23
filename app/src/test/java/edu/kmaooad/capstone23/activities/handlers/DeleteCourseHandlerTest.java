package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.DeleteCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.CourseDeleted;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;



    @QuarkusTest
    class DeleteCourseHandlerTest {
        @Inject
        DeleteCourseHandler handler;
        @Inject
        CourseRepository repository;

        @Test
        @DisplayName("Basic Handling")
        void testSuccessfulHandling() {
            Course course = createDefaultCourse();
            repository.insert(course);

            DeleteCourse command = new DeleteCourse();
            command.setId(course.id);

            CourseDeleted courseDeleted = handler.handle(command).getValue();
            assertNotNull(courseDeleted);
            assertEquals(course.id, courseDeleted.getCourse());

            Course deletedCourse = repository.findById(course.id);
            assertNull(deletedCourse);
        }

        @Test
        @DisplayName("Delete Non-Existent Course")
        void testDeleteNonExistentCourse() {
            DeleteCourse command = new DeleteCourse();
            command.setId(new ObjectId("5f7e4afc8e1f7112d73c92a1")); // Using a random ObjectId

            Result<?> courseDeleted = handler.handle(command);
            assertFalse(courseDeleted.isSuccess());
        }

        private Course createDefaultCourse() {
            Course course = new Course();
            course.name = "Test Course";
            return course;
        }

    }
