package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatusOff;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@QuarkusTest
public class SetHiringStatusOffHandlerTest {

    @Inject
    SetHiringStatusOffHandler handler;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    JobRepository jobRepository;


    private String orgId;

    private String jobId;

    @BeforeEach
    void setUp() {
        orgsRepository.deleteAll();
        Org org = new Org();

        org.name = "Initial Organization";
        org.description = "Initial Organization Description";


        Job job = new Job();
        job.name = "Initial Job";
        job.active = true;
        jobRepository.insert(job);


        org.jobs = new ArrayList<>();
        org.jobs.add(job.id.toString());

        orgsRepository.insert(org);

        orgId = org.id.toString();

        jobId = job.id.toString();
    }


    @Test
    @DisplayName("Set hiring status off")
    void setHiringStatusOn() {
        SetHiringStatusOff command = new SetHiringStatusOff();

        command.setOrgId(orgId);

        Result result = handler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), null);
        Assertions.assertTrue(result.isSuccess());

        Org department = orgsRepository.findById(orgId);

        Assertions.assertEquals(department.hiringStatus, "Off");

        Job job = jobRepository.findById(new ObjectId(jobId));

        Assertions.assertNotNull(job);

        Assertions.assertFalse(job.active);
    }

    @Test
    @DisplayName("Set hiring status off with wrong id")
    void setHiringStatusOffWithWrongId() {
        SetHiringStatusOff command = new SetHiringStatusOff();
        command.setOrgId("64fbb243275c1111167b87a3");

        Result result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

}