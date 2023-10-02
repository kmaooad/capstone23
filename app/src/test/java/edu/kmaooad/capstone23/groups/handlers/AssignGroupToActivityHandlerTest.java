package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import edu.kmaooad.capstone23.groups.commands.AssignGroupToActivity;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.events.ActivityAssigned;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
@QuarkusTest
public class AssignGroupToActivityHandlerTest {
    @Inject
    CommandHandler<CreateGroup, GroupCreated> handler;
    @Inject
    CommandHandler<AssignGroupToActivity, ActivityAssigned> relateHandler;
    @Inject
    CommandHandler<CreateGroupTemplate, GroupTemplateCreated> templateHandler;
    @Inject
    CourseRepository courseRepository;
    private ObjectId courseId;
    private Result<GroupCreated> result;
    @BeforeEach
    void setUp() {
        Course course = new Course();
        course.name = "Initial Course";
        courseRepository.insert(course);
        courseId = course.id;

        CreateGroupTemplate templateCommand = new CreateGroupTemplate();
        templateCommand.setGroupTemplateName("template");
        Result<GroupTemplateCreated> resultForTemplate = templateHandler.handle(templateCommand);
        CreateGroup commandGroup = new CreateGroup();
        commandGroup.setGroupName("se");
        commandGroup.setTemplateId(resultForTemplate.getValue().getGroupTemplateId().toString());
        result = handler.handle(commandGroup);

    }
    @Test
    void testSuccessfulHandling() {
        AssignGroupToActivity relateGroupToActivity = new AssignGroupToActivity();
        relateGroupToActivity.setGroupId(new ObjectId(result.getValue().getGroupId()));
        relateGroupToActivity.setActivityId(courseId);
        Result<ActivityAssigned> activityRelatedResult = relateHandler.handle(relateGroupToActivity);

        Assertions.assertTrue(activityRelatedResult.isSuccess());
        Assertions.assertNotNull(activityRelatedResult.getValue());
        ObjectId gr = new ObjectId(result.getValue().getGroupId());
        Assertions.assertTrue(activityRelatedResult.getValue().getGroupId().equals(gr));
    }

    @Test
    void testDeclineADublicateRelationHandling() {
        AssignGroupToActivity relateGroupToActivity = new AssignGroupToActivity();
        relateGroupToActivity.setGroupId(new ObjectId(result.getValue().getGroupId()));
        relateGroupToActivity.setActivityId(courseId);
        Result<ActivityAssigned> activityRelatedResult = relateHandler.handle(relateGroupToActivity);

        Assertions.assertTrue(activityRelatedResult.isSuccess());
        Assertions.assertNotNull(activityRelatedResult.getValue());
        ObjectId gr = new ObjectId(result.getValue().getGroupId());
        Assertions.assertEquals(activityRelatedResult.getValue().getGroupId(), gr);

        AssignGroupToActivity relateGroupToActivityTwo = new AssignGroupToActivity();
        relateGroupToActivityTwo.setGroupId(new ObjectId(result.getValue().getGroupId()));
        relateGroupToActivityTwo.setActivityId(courseId);
        Result<ActivityAssigned> activityRelatedResultTwo = relateHandler.handle(relateGroupToActivity);

        Assertions.assertFalse(activityRelatedResultTwo.isSuccess());
        Assertions.assertNull(activityRelatedResultTwo.getValue());
    }

    @Test
    void testHandlingUnExistedCourse() {
        AssignGroupToActivity relateGroupToActivity = new AssignGroupToActivity();
        relateGroupToActivity.setGroupId(new ObjectId(result.getValue().getGroupId()));

        ObjectId nonexistentCourseId = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");
        relateGroupToActivity.setActivityId(nonexistentCourseId);

        Result<ActivityAssigned> activityRelatedResult = relateHandler.handle(relateGroupToActivity);

        Assertions.assertFalse(activityRelatedResult.isSuccess());
        Assertions.assertNull(activityRelatedResult.getValue());
    }
}
