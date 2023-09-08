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

    @Test
    void testSuccessfulHandling() {
        CreateJob command = new CreateJob();
        command.setJobName("handlerTestJobName");

        System.out.println("Before calling handler.handle(command)");

        Result<JobCreated> result = handler.handle(command);

        System.out.println("After calling handler.handle(command)");

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getJobId().isEmpty());
    }

    @Test
    void testNameValidation() {
        CreateJob command = new CreateJob();
        command.setJobName("handler_Invalid_Test_Job_Name");

        Result<JobCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
