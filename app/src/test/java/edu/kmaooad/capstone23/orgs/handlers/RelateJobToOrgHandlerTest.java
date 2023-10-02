package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.orgs.commands.RelateJobToOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.JobToOrgRelated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@QuarkusTest
public class RelateJobToOrgHandlerTest {

    @Inject
    RelateJobToOrgHandler handler;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    JobRepository jobRepository;

    private String orgId;

    private String jobId;

    @BeforeEach
    void setUp() {
        jobRepository.deleteAll();
        orgsRepository.deleteAll();
        Org org = new Org();

        org.name = "Initial Department";
        org.description = "Initial Department Description";
        org.jobs = new ArrayList<>();

        Job job = new Job();
        job.name = "Initial Job";
        job.active = true;
        jobRepository.insert(job);

        org.jobs.add(job.id.toString());
        orgsRepository.insert(org);

        orgId = org.id.toString();
        jobId = job.id.toString();
    }


    @Test
    @DisplayName("Relate Job To Department: existed job")
    public void testBasicJobOrgConnectionCreation() {

        RelateJobToOrg command = new RelateJobToOrg();
        command.setOrgId(orgId);
        command.setJobId(jobId);

        Result<JobToOrgRelated> result = handler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), null);

        Assertions.assertEquals(result.getValue().getDepartmentId(), orgId);
        Assertions.assertEquals(result.getValue().getJobId(), jobId);

        Org org = orgsRepository.findById(orgId);

        Assertions.assertNotNull(org);

        Assertions.assertNotNull(org.jobs.stream().filter(job -> job.equals(jobId)).findFirst());

        Job job = jobRepository.findById(new ObjectId(jobId));

        Assertions.assertNotNull(job);

    }

    @Test
    @DisplayName("Relate Job To Department: notExisted job")
    public void testNotExistedJobOrgConnectionCreation() {

        RelateJobToOrg command = new RelateJobToOrg();
        command.setOrgId(orgId);
        command.setJobId("aaaaaaaaaaaaaaaaaaaaaaaa");

        Result<JobToOrgRelated> result = handler.handle(command);

        Assertions.assertNull(result.getValue());

        Org org = orgsRepository.findById(orgId);

        Assertions.assertNotNull(org);

        Assertions.assertNull(org.jobs.stream().filter(job -> job.equals("aaaaaaaaaaaaaaaaaaaaaaaa")).findFirst().orElse(null));

    }

}
