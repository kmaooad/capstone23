package edu.kmaooad.capstone23.orgs.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.ActivateJob;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.DeactivateJob;
import edu.kmaooad.capstone23.jobs.events.JobActivated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.jobs.events.JobDeactivated;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
@QuarkusTest
public class DeactivateJobHandlerTest {
    @Inject
    CommandHandler<CreateJob, JobCreated> handler;
    @Inject
    CommandHandler<DeactivateJob, JobDeactivated> handlerForDeactivation;
    @Test
    void testSuccessfulHandling() {
//        Map<String, Object> jsonAsMap = new HashMap<>();
//        ObjectId id = new ObjectId("64faf6ad341e202c91f76c83");
//        jsonAsMap.put("_id", id);

        CreateJob command = new CreateJob("IT teacher", true);

        Result<JobCreated> result = handler.handle(command);

        DeactivateJob commandForDeact = new DeactivateJob();
        commandForDeact.setJobId(result.getValue().getJobId());

        Result<JobDeactivated> resultForDeact = handlerForDeactivation.handle(commandForDeact);

        Assertions.assertTrue(resultForDeact.isSuccess());
        Assertions.assertNotNull(resultForDeact.getValue());
        Assertions.assertTrue(resultForDeact.getValue().getJobId().equals(result.getValue().getJobId()));
    }

    @Test
    void testUnsuccessfulHandling() {

        ObjectId nonExistingJob = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");

        DeactivateJob commandForDeact = new DeactivateJob();
        commandForDeact.setJobId(nonExistingJob);

        Result<JobDeactivated> resultForDeact = handlerForDeactivation.handle(commandForDeact);

        Assertions.assertFalse(resultForDeact.isSuccess());
        Assertions.assertNull(resultForDeact.getValue());
    }

}
