package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import edu.kmaooad.capstone23.cvs.dal.CV;
import java.time.LocalDateTime;
import edu.kmaooad.capstone23.common.*;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import edu.kmaooad.capstone23.cvs.commands.CreateJobPref;
import edu.kmaooad.capstone23.cvs.events.JobPrefCreated;
import edu.kmaooad.capstone23.cvs.commands.UpdateJobPref;
import edu.kmaooad.capstone23.cvs.events.JobPrefUpdated;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
@QuarkusTest
public class UpdateJobPrefHandlerTest {
    @BeforeAll
    static void deleteAllData() {
        CVRepository repository = new CVRepository();
        repository.deleteAll();
    }

    @Inject
    CommandHandler<CreateCV, CVCreated> createHandler;

    @Inject
    CommandHandler<CreateJobPref, JobPrefCreated> createJobPrefHandler;

    @Inject
    CommandHandler<UpdateJobPref, JobPrefUpdated> updateJobPrefHandler;

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
    @DisplayName("Update job preferences: successful handling")
    void testSuccessfulHandling() {
        ObjectId cvId = new ObjectId(getCreateCvId());
        CreateJobPref createJobPrefcommand = new CreateJobPref();
        createJobPrefcommand.setCvId(cvId);
        createJobPrefcommand.setLocation("Kyiv");
        createJobPrefcommand.setIndustry("IT");
        createJobPrefcommand.setCategory(JobPreference.Category.PART_TIME);

        Result<JobPrefCreated> resultcreate = createJobPrefHandler.handle(createJobPrefcommand);

        UpdateJobPref updateJobPrefcommand = new UpdateJobPref();
        updateJobPrefcommand.setCvId(resultcreate.getValue().getCVId());
        updateJobPrefcommand.setLocation("Lviv");
        updateJobPrefcommand.setIndustry("Marketing");

        Result<JobPrefUpdated> result = updateJobPrefHandler.handle(updateJobPrefcommand);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

}
