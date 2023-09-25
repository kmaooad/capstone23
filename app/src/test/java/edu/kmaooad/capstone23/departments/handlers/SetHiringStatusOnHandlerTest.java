package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.SetHiringStatusOn;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
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
    DepartmentsRepository departmentsRepository;
    private String departmentId;

    @BeforeEach
    void setUp() {
        Department department = new Department();

        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        department.members = new ArrayList<>();
        departmentsRepository.insert(department);

        departmentId = department.id.toString();
    }


    @Test
    @DisplayName("Set hiring status on")
    void setHiringStatusOn() {
        SetHiringStatusOn command = new SetHiringStatusOn();

        command.setDepartmentId(departmentId);

        Result result = handler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), null);
        Assertions.assertTrue(result.isSuccess());

        Department department = departmentsRepository.findById(departmentId);

        Assertions.assertEquals(department.hiringStatus, "We are hiring");
    }

    @Test
    @DisplayName("Set hiring status on with wrong id")
    void setHiringStatusOnWithWrongId() {
        SetHiringStatusOn command = new SetHiringStatusOn();
        command.setDepartmentId("64fbb243275c1111167b87a3");

        Result result = handler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), "VALIDATION_FAILED");
        Assertions.assertFalse(result.isSuccess());
    }

}