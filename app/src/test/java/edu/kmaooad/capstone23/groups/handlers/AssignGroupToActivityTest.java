package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.groups.commands.AssignGroupToActivity;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.events.ActivityAssigned;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
@QuarkusTest
public class AssignGroupToActivityTest {
    @Inject
    CommandHandler<CreateGroup, GroupCreated> handler;
    @Inject
    CommandHandler<AssignGroupToActivity, ActivityAssigned> relateHandler;
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
        CreateGroup command = new CreateGroup();
        command.setGroupName("se");
        result = handler.handle(command);

    }
    @Test
    void testSuccessfulHandling() {
        AssignGroupToActivity relateGroupToActivity = new AssignGroupToActivity();
        relateGroupToActivity.setGroupId(new ObjectId(result.getValue().getGroupId()));
        relateGroupToActivity.setActivityId(courseId);
        Result<ActivityAssigned> activityRelatedResult = relateHandler.handle(relateGroupToActivity);

        Assertions.assertTrue(activityRelatedResult.isSuccess());
        Assertions.assertNotNull(activityRelatedResult.getValue());
        Assertions.assertTrue(activityRelatedResult.getValue().getGroupId().equals(result.getValue().getGroupId()));


    }
}
