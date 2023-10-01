package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.AddTagToCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class AddTagToCourseTest {

    @Inject
    TagRepository tagRepository;
    @Inject
    CourseRepository courseRepository;

    @Inject
    AddTagToCourseHandler addTagToCourseHandler;

    @BeforeEach
    public void setup() {
        var course = new Course();
        course.name = "Test_Course";
        course.tags = new ArrayList<>();
        courseRepository.insert(course);

        var tag = new Tag();
        tag.tagName = "Test_Tag";
        tagRepository.persist(tag);
    }

    @Test
    void testAddTagToCourse() {
        var addTagToCourse = new AddTagToCourse();
        addTagToCourse.setCourseName("Test_Course");
        addTagToCourse.setTagName("Test_Tag");
        var result = addTagToCourseHandler.handle(addTagToCourse);
        assertTrue(result.isSuccess());
        assertEquals(addTagToCourse.getCourseName(), result.getValue().getCourseName());
        assertEquals(addTagToCourse.getTagName(), result.getValue().getTagName());
    }

    @Test
    @DisplayName("Course not exists")
    void testAddTagToCourse2() {
        var addTagToCourse = new AddTagToCourse();
        addTagToCourse.setCourseName("Test_Course1");
        addTagToCourse.setTagName("Test_Tag");
        var result = addTagToCourseHandler.handle(addTagToCourse);
        assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Tag not exists")
    void testAddTagToCourse3() {
        var addTagToCourse = new AddTagToCourse();
        addTagToCourse.setCourseName("Test_Course");
        addTagToCourse.setTagName("Test_Tag1");
        var result = addTagToCourseHandler.handle(addTagToCourse);
        assertFalse(result.isSuccess());
    }

    @AfterEach
    public void teardown() {
        courseRepository.deleteAll();
        tagRepository.deleteAll();
    }


}
