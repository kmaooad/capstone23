package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.SetHiringStatusOff;
import edu.kmaooad.capstone23.departments.commands.SetHiringStatusOn;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import edu.kmaooad.capstone23.departments.events.HiringStatusSettedOff;
import edu.kmaooad.capstone23.departments.events.HiringStatusSettedOn;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
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
    DepartmentService departmentService;

    @Inject
    DepartmentDriver departmentDriver;

    @Inject
    CommandHandler<BanEntity, EntityBanned> banHandler;

    @Inject
    JobRepository jobRepository;


    private String departmentId;

    private String jobId;

    @BeforeEach
    void setUp() {
        Department department = departmentDriver.createDepartment();


        Job job = new Job();
        job.name = "Initial Job";
        job.active = true;
        jobRepository.insert(job);


        department.jobs = new ArrayList<>();
        department.jobs.add(job.id.toString());

        departmentService.updateDepartment(department);

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

        Department department = departmentService.getDepartmentById(departmentId);

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

    @Test
    @DisplayName("Set hiring off test: Department is banned")
    void setHiringStatusWithBannedTest() {
        var banRequest = new BanEntity();
        banRequest.setEntityId(new ObjectId(departmentId));
        banRequest.setEntityType(BannedEntityType.Department.name());
        banRequest.setReason("Hello there");

        var banResult = banHandler.handle(banRequest);

        Assertions.assertTrue(banResult.isSuccess());
        Assertions.assertNotNull(banResult.getValue());

        SetHiringStatusOff command = new SetHiringStatusOff();

        command.setDepartmentId(departmentId);

        Result<HiringStatusSettedOff> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.VALIDATION_FAILED);
        Assertions.assertEquals(result.getMessage(), "Department is banned");

    }
}
