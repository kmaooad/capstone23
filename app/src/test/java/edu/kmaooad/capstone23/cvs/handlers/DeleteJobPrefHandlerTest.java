package edu.kmaooad.capstone23.cvs.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import edu.kmaooad.capstone23.cvs.dal.CV;
import java.time.LocalDateTime;
import edu.kmaooad.capstone23.common.*;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import edu.kmaooad.capstone23.cvs.commands.CreateJobPref;
import edu.kmaooad.capstone23.cvs.events.JobPrefCreated;
import edu.kmaooad.capstone23.cvs.commands.DeleteJobPref;
import edu.kmaooad.capstone23.cvs.events.JobPrefDeleted;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
@QuarkusTest
public class DeleteJobPrefHandlerTest {

    @Inject
    CommandHandler<CreateCV, CVCreated> createHandler;

    @Inject
    CommandHandler<DeleteJobPref, JobPrefDeleted> deleteJobPrefHandler;

    @Inject
    CommandHandler<CreateJobPref, JobPrefCreated> createJobPrefHandler;

    String getCreateCvId(){
        CreateCV command = new CreateCV();
        command.setDateTimeCreated(LocalDateTime.now());
        command.setTextInfo("some info about a student");
        command.setStatus(CV.Status.OPEN);
        command.setVisibility(CV.Visibility.VISIBLE);

        Result<CVCreated> result = createHandler.handle(command);


        return result.getValue().getCVId();
    }

    @Test
    @DisplayName("Delete job preferences: successful handling")
    void testSuccessfulHandling() {
        ObjectId cvId = new ObjectId(getCreateCvId());
        CreateJobPref command = new CreateJobPref();
        command.setCvId(cvId);
        command.setLocation("Kyiv");
        command.setIndustry("IT");
        command.setCategory(JobPreference.Category.PART_TIME);

        Result<JobPrefCreated> resultCreate = createJobPrefHandler.handle(command);

        DeleteJobPref commandDel = new DeleteJobPref();
        commandDel.setCvId(cvId);
        Result<JobPrefDeleted> result = deleteJobPrefHandler.handle(commandDel);

        Assertions.assertTrue(result.isSuccess());
    }

}
