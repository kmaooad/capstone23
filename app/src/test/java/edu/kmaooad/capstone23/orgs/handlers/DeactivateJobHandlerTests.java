package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.DeactivateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobsRepository;
import edu.kmaooad.capstone23.jobs.events.JobDeactivated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeactivateJobHandlerTests {
    @Inject
    CommandHandler<DeactivateJob, JobDeactivated> handler;

    @Inject
    private JobsRepository repository;
    private ObjectId idToActivate;

    @BeforeEach
    void setUp(){
        Job job = new Job();
        job.name = "testJob";
        repository.insert(job);
        idToActivate = job.id;
    }
    @Test
    void testSuccessfulHandling() {
        DeactivateJob command = new DeactivateJob();
        command.setJobId(idToActivate.toString());

        System.out.println("Before calling handler.handle(command)");

        Result<JobDeactivated> result = handler.handle(command);

        System.out.println("After calling handler.handle(command)");

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getJobId().isEmpty());
    }

}
