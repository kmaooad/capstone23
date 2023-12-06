package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.DeleteDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import edu.kmaooad.capstone23.departments.events.DepartmentDeleted;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteDepartmentHandlerTest {
    private String departmentId;

    @Inject
    DeleteDepartmentHandler handler;

    @Inject
    DepartmentService departmentService;

    @Inject
    DepartmentDriver departmentDriver;

    @BeforeEach
    void setUp() {
        Department department = departmentDriver.createDepartment();

        departmentId = department.id.toString();
    }

    @Test
    @DisplayName("Delete Department: Successful handling")
    void testSuccessfulHandling() {
        DeleteDepartment command = new DeleteDepartment();
        command.setId(departmentId);

        Result<DepartmentDeleted> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(departmentId, result.getValue().getId());

        Department deletedDepartment = departmentService.getDepartmentById(departmentId);
        Assertions.assertNull(deletedDepartment);
    }

    @Test
    void notificationSentOnDepartmentDeleted() {
        DeleteDepartment command = new DeleteDepartment();
        command.setId(departmentId);

        Result<DepartmentDeleted> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(departmentId, result.getValue().getId());

        Department deletedDepartment = departmentService.getDepartmentById(departmentId);
        Assertions.assertNull(deletedDepartment);
    }

    @Test
    @DisplayName("Delete Department: Handle non-existent department")
    void testHandleWithNonExistentId() {
        String nonexistentId = "64fbb243275c1111167b87a3";

        DeleteDepartment command = new DeleteDepartment();
        command.setId(nonexistentId);

        Result<DepartmentDeleted> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.EXCEPTION, result.getErrorCode());
        Assertions.assertEquals("Department not found", result.getMessage());
    }
}


