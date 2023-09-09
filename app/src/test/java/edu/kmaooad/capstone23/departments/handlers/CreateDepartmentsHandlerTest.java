package edu.kmaooad.capstone23.departments.handlers;

import jakarta.inject.Inject;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@QuarkusTest
public class CreateDepartmentsHandlerTest {
    @Inject
    CreateDepartmentHandler handler;

    @Inject
    DepartmentsRepository departmentsRepository;

    @Test
    @DisplayName("Create Departments: Basic successful handling when parent is set correctly")
    void testSuccessfulHandling() {
        String parentOrgName = "NaUKMA";
        String departmentName = "FSNST";


        CreateDepartment command = new CreateDepartment();
        command.setName(departmentName);
        command.setParent(parentOrgName);

        Result<DepartmentCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        Department createdDepartment = departmentsRepository.findByName(departmentName);
        Assertions.assertTrue(createdDepartment != null);
        Assertions.assertEquals(departmentName, createdDepartment.name);
    }

    @Test
    @DisplayName("Create Departments: Basic error handling when parent is set incorrectly")
    void testCreateWithNonExistentParent() {
        String parentOrgName = "NonExistentParent";
        String departmentName = "NewDepartment";

        CreateDepartment command = new CreateDepartment();
        command.setName(parentOrgName);
        command.setParent(departmentName);

        Result<DepartmentCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.EXCEPTION, result.getErrorCode());
        Assertions.assertEquals("Parent not found", result.getMessage());
    }
}
