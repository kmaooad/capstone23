package edu.kmaooad.capstone23.cvs.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import edu.kmaooad.capstone23.cvs.dal.CV;
import java.time.LocalDateTime;
import edu.kmaooad.capstone23.common.*;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import edu.kmaooad.capstone23.cvs.commands.ReadCV;
import edu.kmaooad.capstone23.cvs.events.CVRead;
import org.junit.jupiter.api.BeforeEach;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import edu.kmaooad.capstone23.cvs.commands.CreateJobPref;
import edu.kmaooad.capstone23.cvs.events.JobPrefCreated;
import org.bson.types.ObjectId;
@QuarkusTest
public class ReadCVHandlerTest {

    @Inject
    CommandHandler<CreateCV, CVCreated> createHandler;

    @Inject
    CommandHandler<ReadCV, CVRead> readHandler;

    @Inject
    CommandHandler<CreateJobPref, JobPrefCreated> createJobPrefHandler;

    @Inject
    CVRepository cvRepository;

    @BeforeEach
    public void setup() {
        cvRepository.deleteAll();

        CreateCV createCommand1 = new CreateCV();
        createCommand1.setDateTimeCreated(LocalDateTime.now());
        createCommand1.setTextInfo("some info about a student");
        createCommand1.setStatus(CV.Status.CLOSED);
        createCommand1.setVisibility(CV.Visibility.VISIBLE);
        Result<CVCreated> resultreate1 = createHandler.handle(createCommand1);

        ObjectId cvId1 = new ObjectId(resultreate1.getValue().getCVId());
        CreateJobPref command1 = new CreateJobPref();
        command1.setCvId(cvId1);
        command1.setLocation("Kyiv");
        command1.setIndustry("IT");
        command1.setCategory(JobPreference.Category.PART_TIME);
        Result<JobPrefCreated> resultCreateJobPref1 = createJobPrefHandler.handle(command1);

        CreateCV createCommand2 = new CreateCV();
        createCommand2.setDateTimeCreated(LocalDateTime.now());
        createCommand2.setTextInfo("new student");
        createCommand2.setStatus(CV.Status.OPEN);
        createCommand2.setVisibility(CV.Visibility.VISIBLE);
        Result<CVCreated> resultreate2 = createHandler.handle(createCommand2);

        ObjectId cvId2 = new ObjectId(resultreate2.getValue().getCVId());
        CreateJobPref command2 = new CreateJobPref();
        command2.setCvId(cvId2);
        command2.setLocation("Lviv");
        command2.setIndustry("IT");
        command2.setCategory(JobPreference.Category.PART_TIME);
        Result<JobPrefCreated> resultCreateJobPref2 = createJobPrefHandler.handle(command2);

        CreateCV createCommand3 = new CreateCV();
        createCommand3.setDateTimeCreated(LocalDateTime.now());
        createCommand3.setTextInfo("here info about student");
        createCommand3.setStatus(CV.Status.OPEN);
        createCommand3.setVisibility(CV.Visibility.VISIBLE);
        Result<CVCreated> resultreate3 = createHandler.handle(createCommand3);

        ObjectId cvId3 = new ObjectId(resultreate3.getValue().getCVId());
        CreateJobPref command3 = new CreateJobPref();
        command3.setCvId(cvId3);
        command3.setLocation("Lviv");
        command3.setIndustry("Marketing");
        command3.setCategory(JobPreference.Category.FULL_TIME);
        Result<JobPrefCreated> resultCreateJobPref3 = createJobPrefHandler.handle(command3);

        CreateCV createCommand4 = new CreateCV();
        createCommand4.setDateTimeCreated(LocalDateTime.now());
        createCommand4.setTextInfo("here new info about student");
        createCommand4.setStatus(CV.Status.OPEN);
        createCommand4.setVisibility(CV.Visibility.VISIBLE);
        Result<CVCreated> resultreate4 = createHandler.handle(createCommand4);

        ObjectId cvId4 = new ObjectId(resultreate4.getValue().getCVId());
        CreateJobPref command4 = new CreateJobPref();
        command4.setCvId(cvId4);
        command4.setLocation("Lviv");
        command4.setIndustry("Marketing");
        command4.setCategory(JobPreference.Category.PART_TIME);
        Result<JobPrefCreated> resultCreateJobPref4 = createJobPrefHandler.handle(command4);
    }

    @Test
    @DisplayName("Read Cvs: status param")
    void testWithStatusParamHandling() {
        ReadCV readCommand = new ReadCV();
        readCommand.setStatus(CV.Status.OPEN);
        Result<CVRead> result = readHandler.handle(readCommand);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(3,result.getValue().getCvs().size());
    }

    @Test
    @DisplayName("Read Cvs: industry param")
    void testWithIndustryParamHandling() {
        ReadCV readCommand = new ReadCV();
        readCommand.setIndustry("Marketing");
        Result<CVRead> result = readHandler.handle(readCommand);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(2,result.getValue().getCvs().size());
    }

    @Test
    @DisplayName("Read Cvs: location & category param")
    void testWithLocationAndCategoryParamHandling() {
        ReadCV readCommand = new ReadCV();
        readCommand.setLocation("Lviv");
        readCommand.setCategory(JobPreference.Category.FULL_TIME);
        Result<CVRead> result = readHandler.handle(readCommand);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(1,result.getValue().getCvs().size());
    }

    @Test
    @DisplayName("Read Cvs: location & industry param")
    void testWithLocationAndIndustryParamHandling() {
        ReadCV readCommand = new ReadCV();
        readCommand.setLocation("Kyiv");
        readCommand.setIndustry("IT");
        Result<CVRead> result = readHandler.handle(readCommand);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(1,result.getValue().getCvs().size());
    }


    @Test
    @DisplayName("Read Cvs: no params")
    void testHandleWithoutStatus() {

        ReadCV readCommand = new ReadCV();
        Result<CVRead> result = readHandler.handle(readCommand);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(4,result.getValue().getCvs().size());
    }

}
