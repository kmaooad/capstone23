
package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.DeactivateJob;
import edu.kmaooad.capstone23.jobs.commands.UpdateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobsRepository;
import edu.kmaooad.capstone23.jobs.events.JobDeactivated;
import edu.kmaooad.capstone23.jobs.events.JobUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateJobHandlerTest {
    @Inject
    CommandHandler<UpdateJob, JobUpdated> handler;

    @Inject
    private JobsRepository repository;
    private ObjectId idToUpdate;

    @BeforeEach
    void setUp(){
        Job job = new Job();
        job.name = "testJob";
        repository.insert(job);
        idToUpdate = job.id;
    }
    @Test
    void testSuccessfulHandling() {
        UpdateJob command = new UpdateJob();
        command.setJobId(idToUpdate.toString());
        command.setJobName("newJobName");

        System.out.println("Before calling handler.handle(command)");

        Result<JobUpdated> result = handler.handle(command);

        System.out.println("After calling handler.handle(command)");

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getJobId().isEmpty());
    }

}
