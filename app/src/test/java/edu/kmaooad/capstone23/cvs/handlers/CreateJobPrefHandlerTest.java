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
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
@QuarkusTest
public class CreateJobPrefHandlerTest {

    @Inject
    CommandHandler<CreateCV, CVCreated> createHandler;

    @Inject
    CommandHandler<CreateJobPref, JobPrefCreated> createJobPrefHandler;

    ObjectId getCreateCvId(){
        CreateCV command = new CreateCV();
        command.setDateTimeCreated(LocalDateTime.now());
        command.setTextInfo("some info about a student");
        command.setStatus(CV.Status.OPEN);
        command.setVisibility(CV.Visibility.VISIBLE);

        Result<CVCreated> result = createHandler.handle(command);
        return result.getValue().getCVId();
    }

    @Test
    @DisplayName("Create job preferences: successful handling")
    void testSuccessfulHandling() {
        ObjectId cvId = getCreateCvId();
        CreateJobPref command = new CreateJobPref();
        command.setCvId(cvId);
        command.setLocation("Kyiv");
        command.setIndustry("IT");
        command.setCategory(JobPreference.Category.PART_TIME);

        Result<JobPrefCreated> result = createJobPrefHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
    }

    @Test
    @DisplayName("Create job preferences: location null")
    void testLocationNullHandling() {
        ObjectId cvId = getCreateCvId();
        CreateJobPref command = new CreateJobPref();
        command.setCvId(cvId);
        command.setIndustry("IT");
        command.setCategory(JobPreference.Category.PART_TIME);

        Result<JobPrefCreated> result = createJobPrefHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Create job preferences: location blank")
    void testLocationBlankHandling() {
        ObjectId cvId = getCreateCvId();
        CreateJobPref command = new CreateJobPref();
        command.setCvId(cvId);
        command.setLocation(" ");
        command.setIndustry("IT");
        command.setCategory(JobPreference.Category.PART_TIME);

        Result<JobPrefCreated> result = createJobPrefHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Create job preferences: location too big name")
    void testLocationBigNameHandling() {
        ObjectId cvId = getCreateCvId();
        CreateJobPref command = new CreateJobPref();
        command.setCvId(cvId);
        command.setLocation("fwefeewgwegegrekgwgjreiiiiiiiiiiioooooooooooooooooooojwwwwwwwwwwwwwwwwwwwwwwqqqqqqqqqqqqqqqqqqqqqqqqqqqqwce");
        command.setIndustry("IT");
        command.setCategory(JobPreference.Category.PART_TIME);

        Result<JobPrefCreated> result = createJobPrefHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Create job preferences: industry null")
    void testIndustryNullHandling() {
        ObjectId cvId = getCreateCvId();
        CreateJobPref command = new CreateJobPref();
        command.setCvId(cvId);
        command.setLocation("Kyiv");
        command.setCategory(JobPreference.Category.PART_TIME);

        Result<JobPrefCreated> result = createJobPrefHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Create job preferences: industry blank")
    void testIndustryBlankHandling() {
        ObjectId cvId = getCreateCvId();
        CreateJobPref command = new CreateJobPref();
        command.setCvId(cvId);
        command.setLocation("Kyiv");
        command.setIndustry(" ");
        command.setCategory(JobPreference.Category.PART_TIME);

        Result<JobPrefCreated> result = createJobPrefHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Create job preferences: industry too big name")
    void testIndustryBigNameHandling() {
        ObjectId cvId = getCreateCvId();
        CreateJobPref command = new CreateJobPref();
        command.setCvId(cvId);
        command.setLocation("Kyiv");
        command.setIndustry("fwefeewgwegegrekgwgjreiiiiiiiiiiioooooooooooooooooooojwwwwwwwwwwwwwwwwwwwwwwqqqqqqqqqqqqqqqqqqqqqqqqqqqqwce");
        command.setCategory(JobPreference.Category.PART_TIME);

        Result<JobPrefCreated> result = createJobPrefHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

}
