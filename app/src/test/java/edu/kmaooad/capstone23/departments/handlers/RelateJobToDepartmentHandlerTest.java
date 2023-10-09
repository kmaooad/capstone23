package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.handlers.BanEntityHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.ApproveJoinRequest;
import edu.kmaooad.capstone23.departments.commands.RelateJobToDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.dal.Request;
import edu.kmaooad.capstone23.departments.dal.RequestsRepository;
import edu.kmaooad.capstone23.departments.events.JobToDepartmentRelated;
import edu.kmaooad.capstone23.departments.events.RequestApproved;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.common.constraint.Assert;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static edu.kmaooad.capstone23.common.ErrorCode.EXCEPTION;

@QuarkusTest
public class RelateJobToDepartmentHandlerTest {

    @Inject
    RelateJobToDepartmentHandler handler;

    @Inject
    BanEntityHandler banEntityHandler;

    @Inject
    DepartmentsRepository departmentsRepository;

    @Inject
    JobRepository jobRepository;

    private String departmentId;

    private String jobId;

    @BeforeEach
    void setUp() {
        jobRepository.deleteAll();
        departmentsRepository.deleteAll();
        Department department = new Department();

        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        department.members = new ArrayList<>();
        department.jobs = new ArrayList<>();

        Job job = new Job();
        job.name = "Initial Job";
        job.active = true;
        jobRepository.insert(job);

        department.jobs.add(job.id.toString());
        departmentsRepository.insert(department);

        departmentId = department.id.toString();
        jobId = job.id.toString();
    }


    @Test
    @DisplayName("Relate Job To Department: existed job")
    public void testBasicJobDepartmentConnectionCreation() {

        RelateJobToDepartment command = new RelateJobToDepartment();
        command.setDepartmentId(departmentId);
        command.setJobId(jobId);

        Result<JobToDepartmentRelated> result = handler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), null);

        Assertions.assertEquals(result.getValue().getDepartmentId(), departmentId);
        Assertions.assertEquals(result.getValue().getJobId(), jobId);

        Department department = departmentsRepository.findById(departmentId);

        Assertions.assertNotNull(department);

        Assertions.assertNotNull(department.jobs.stream().filter(job -> job.equals(jobId)).findFirst());

        Job job = jobRepository.findById(new ObjectId(jobId));

        Assertions.assertNotNull(job);

    }

    @Test
    @DisplayName("Relate Job To Department: notExisted job")
    public void testNotExistedJobDepartmentConnectionCreation() {
        RelateJobToDepartment command = new RelateJobToDepartment();
        command.setDepartmentId(departmentId);
        command.setJobId("aaaaaaaaaaaaaaaaaaaaaaaa");

        Result<JobToDepartmentRelated> result = handler.handle(command);

        Assertions.assertNull(result.getValue());

        Department department = departmentsRepository.findById(departmentId);

        Assertions.assertNotNull(department);

        Assertions.assertNull(department.jobs.stream().filter(job -> job.equals("aaaaaaaaaaaaaaaaaaaaaaaa")).findFirst().orElse(null));
    }

    @Test
    @DisplayName("Relate Job To Department: department banned")
    public void testBannedDepartmentJobDepartmentConnectionCreation() {
        BanEntity banCommand = new BanEntity();
        banCommand.setEntityId(new ObjectId(departmentId));
        banCommand.setEntityType("Department");
        banCommand.setReason("Hello there");

        var banResult = banEntityHandler.handle(banCommand);
        Assertions.assertTrue(banResult.isSuccess());

        RelateJobToDepartment command = new RelateJobToDepartment();
        command.setDepartmentId(departmentId);
        command.setJobId(jobId);

        Result<JobToDepartmentRelated> result = handler.handle(command);
        Assertions.assertEquals(EXCEPTION, result.getErrorCode());
        Assertions.assertEquals("Department is banned", result.getMessage());
    }
}
