package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.FindCoursesByTag;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class FindCoursesByTagNameHandlerTest {
    @Inject
    TagRepository tagRepository;
    @Inject
    CourseRepository courseRepository;

    @Inject
    FindCoursesByTagNameHandler coursesByTagNameHandler;

    @BeforeEach
    public void init() {
        courseRepository.deleteAll();
        tagRepository.deleteAll();

        tagRepository.persist(new Tag("tag1"));
        tagRepository.persist(new Tag("tag2"));
        tagRepository.persist(new Tag("tag3"));
    }

    @Test
    public void findOnlyCoursesWithGivenTag() {
        Course course = new Course();
        course.name = "course1";
        course.tags.add(new Tag("tag1"));
        courseRepository.persist(course);
        Course course2 = new Course();
        course2.name = "course2";
        courseRepository.persist(course2);

        FindCoursesByTag command = new FindCoursesByTag();
        command.tagName = "tag1";
        var result = coursesByTagNameHandler.handle(command);
        var courses = result.getValue().courses;
        Assertions.assertEquals(1, courses.size());
    }

    @Test
    public void findsByTagIfCourseHasMultipleTags() {
        Course course = new Course();
        course.name = "course1";
        course.tags.add(new Tag("tag1"));
        course.tags.add(new Tag("tag2"));
        courseRepository.persist(course);
        Course course2 = new Course();
        course2.name = "course2";
        course2.tags.add(new Tag("tag2"));
        courseRepository.persist(course2);

        FindCoursesByTag command = new FindCoursesByTag();
        command.tagName = "tag1";
        var result = coursesByTagNameHandler.handle(command);
        var courses = result.getValue().courses;
        Assertions.assertEquals(1, courses.size());
    }

    @Test
    public void notFoundIfTagNameDoesNotExist() {
        FindCoursesByTag command = new FindCoursesByTag();
        command.tagName = "null";
        var result = coursesByTagNameHandler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }

    @AfterEach
    public void teardown() {
        courseRepository.deleteAll();
        tagRepository.deleteAll();
    }
}
