package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.RequestToJoinDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.dal.Request;
import edu.kmaooad.capstone23.departments.dal.RequestsRepository;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RequestToJoinDepartmentHandlerTest {
    @Inject
    CreateRequestToJoinHandler handler;

    @Inject
    DepartmentsRepository departmentsRepository;

    @Inject
    RequestsRepository requestsRepository;

    private String departmentId;

    @BeforeEach
    void setUp() {
        Department department = new Department();

        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        departmentsRepository.insert(department);

        departmentId = department.id.toString();
    }

    @Test
    @DisplayName("Create Request to Join Department: Basic")
    public void testBasicRequestToJoinDepartment() {
        String userName = "user1";

        RequestToJoinDepartment command = new RequestToJoinDepartment();
        command.setUserName(userName);
        command.setDepartmentId(departmentId);

        Result<RequestCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        Request resultRequest = requestsRepository.findById(result.getValue().getId());
        Assertions.assertNotNull(resultRequest);
    }


    @Test
    @DisplayName("Create Request to Join Department: Error handling when department is not found")
    public void testRequestToJoinDepartmentWithNonExistentDepartment() {
        String userName = "user1";
        departmentId = "64fbb243275c1111167b87a3";

        RequestToJoinDepartment command = new RequestToJoinDepartment();
        command.setUserName(userName);
        command.setDepartmentId(departmentId);

        Result<RequestCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals("Department not found", result.getMessage());
    }

}
