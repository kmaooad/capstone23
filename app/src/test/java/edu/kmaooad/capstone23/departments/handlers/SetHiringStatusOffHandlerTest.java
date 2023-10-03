package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.SetHiringStatusOff;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

@QuarkusTest
public class SetHiringStatusOffHandlerTest {

    @Inject
    SetHiringStatusOffHandler handler;

    @Inject
    DepartmentsRepository departmentsRepository;

    @Inject
    JobRepository jobRepository;


    private String departmentId;

    private String jobId;

    @BeforeEach
    void setUp() {
        departmentsRepository.deleteAll();
        Department department = new Department();

        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        department.members = new ArrayList<>();


        Job job = new Job();
        job.name = "Initial Job";
        job.active = true;
        jobRepository.insert(job);


        department.jobs = new ArrayList<>();
        department.jobs.add(job.id.toString());

        departmentsRepository.insert(department);

        departmentId = department.id.toString();

        jobId = job.id.toString();
    }


    @Test
    @DisplayName("Set hiring status off")
    void setHiringStatusOn() {
        SetHiringStatusOff command = new SetHiringStatusOff();

        command.setDepartmentId(departmentId);

        Result result = handler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), null);
        Assertions.assertTrue(result.isSuccess());

        Department department = departmentsRepository.findById(departmentId);

        Assertions.assertEquals(department.hiringStatus, "Off");

        Job job = jobRepository.findById(new ObjectId(jobId));

        Assertions.assertNotNull(job);

        Assertions.assertFalse(job.active);
    }

    @Test
    @DisplayName("Set hiring status off with wrong id")
    void setHiringStatusOffWithWrongId() {
        SetHiringStatusOff command = new SetHiringStatusOff();
        command.setDepartmentId("64fbb243275c1111167b87a3");

        Result result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

}