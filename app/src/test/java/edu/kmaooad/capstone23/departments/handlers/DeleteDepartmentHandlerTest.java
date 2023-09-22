package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.DeleteDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentDeleted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

@QuarkusTest
public class DeleteDepartmentHandlerTest {
    @BeforeAll
    static void deleteAllData() {
        DepartmentsRepository repository = new DepartmentsRepository();
        repository.deleteAll();
    }
    private String departmentId;

    @Inject
    DeleteDepartmentHandler handler;

    @Inject
    DepartmentsRepository departmentsRepository;

    @BeforeEach
    void setUp() {
        Department department = new Department();
        department.name = "Department to Delete";
        department.description = "This is a department to be deleted";
        department.parent = "NaUKMA";
        departmentsRepository.insert(department);

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

        Department deletedDepartment = departmentsRepository.findById(departmentId);
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


