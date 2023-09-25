package edu.kmaooad.capstone23.groups.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.commands.RelateGroupToActivity;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
@QuarkusTest
public class RelateGroupToActivityTest {
    @Inject
    CommandHandler<CreateGroup, GroupCreated> handler;
    @Inject
    CommandHandler<RelateGroupToActivity, ActivityRelated> relateHandler;
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
        RelateGroupToActivity relateGroupToActivity = new RelateGroupToActivity();
        relateGroupToActivity.setGroupId(new ObjectId(result.getValue().getGroupId()));
        relateGroupToActivity.setActivityId(courseId);
        Result<ActivityRelated> activityRelatedResult = relateHandler.handle(relateGroupToActivity);

        Assertions.assertTrue(activityRelatedResult.isSuccess());
        Assertions.assertNotNull(activityRelatedResult.getValue());
        Assertions.assertTrue(activityRelatedResult.getValue().getJobId().equals(result.getValue().getGroupId()));


    }
}
