package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.UpdateDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateDepartmentsHandlerTest {
    @Inject
    UpdateDepartmentHandler handler;

    @Inject
    DepartmentsRepository departmentsRepository;

    private String idToUpdate;

    @BeforeEach
    void setUp() {
        Department department = new Department();

        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        departmentsRepository.insert(department);

        idToUpdate = department.id.toString();
    }

    @Test
    @DisplayName("Update Departments: Basic successful handling when id and parent is set correctly")
    void testSuccessfulHandling() {
        String parentOrgName = "NaUKMA";
        String departmentName = "FSNST";


        UpdateDepartment command = new UpdateDepartment();
        command.setName(departmentName);
        command.setParent(parentOrgName);
        command.setId(idToUpdate);

        Result<DepartmentUpdated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        Department createdDepartment = departmentsRepository.findByName(departmentName);
        Assertions.assertTrue(createdDepartment != null);
        Assertions.assertEquals(departmentName, createdDepartment.name);
    }

}
