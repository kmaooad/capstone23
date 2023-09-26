package edu.kmaooad.capstone23.jobs.handlers;
import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.*;
import edu.kmaooad.capstone23.jobs.events.*;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteRelateJobToActivitiesHandlerTest {


    @Inject
    CommandHandler<CreateJob, JobCreated> createJobHandler;
    @Inject
    CommandHandler<CreateCourse, CourseCreated> createSkillHandler;
    @Inject
    CommandHandler<RelateJobToActivities, ActivityRelated> relateHandler;

    @Inject
    CommandHandler<DeleteRelateJobToActivities, ActivityUnrelated> handlerForDel;
    @Test
    void testSuccessfulDeleteHandling(){
        CreateJob command = new CreateJob("TeacherUnique", true);
        Result<JobCreated> result = createJobHandler.handle(command);

        CreateCourse courseCommand = new CreateCourse();
        courseCommand.setName("Java Programming");
        Result<CourseCreated> courseResult = createSkillHandler.handle(courseCommand);

        RelateJobToActivities relateCommand = new RelateJobToActivities();
        relateCommand.setJobId(result.getValue().getJobId());
        ObjectId idCourse = new ObjectId(courseResult.getValue().getId());
        relateCommand.setActivityId(idCourse);
        Result<ActivityRelated> activityRelatedResult = relateHandler.handle(relateCommand);

        DeleteRelateJobToActivities commandForDel = new DeleteRelateJobToActivities();
        commandForDel.setJobId(result.getValue().getJobId());
        commandForDel.setActivityId(idCourse);
        Result<ActivityUnrelated> resultForDel = handlerForDel.handle(commandForDel);

        Assertions.assertTrue(resultForDel.isSuccess());
        Assertions.assertNotNull(resultForDel.getValue());
        Assertions.assertTrue(resultForDel.getValue().getJobId().equals(result.getValue().getJobId()));

    }
}