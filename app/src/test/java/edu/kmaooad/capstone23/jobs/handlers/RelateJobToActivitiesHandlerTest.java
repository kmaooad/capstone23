package edu.kmaooad.capstone23.jobs.handlers;

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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RelateJobToActivitiesHandlerTest {
    @Inject
    CommandHandler<CreateJob, JobCreated> handler;
    @Inject
    CommandHandler<RelateJobToActivities, ActivityRelated> relateHandler;
    @Test
    void testSuccessfulHandling() {
        CreateJob command = new CreateJob("IT teacher", true);
        Result<JobCreated> result = handler.handle(command);

    }
}
