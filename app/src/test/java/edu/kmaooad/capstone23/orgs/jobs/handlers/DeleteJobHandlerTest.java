package edu.kmaooad.capstone23.orgs.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.DeleteJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import edu.kmaooad.capstone23.jobs.events.JobDeleted;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.kmaooad.capstone23.common.Result;
import java.util.HashMap;
import java.util.Map;
import jakarta.inject.Inject;
@QuarkusTest
public class DeleteJobHandlerTest {
    @Inject
    CommandHandler<CreateJob, JobCreated> handler;
    @Inject
    CommandHandler<DeleteJob, JobDeleted> handlerForDel;
    @Test
    void testSuccessfulHandling() {
//        Map<String, Object> jsonAsMap = new HashMap<>();
//        ObjectId id = new ObjectId("64faf6ad341e202c91f76c83");
//        jsonAsMap.put("_id", id);

        CreateJob command = new CreateJob("IT teacher", true);

        Result<JobCreated> result = handler.handle(command);

        DeleteJob commandForDel = new DeleteJob(result.getValue().getJobId());

        Result<JobDeleted> resultForDel = handlerForDel.handle(commandForDel);

        Assertions.assertTrue(resultForDel.isSuccess());
        Assertions.assertNotNull(resultForDel.getValue());
        Assertions.assertTrue(resultForDel.getValue().getJobId().equals(result.getValue().getJobId()));


    }

}
