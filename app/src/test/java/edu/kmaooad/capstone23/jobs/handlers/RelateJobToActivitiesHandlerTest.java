package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.DeleteJob;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.jobs.events.JobDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RelateJobToActivitiesHandlerTest {
    @Inject
    CommandHandler<CreateJob, JobCreated> handler;
    @Inject
    CommandHandler<RelateJobToActivities, ActivityRelated> relateHandler;
    @Inject
    CourseRepository courseRepository;
    private ObjectId courseId;
    private  Result<JobCreated> result;
    @BeforeEach
    void setUp() {
        Course course = new Course();
        course.name = "Initial Course";
        courseRepository.insert(course);
        courseId = course.id;
        CreateJob command = new CreateJob("IT teacher", true);
        result = handler.handle(command);

    }
    @Test
    void testSuccessfulHandling() {
        RelateJobToActivities relateJobToActivities = new RelateJobToActivities();
        relateJobToActivities.setJobId(result.getValue().getJobId());
        relateJobToActivities.setActivityId(courseId);
        Result<ActivityRelated> activityRelatedResult = relateHandler.handle(relateJobToActivities);

        Assertions.assertTrue(activityRelatedResult.isSuccess());
        Assertions.assertNotNull(activityRelatedResult.getValue());
        Assertions.assertTrue(activityRelatedResult.getValue().getJobId().equals(result.getValue().getJobId()));


    }
}
