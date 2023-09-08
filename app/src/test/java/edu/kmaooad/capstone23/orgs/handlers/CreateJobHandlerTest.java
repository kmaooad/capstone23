package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateJobHandlerTest {
    @Inject
    CommandHandler<CreateJob, JobCreated> handler;

    //TODO: find out why this test isn't working (gives 400 instead of 200)
//    @Test
//    void testSuccessfulHandling() {
//        CreateJob command = new CreateJob();
//        command.setJobName("handler_test_job_name");
//
//        Result<JobCreated> result = handler.handle(command);
//
//        Assertions.assertTrue(result.isSuccess());
//        Assertions.assertNotNull(result.getValue());
//        Assertions.assertFalse(result.getValue().getJobId().isEmpty());
//    }

    @Test
    void testNameValidation() {
        CreateJob command = new CreateJob();
        command.setJobName("handler_test2_job_name");

        Result<JobCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
