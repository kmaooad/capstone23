package edu.kmaooad.capstone23.orgs.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateJobHandlerTest {
    @Inject
    CommandHandler<CreateJob, JobCreated> handler;

    @Test
    void testSuccessfulHandling() {
        CreateJob command = new CreateJob(new ObjectId("1"), "IT teacher", true);

        Result<JobCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getJobId().isEmpty());
    }

    @Test
    void testNameValidation() {
        CreateJob command = new CreateJob(new ObjectId("2"), "Math_teacher", true);

        Result<JobCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
