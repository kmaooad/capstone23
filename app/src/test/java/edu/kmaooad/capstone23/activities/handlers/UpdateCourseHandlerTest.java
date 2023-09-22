package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.UpdateCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.activities.events.CourseUpdated;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@QuarkusTest
public class UpdateCourseHandlerTest {
    @Inject
    CourseRepository courseRepository;

    @BeforeEach
    void deleteAllData() {
        courseRepository.deleteAll();
    }

    private ObjectId idToUpdate;

    @Inject
    UpdateCourseHandler updateCourseHandler;

    @BeforeEach
    void setUp() {
        Course course = new Course();
        course.name = "Initial Course";
        courseRepository.insert(course);
        idToUpdate = course.id;
    }

    @Test
    @DisplayName("Update Course: Basic")
    void testSuccessfulBasicHandling() {
        UpdateCourse command = new UpdateCourse();
        command.setId(idToUpdate.toString());
        command.setName("Updated Course");

        Result<CourseUpdated> result = updateCourseHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());
        Assertions.assertFalse(result.getValue().getName().isEmpty());

        Optional<Course> updatedCourseOptional = courseRepository.findById(idToUpdate.toString());
        Assertions.assertTrue(updatedCourseOptional.isPresent());

        Course updatedCourse = updatedCourseOptional.get();
        Assertions.assertEquals("Updated Course", updatedCourse.name);
    }

    @Test
    @DisplayName("Update Course")
    void testSuccessfulHandling() {
        UpdateCourse command = new UpdateCourse();
        command.setId(idToUpdate.toString());
        command.setName("Updated Course");

        Result<CourseUpdated> result = updateCourseHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());
        Assertions.assertFalse(result.getValue().getName().isEmpty());

        Optional<Course> updatedCourseOptional = courseRepository.findById(idToUpdate.toString());
        Assertions.assertTrue(updatedCourseOptional.isPresent());

        Course updatedCourse = updatedCourseOptional.get();
        Assertions.assertEquals("Updated Course", updatedCourse.name);
    }


    @Test
    @DisplayName("Update Course: Basic not exist course")
    void testNonExistentCourse() {
        UpdateCourse command = new UpdateCourse();
        command.setId("nonExistentId");
        command.setName("Updated Course");

        Result<CourseUpdated> result = updateCourseHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
        Assertions.assertEquals("Course with this ID does not exist", result.getMessage());
    }

}