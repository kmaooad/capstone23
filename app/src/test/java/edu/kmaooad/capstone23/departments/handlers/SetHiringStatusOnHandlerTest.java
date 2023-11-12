package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.dal.EntityBanRepository;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.ban.handlers.BanEntityHandler;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.SetHiringStatusOn;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import edu.kmaooad.capstone23.departments.events.HiringStatusSettedOn;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@QuarkusTest
public class SetHiringStatusOnHandlerTest {

    @Inject
    SetHiringStatusOnHandler handler;

    @Inject
    CommandHandler<BanEntity, EntityBanned> banHandler;

    @Inject
    EntityBanRepository entityBanRepository;

    @Inject
    DepartmentService departmentService;

    @Inject
    DepartmentDriver departmentDriver;

    private String departmentId;

    @BeforeEach
    void setUp() {
        entityBanRepository.deleteAll();

        Department department = departmentDriver.createDepartment();

        departmentId = department.id.toString();
    }


    @Test
    @DisplayName("Set hiring status on")
    void setHiringStatusOn() {
        SetHiringStatusOn command = new SetHiringStatusOn();

        command.setDepartmentId(departmentId);

        Result<HiringStatusSettedOn> result = handler.handle(command);

        Assertions.assertEquals(null, result.getMessage());
        Assertions.assertEquals(null, result.getErrorCode());
        Assertions.assertTrue(result.isSuccess());

        Department department = departmentService.getDepartmentById(departmentId);

        Assertions.assertEquals("We are hiring", department.hiringStatus);
    }

    @Test
    @DisplayName("Set hiring status on with wrong id")
    void setHiringStatusOnWithWrongId() {
        SetHiringStatusOn command = new SetHiringStatusOn();
        command.setDepartmentId("64fbb243275c1111167b87a3");

        Result<HiringStatusSettedOn> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }


    @Test
    @DisplayName("Set hiring on test: Department is banned")
    void setHiringStatusWithBannedTest() {
        var banRequest = new BanEntity();
        banRequest.setEntityId(new ObjectId(departmentId));
        banRequest.setEntityType(BannedEntityType.Department.name());
        banRequest.setReason("Hello there");

        var banResult = banHandler.handle(banRequest);

        Assertions.assertTrue(banResult.isSuccess());
        Assertions.assertNotNull(banResult.getValue());

        SetHiringStatusOn command = new SetHiringStatusOn();

        command.setDepartmentId(departmentId);

        Result<HiringStatusSettedOn> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.VALIDATION_FAILED);
        Assertions.assertEquals(result.getMessage(), "Department is banned");

    }
}
